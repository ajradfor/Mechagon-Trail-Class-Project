
package doslosmuertos.mechagontrail.util;

import java.util.Random;

public class GameBattle {
    private GameMech mech;
    private GameEnemy enemy;
    private boolean victory;
    private boolean over;

    public GameBattle(GameMech theMech) {
        mech = theMech;
        this.victory = false;
        this.over = false;
        battle();
    }

    public GameBattle(GameMech theMech, GameEnemy theEnemy) {
        this.mech = theMech;
        this.enemy = theEnemy;
        this.victory = false;
    }

    public void exchangeBlows() {
        Random random = new Random();
        int low = 1;
        int mechHi = 13;
        int enemyHi = 12;
        int mechSeed = random.nextInt(mechHi - low) + low;
        int enemySeed = random.nextInt(enemyHi - low) + low;
        int mechDamage = mech.doDamage(mechSeed);
        int enemyDamage = enemy.doDamage(enemySeed);

        this.enemy.setHp(this.enemy.getHp() - mechDamage);

        if (this.enemy.getHp() == 0) {
            this.over = true;
            this.victory = true;
            return;
        }

        this.mech.setHead(this.mech.getHead() - enemyDamage);

        if (this.mech.getHead() == 0) {
            this.over = true;
            this.victory = false;
            return;
        }

    }

    private void battle() {
        boolean runAway = false;
        boolean victory = true;
        while(enemy.getHp() > 0){
            if (runAway){
                victory = false;
                break;
            }
            //ToDo

        }

        if (victory){
            //ToDo

        }
    }

    public boolean isOver() { return over; }

    public boolean isWon() { return victory; }
}