package doslosmuertos.mechagontrail;

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

    Button runButton;
    Button itemButton;
    Button attackButton;
    TextView mechHp;
    TextView enemyHp;
    TextView winLose;
    GameMech mech;
    GameEnemy enemy;
    MechagonTrailApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_battle_screen);
        app = (MechagonTrailApplication)getApplication();
        runButton = (Button)findViewById(R.id.runButton);
        itemButton = (Button)findViewById(R.id.useItemButton);
        attackButton = (Button)findViewById(R.id.attackButton);
        mechHp = (TextView)findViewById(R.id.mechHealth);
        enemyHp = (TextView)findViewById(R.id.enemyHealth);
        winLose = (TextView)findViewById(R.id.winLose);
        mech = app.getGameState().getMech();
        enemy = new GameEnemy();

        mechHp.setText(mech.getHead() + " / 100");
        enemyHp.setText(enemy.getHp() + " / 50");
        final int chance = enemy.getChance();

        final GameBattle batt = new GameBattle(mech, enemy, app.getGameState().stats);

        attackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                batt.exchangeBlows();

                enemyHp.setText(enemy.getHp() + " / 50");
                mechHp.setText(mech.getHealth() + " / 500");

                if (batt.isOver()) {
                    if (batt.isWon()) {
                        winLose.setText("You win!");
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
