package doslosmuertos.mechagontrail;

import doslosmuertos.mechagontrail.util.GameState;
import doslosmuertos.mechagontrail.util.MechagonTrailApplication;
import doslosmuertos.mechagontrail.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class UpgradeMechActivity extends Activity {

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_upgrade_mech);

        GameState gamestate;
        MechagonTrailApplication app = (MechagonTrailApplication)getApplication();
        gamestate = app.getGameState();

        super.onCreate(savedInstanceState);

        Button upgradeHealth, upgradeDamage, back;
        final TextView message;
        message = (TextView) findViewById(R.id.upgrade_message);
        message.setText("Money in the bank: " + gamestate.stats.getCash());

        //Upgrade health button functionality
        upgradeHealth = (Button)findViewById(R.id.upgrade_health_button);
        upgradeHealth.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){

                GameState gamestate;
                MechagonTrailApplication app = (MechagonTrailApplication)getApplication();
                gamestate = app.getGameState();

                if(gamestate.stats.getCash() >= 200){

                    gamestate.stats.setCash(gamestate.stats.getCash() - 200);
                    gamestate.getMech().setMaxHealthAndHeal(gamestate.getMech().getMaxHealth() + 50);
                    message.setText("Health upgraded!\nMoney = " + gamestate.stats.getCash());

                } else

                    message.setText("Not enough money for that!\nMoney = " + gamestate.stats.getCash());

            }
        });

        //Upgrade damage button functionality
        upgradeDamage = (Button)findViewById(R.id.upgrade_damage_button);
        upgradeDamage.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                GameState gamestate;
                MechagonTrailApplication app = (MechagonTrailApplication)getApplication();
                gamestate = app.getGameState();

                if(gamestate.stats.getCash() >= 200){

                    gamestate.stats.setCash(gamestate.stats.getCash() - 200);
                    gamestate.stats.setDamage(gamestate.stats.getDamage() + 5);
                    message.setText("Damage upgraded!\nMoney = " + gamestate.stats.getCash());

                } else
                    message.setText("Not enough money for that!\nMoney = " + gamestate.stats.getCash());

            }
        });


        //Back button functionality
        back = (Button) findViewById(R.id.back_to_shop);
        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), ShopScreen.class);
                startActivity(intent);
            }
        });




        final View controlsView = findViewById(R.id.fullscreen_content_controls);


    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

    }

}
