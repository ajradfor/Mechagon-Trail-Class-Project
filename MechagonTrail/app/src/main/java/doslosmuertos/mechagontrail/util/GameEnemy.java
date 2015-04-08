package doslosmuertos.mechagontrail.util;

public class GameEnemy {

    private int hp;
    private int damage;
    private int chance;

    public GameEnemy(){
        hp = 50;
        damage = 5;
        chance = 50;
    }

    /**
     * A more useful constructor, should probably be expanded upon
     * @param HP - the hp of the enemy
     * @param dam - the damage that the enemy should deal
     * @param c - the chance of running away from the enemy 0 - 100
     */
    public GameEnemy(int HP, int dam, int c){
        hp = HP;
        damage = dam;
        chance = c;
    }

    /**
     * Will's idea for a more varied damage system.
     * @param seed
     * @return
     */
    public int doDamage(int seed) {
        if (seed >= 1 && seed <= 2) { return 1; }
        else if (seed >= 3 && seed <= 5) { return 3; }
        else if (seed >= 6 && seed <= 11) { return 5; }
        else if (seed == 12) { return 9; }
        else { return 5; }
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setHp(int hp) {
        this.hp = hp;
        if (hp < 0) { this.hp = 0; }
    }

    public int getDamage() {
        return damage;
    }

    public int getHp() {
        return hp;
    }

    public int getChance() { return chance; }
}