package doslosmuertos.mechagontrail;

import doslosmuertos.mechagontrail.util.SystemUiHider;
import doslosmuertos.mechagontrail.util.MechagonTrailApplication;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.view.Window;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class NewGameInfoScreen extends Activity {
    EditText headName;
    EditText lArmName;
    EditText rArmName;
    EditText lLegName;
    EditText rLegName;
    Button submit;

    // Used to keep a global GameState.
    // See doslosmuertos.mechagontrail.util.MechagonTrailApplication for details.
    MechagonTrailApplication app;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_new_game_info_screen);
        setupActionBar();

        final View contentView = findViewById(R.id.fullscreen_content);

        app = (MechagonTrailApplication)getApplication();

        submit = (Button)findViewById(R.id.submit_button);
        headName = (EditText)findViewById(R.id.head_name_box);
        lArmName = (EditText)findViewById(R.id.left_arm_name_box);
        rArmName = (EditText)findViewById(R.id.right_arm_name_box);
        lLegName = (EditText)findViewById(R.id.left_leg_name_box);
        rLegName = (EditText)findViewById(R.id.right_leg_name_box);

        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (!headName.getText().toString().isEmpty()) {
                    app.getGameState().playerCharacter.setName(headName.getText().toString());
                    if (!lArmName.getText().toString().isEmpty()) {
                        app.getGameState().leftArm.setName(lArmName.getText().toString());
                    }
                    else {
                        app.getGameState().leftArm.setName("Left Arm Pilot");
                    }
                    if (!rArmName.getText().toString().isEmpty()) {
                        app.getGameState().rightArm.setName(rArmName.getText().toString());
                    }
                    else {
                        app.getGameState().rightArm.setName("Right Arm Pilot");
                    }
                    if (!lLegName.getText().toString().isEmpty()) {
                        app.getGameState().leftLeg.setName(lLegName.getText().toString());
                    }
                    else {
                        app.getGameState().leftLeg.setName("Left Leg Pilot");
                    }
                    if (!rLegName.getText().toString().isEmpty()) {
                        app.getGameState().rightLeg.setName(rLegName.getText().toString());
                    }
                    else {
                        app.getGameState().rightLeg.setName("Right Leg Pilot");
                    }
                    Intent intent = new Intent(getApplicationContext(), DataPassTest.class);
                    startActivity(intent);
                }
            }
        });

        //headName = findViewById(R.id.head_name_box);

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
                            // If the ViewPropertyAnimator API is available
                            // (Honeycomb MR2 and later), use it to animate the
                            // in-layout UI controls at the bottom of the
                            // screen.
                            if (mControlsHeight == 0) {
                            }
                            if (mShortAnimTime == 0) {
                                mShortAnimTime = getResources().getInteger(
                                        android.R.integer.config_shortAnimTime);
                            }
                        } else {
                            // If the ViewPropertyAnimator APIs aren't
                            // available, simply show or hide the in-layout UI
                            // controls.

                        }

                        if (visible && AUTO_HIDE) {
                            // Schedule a hide().
                            delayedHide(AUTO_HIDE_DELAY_MILLIS);
                        }
                    }
                });

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
        //findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);
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
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setupActionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
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
