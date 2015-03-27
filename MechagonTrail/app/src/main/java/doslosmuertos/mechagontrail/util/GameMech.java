package doslosmuertos.mechagontrail.util;

public class GameMech{

    private int head;
    private int larm;
    private int rarm;
    private int lleg;
    private int rleg;
    private int ammo;

    public GameMech(){
        head = 100;
        larm = 100;
        rarm = 100;
        lleg = 100;
        rleg = 100;
        ammo = 0;
    }

    public GameMech(int theAmmo){
        head = 100;
        larm = 100;
        rarm = 100;
        lleg = 100;
        rleg = 100;
        ammo = theAmmo;
    }

    public void setHead(int newHead){
        head = newHead;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public void setLarm(int larm) {
        this.larm = larm;
    }

    public void setLleg(int lleg) {
        this.lleg = lleg;
    }

    public void setRarm(int rarm) {
        this.rarm = rarm;
    }

    public void setRleg(int rleg) {
        this.rleg = rleg;
    }

    public int getAmmo() {
        return ammo;
    }

    public int getHead() {
        return head;
    }

    public int getLarm() {
        return larm;
    }
    public int getLleg() {
        return lleg;
    }

    public int getRarm() {
        return rarm;
    }

    public int getRleg() {
        return rleg;
    }
}