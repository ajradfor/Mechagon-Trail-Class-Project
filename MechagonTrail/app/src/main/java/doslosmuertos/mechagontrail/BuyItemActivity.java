package doslosmuertos.mechagontrail;

import doslosmuertos.mechagontrail.util.GameMech;
import doslosmuertos.mechagontrail.util.GameState;
import doslosmuertos.mechagontrail.util.Item;
import doslosmuertos.mechagontrail.util.MechagonTrailApplication;
import doslosmuertos.mechagontrail.util.SystemUiHider;
import doslosmuertos.mechagontrail.util.util.ItemNumberPair;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class BuyItemActivity extends Activity {

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

    EditText quantityField;
    Button buy, leave, repairKit, medicine,
           ammo, foodRations, fuel;
    TextView message, moneyLeft;
    MechagonTrailApplication app;
    GameState gameState;
    int itemCost, number;
    GameMech mech;
    Item item;
    ItemNumberPair pair;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_buy_item_avtivity);

        quantityField = (EditText) findViewById(R.id.editQuantity);

        repairKit = (Button) findViewById(R.id.repairKit_button);
        if (repairKit != null) {
            repairKit.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    item = new Item();
                    item.setName("Repair Kit");
                    itemCost = 100;
                }
            });
        }

        medicine = (Button) findViewById(R.id.medicine_button);
        medicine.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                item = new Item();
                item.setName("Medicine");
                itemCost = 50;
            }
        });

        ammo = (Button) findViewById(R.id.ammo_button);
        ammo.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                item = new Item();
                item.setName("Ammo");
                itemCost = 1;
            }
        });

        foodRations = (Button) findViewById(R.id.food_rations_button);
        foodRations.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                item = new Item();
                item.setName("Food Ration");
                itemCost = 10;
            }
        });

        fuel = (Button) findViewById(R.id.fuel_button);
        fuel.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                item = new Item();
                item.setName("Fuel");
                itemCost = 1;
            }
        });

        message = (TextView) findViewById(R.id.buy_info);
        moneyLeft = (TextView) findViewById(R.id.money_display);
        app = (MechagonTrailApplication)getApplication();
        gameState = app.getGameState();
        moneyLeft.setText("Money: " + gameState.stats.getCash());

        buy = (Button) findViewById(R.id.confirm_purchase);
        buy.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {

                number = Integer.parseInt(quantityField.getText().toString());
                //Check to see if they have enough money
                if(gameState.stats.getCash() >= (itemCost * number)){

                    //If they try to buy nothing of something, tell them they're weird
                    if(number <= 0){
                        message.setText("Wat r u doing?");
                        return;
                    }

                    //Decrement space cash
                    gameState.stats.setCash(gameState.stats.getCash() - (itemCost * number));
                    moneyLeft.setText("Money: " + gameState.stats.getCash());
                    //If the mech has the item, update the number

                    if(item.getName() == "Food Ration"){
                        gameState.getMech().addFood(number);
                        message.setText("Bought " + number + " food!");
                        return;
                    }

                    if(mech.containsItem(item)){
                        mech.updateItemNumber(item, number);
                    } else {
                        pair = new ItemNumberPair(item, number);
                        mech.addToInventory(pair);
                    }
                    message.setText("Bought " + item.getName() + "!");
                    moneyLeft.setText("Money: " + gameState.stats.getCash());
                    //If they don't have enough money, do nothing but notify them
                } else { message.setText("Not enough money!"); }
            }

        });

        leave = (Button) findViewById(R.id.leave_shop_button);
        leave.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ShopScreen.class);
                startActivity(intent);
            }
        });


        final View controlsView = findViewById(R.id.fullscreen_content_controls);

        // Set up an instance of SystemUiHider to control the system UI for
        // this activity.


        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
    }


    Handler mHideHandler = new Handler();

}
