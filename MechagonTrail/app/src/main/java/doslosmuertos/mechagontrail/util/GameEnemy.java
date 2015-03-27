package doslosmuertos.mechagontrail.util;

public class GameEnemy{

    private int hp;
    private int damage;

    public GameEnemy(){
        hp = 50;
        damage = 5;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getDamage() {
        return damage;
    }

    public int getHp() {
        return hp;
    }
}