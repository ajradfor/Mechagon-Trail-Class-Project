package doslosmuertos.mechagontrail;

import doslosmuertos.mechagontrail.util.GameState;
import doslosmuertos.mechagontrail.util.MechagonTrailApplication;
import doslosmuertos.mechagontrail.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Date;
import java.util.Random;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class GameLoop extends Activity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * If set, will toggle the system UI visibility upon interaction. Otherwise,
     * will show the system UI visibility upon interaction.
     */
    private static final boolean TOGGLE_ON_CLICK = true;

    /**
     * The flags to pass to {@link SystemUiHider#getInstance}.
     */
    private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;

    /**
     * The instance of the {@link SystemUiHider} for this activity.
     */
    private SystemUiHider mSystemUiHider;
    MechagonTrailApplication app;
    GameState gs;
    TextView days, pace, meals, distanceToGo, foodRemaining, fuelRemaining, eventText, health;
    Button paceUp, paceDown, goStop, mealsUp, mealsDown, goToShop;
    boolean pause;

    int slow = -1;
    int delay = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game_loop);

        final View contentView = findViewById(R.id.fullscreen_content);

        final int destination = 50;

        app = (MechagonTrailApplication)getApplication();
        gs = app.getGameState();

        pause = true;
        days = (TextView)findViewById(R.id.dayCounter);
        days.setText("Space Day " + app.getGameState().getDay());

        meals = (TextView)findViewById(R.id.meals);
        meals.setText("Meals: " + gs.getMeals());

        pace = (TextView)findViewById(R.id.pace);
        pace.setText("Pace: " + app.getGameState().getPace());

        distanceToGo = (TextView)findViewById(R.id.distanceToGo);
        distanceToGo.setText("Distance to go: " + (destination - gs.getDistance()));

        fuelRemaining = (TextView)findViewById(R.id.fuelRemaining);
        fuelRemaining.setText("Fuel Remaining: " + gs.getMech().getFuel());

        foodRemaining = (TextView)findViewById(R.id.foodRemaining);
        foodRemaining.setText("Food Remaining: " + gs.getMech().getFood());

        eventText = (TextView)findViewById(R.id.eventText);
        eventText.setText("");

        health = (TextView)findViewById(R.id.health);
        health.setText("Health: " + gs.getMech().getHealth());

        goToShop = (Button)findViewById(R.id.go_to_shop);
        goToShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ShopScreen.class);
                startActivity(intent);
            }
        });

        paceUp = (Button)findViewById(R.id.paceUp);
        paceUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gs.setPace(gs.getPace() + 1);
                if (gs.getPace() == 4) { gs.setPace(3); }
                if (gs.getPace() == 3) {
                    paceUp.setClickable(false);
                }
                else {
                    paceUp.setClickable(true);
                }
                paceDown.setClickable(true);
                pace.setText("Pace: " + app.getGameState().getPace());
            }
        });

        paceDown = (Button)findViewById(R.id.paceDown);
        paceDown.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                gs.setPace(gs.getPace() - 1);
                if (gs.getPace() == 0) { gs.setPace(1); }
                if (gs.getPace() == 1) {
                    paceDown.setClickable(false);
                }
                else {
                    paceDown.setClickable(true);
                }
                paceUp.setClickable(true);
                pace.setText("Pace: " + app.getGameState().getPace());
            }
        });

        mealsUp = (Button)findViewById(R.id.mealsUp);
        mealsUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                gs.setMeals(gs.getMeals() + 1);
                if (gs.getMeals() == 4) { gs.setMeals(3); }
                if (gs.getMeals() == 3) {
                    mealsUp.setClickable(false);
                }
                else {
                    mealsUp.setClickable(true);
                }
                mealsDown.setClickable(true);
                meals.setText("Meals: " + gs.getMeals());
            }
        });

        mealsDown = (Button)findViewById(R.id.mealsDown);
        mealsDown.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                gs.setMeals(gs.getMeals() - 1);
                if (gs.getMeals() == 0) { gs.setMeals(1); }
                if (gs.getMeals() == 1) {
                    mealsDown.setClickable(false);
                }
                else {
                    mealsDown.setClickable(true);
                }
                mealsUp.setClickable(true);
                meals.setText("Meals: " + gs.getMeals());
            }
        });

        goStop = (Button)findViewById(R.id.goStop);
        goStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pause) {
                    pause = false;
                    long lastTime = new Date().getTime();
                    Random rd = new Random();

                    // The chance an event will happen, in %
                    int eventChance = 10;
                    // The chance a given event will be beneficial, in %
                    int goodEventSeed = 35;

                    boolean ev = false;
                    int pace = gs.getPace();

                    while (!pause && gs.getDistance() <= destination) {
                        pace = gs.getPace();
                        long time = new Date().getTime();

                        if (time - lastTime > delay) {

                            eventText.setText("");

                            if (gs.getMech().getFood() == 0) {
                                gs.getMech().setHead(gs.getMech().getHead() - 1);
                                gs.getMech().setlArm(gs.getMech().getlArm() - 1);
                                gs.getMech().setrArm(gs.getMech().getrArm() - 1);
                                gs.getMech().setlLeg(gs.getMech().getlLeg() - 1);
                                gs.getMech().setrLeg(gs.getMech().getrLeg() - 1);
                                gs.getMech().updateHealth();
                            }

                            distanceToGo.setText("Distance to go: " + (destination - gs.getDistance()));

                            if (destination - gs.getDistance() <= 0) {
                                break;
                            }

                            int eventHappen = rd.nextInt(100) + 1;

                            if (eventHappen < eventChance) {
                                int eventCode = rd.nextInt(100) + 1;
                                if (eventCode < goodEventSeed) {
                                    goodEventTrigger(rd.nextInt(5) + 1);
                                }
                                else {
                                    badEventTrigger(rd.nextInt(6) + 1);
                                }
                                pace = gs.getPace();
                                eventChance = 5;
                                ev = true;
                            }
                            else { eventChance += 5; }

                            lastTime = time;

                            // Advance the gamestate
                            gs.increaseDistance((double)pace / 2);

                            gs.getMech().useFuel(2*pace);

                            if (gs.getMech().getFuel() <= 0) {
                                gs.getMech().setFuel(0);
                            }

                            gs.getMech().useFood(gs.getMeals());

                            if (gs.getMech().getFood() <= 0) {
                                gs.getMech().setFood(0);
                            }

                            slow--;
                            if (slow == 0) {
                                gs.setPace(2);
                                delay = 1000;
                            }
                            gs.increaseDay(1);

                            days.setText("Space Day " + gs.getDay());
                            distanceToGo.setText("Distance to go: " + (destination - gs.getDistance()));
                            fuelRemaining.setText("Fuel Remaining: " + gs.getMech().getFuel());
                            foodRemaining.setText("Food Remaining: " + gs.getMech().getFood());
                            health.setText("Health: " + gs.getMech().getHealth());
                            if (ev) { pause = true; ev = false; }
                        }
                    }
                }
                else {
                    pause = true;
                }
            }
        });

        // Set up an instance of SystemUiHider to control the system UI for
        // this activity.
        mSystemUiHider = SystemUiHider.getInstance(this, contentView, HIDER_FLAGS);
        mSystemUiHider.setup();
        mSystemUiHider
                .setOnVisibilityChangeListener(new SystemUiHider.OnVisibilityChangeListener() {
                    // Cached values.
                    int mControlsHeight;
                    int mShortAnimTime;

                    @Override
                    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
                    public void onVisibilityChange(boolean visible) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
                            if (mShortAnimTime == 0) {
                                mShortAnimTime = getResources().getInteger(
                                        android.R.integer.config_shortAnimTime);
                            }
                        }

                        if (visible && AUTO_HIDE) {
                            // Schedule a hide().
                            delayedHide(AUTO_HIDE_DELAY_MILLIS);
                        }
                    }
                });

        // Set up the user interaction to manually show or hide the system UI.
        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TOGGLE_ON_CLICK) {
                    mSystemUiHider.toggle();
                } else {
                    mSystemUiHider.show();
                }
            }
        });
    }

    private void goodEventTrigger(int event) {
        switch (event) {
            case 1:
                eventText.setText("Came upon a derelict spacecraft. Scavenge 15 space bucks, 50 units of fuel, and 50 ammo!");
                gs.stats.obtainCash(15);
                gs.getMech().obtainAmmo(50);
                gs.getMech().obtainFuel(50);
                break;
            case 2:
                eventText.setText("You syphoned 40 units of fuel from an unsuspecting spacecraft!");
                gs.getMech().obtainFuel(40);
                break;
            case 3:
                eventText.setText("You found 65 units of food in the trash at a space cafe.");
                gs.getMech().obtainFood(65);
                break;
            case 4:
                eventText.setText("Somebody left their wallet in the space truck stop bathroom. Get 20 space bucks.");
                gs.stats.obtainCash(20);
                break;
            case 5:
                Intent intent = new Intent(getApplicationContext(), ShopScreen.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private void badEventTrigger(int event) {
        switch (event) {
            case 1:
                eventText.setText("Defended the mech from space bandits! Used 20 ammo in the process.");
                gs.getMech().useAmmo(20);
                break;
            case 2:
                eventText.setText("An asteroid belt required some fancy flying - use up 30 units of fuel.");
                gs.getMech().useFuel(30);
                break;
            case 3:
                eventText.setText("Space bandits robbed you! They took 30 space bucks!");
                gs.stats.loseCash(30);
                break;
            case 4:
                eventText.setText("Thrusters malfunction: slow down for a bit!");
                gs.setPace(1);
                slow = 4;
                delay = 2000;
                break;
            case 5:
                eventText.setText("The mech is hit by a passing comet: everyone gets hurt.");
                gs.getMech().setHead(gs.getMech().getHead() - 2);
                gs.getMech().setlArm(gs.getMech().getlArm() - 2);
                gs.getMech().setrArm(gs.getMech().getrArm() - 2);
                gs.getMech().setlLeg(gs.getMech().getlLeg() - 2);
                gs.getMech().setrLeg(gs.getMech().getrLeg() - 2);
                gs.getMech().updateHealth();
                break;
            case 6:
                Intent intent = new Intent(getApplicationContext(), BattleScreen.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }


    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };

    Handler mHideHandler = new Handler();
    Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            mSystemUiHider.hide();
        }
    };

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }
}
