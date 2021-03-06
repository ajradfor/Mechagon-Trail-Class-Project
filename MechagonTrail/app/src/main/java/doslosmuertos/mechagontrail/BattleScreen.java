package doslosmuertos.mechagontrail;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.os.Handler;

import doslosmuertos.mechagontrail.util.GameBattle;
import doslosmuertos.mechagontrail.util.GameEnemy;
import doslosmuertos.mechagontrail.util.GameMech;
import doslosmuertos.mechagontrail.util.MechagonTrailApplication;


public class BattleScreen extends ActionBarActivity {

    Button runButton, itemButton, attackButton, backButton;
    TextView mechHp, enemyHp, winLose, ammo;
    GameMech mech;
    GameEnemy enemy;
    MechagonTrailApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_screen);
        app = (MechagonTrailApplication)getApplication();
        runButton = (Button)findViewById(R.id.runButton);
        itemButton = (Button)findViewById(R.id.useItemButton);
        attackButton = (Button)findViewById(R.id.attackButton);
        backButton = (Button)findViewById(R.id.backToTravelButton);
        mechHp = (TextView)findViewById(R.id.mechHealth);
        enemyHp = (TextView)findViewById(R.id.enemyHealth);
        winLose = (TextView)findViewById(R.id.winLose);
        ammo = (TextView)findViewById(R.id.ammoText);
        mech = app.getGameState().getMech();
        enemy = new GameEnemy();

        mechHp.setText(mech.getHealth() + " / " + mech.getMaxHealth());
        enemyHp.setText(enemy.getHp() + " / 50");
        ammo.setText(String.valueOf(mech.getAmmo()));
        final int chance = enemy.getChance();

        final GameBattle batt = new GameBattle(mech, enemy, app.getGameState().stats);

        backButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GameLoop.class);
                startActivity(intent);
            }
        });

        attackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                batt.exchangeBlows();

                enemyHp.setText(enemy.getHp() + " / 50");
                mechHp.setText(mech.getHealth() + " / " + mech.getMaxHealth());
                ammo.setText(String.valueOf(mech.getAmmo()));

                if (batt.isOver()) {
                    if (batt.isWon()) {
                        winLose.setText("You win!");
                        app.getGameState().stats.obtainCash(50);
                        backButton.setClickable(true);
                        backButton.setVisibility(View.VISIBLE);
                    }
                    else {
                        winLose.setText("You lose!");
                    }
                }

            }
        });

        runButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean ran = batt.runaway(chance);
                if(ran){
                    winLose.setText("You coward!");
                } else {
                    winLose.setText("Nice try...");
                    batt.getHit();
                }


            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_battle_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
