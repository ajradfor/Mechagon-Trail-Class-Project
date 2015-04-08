package doslosmuertos.mechagontrail.util;

import java.util.ArrayList;

public class GameMech {

    private int head;
    private int lArm;
    private int rArm;
    private int lLeg;
    private int rLeg;
    private int ammo;
    private ArrayList<Item> inventory = new ArrayList<Item>();

    public GameMech() {
        head = 100;
        lArm = 100;
        rArm = 100;
        lLeg = 100;
        rLeg = 100;
        ammo = 0;
    }

    public GameMech(int theAmmo) {
        head = 100;
        lArm = 100;
        rArm = 100;
        lLeg = 100;
        rLeg = 100;
        ammo = theAmmo;
    }

    public int doDamage(int seed) {
        if (seed == 1) { return 5; }
        else if (seed >= 2 && seed <= 3) { return 8; }
        else if (seed >= 4 && seed <= 9) { return 10; }
        else if (seed >= 10 && seed <= 11) { return 12; }
        else if (seed == 12) { return 12; }
        else { return 10; }
    }

    public void setHead(int newHead){
        head = newHead;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public void setlArm(int lArm) {
        this.lArm = lArm;
    }

    public void setlLeg(int lLeg) {
        this.lLeg = lLeg;
    }

    public void setrArm(int rArm) {
        this.rArm = rArm;
    }

    public void setrLeg(int rLeg) {
        this.rLeg = rLeg;
    }

    public int getAmmo() {
        return ammo;
    }

    public int getHead() {
        return head;
    }

    public int getlArm() {
        return lArm;
    }

    public int getlLeg() {
        return lLeg;
    }

    public int getrArm() {
        return rArm;
    }

    public int getrLeg() {
        return rLeg;
    }

    public ArrayList<Item> getInventory(){ return inventory; }

    public void addToInventory(Item item) { inventory.add(item); }

    public int getInventorySize(){ return inventory.size(); }

    public void setInventory(ArrayList<Item> newInventory) { inventory = newInventory; }
}