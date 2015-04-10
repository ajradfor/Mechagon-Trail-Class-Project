package doslosmuertos.mechagontrail.util;

import java.util.ArrayList;
import java.util.Random;

import doslosmuertos.mechagontrail.util.util.ItemNumberPair;

public class GameMech {
    private int head;
    private int lArm;
    private int rArm;
    private int lLeg;
    private int rLeg;
    private int health;
    private int ammo;
    private int food;
    private int fuel;
    private int maxHealth;
    private ArrayList<ItemNumberPair> inventory;

    public GameMech() {
        head = 100;
        lArm = 100;
        rArm = 100;
        lLeg = 100;
        rLeg = 100;
        ammo = 0;
        food = 400;
        fuel = 500;
        health = 500;
        inventory = new ArrayList<ItemNumberPair>();
        maxHealth = 500;
    }

    public GameMech(int theAmmo) {
        head = 100;
        lArm = 100;
        rArm = 100;
        lLeg = 100;
        rLeg = 100;
        ammo = theAmmo;
        food = 400;
        fuel = 500;
        health = 500;
        maxHealth = 500;
        inventory = new ArrayList<ItemNumberPair>();
    }

    public int doDamage(int seed, int base) {
        if (seed == 1) { return base - 5; }
        else if (seed >= 2 && seed <= 3) { return base - 2; }
        else if (seed >= 4 && seed <= 9) { return base; }
        else if (seed >= 10 && seed <= 11) { return base + 2; }
        else if (seed == 12) { return base + 5; }
        else { return base; }
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

    public void setFood(int food) { this.food = food; }

    public void setFuel(int fuel) { this.fuel = fuel; }

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

    public int getFood() { return food; }

    public int getFuel() { return fuel; }

    public ArrayList<ItemNumberPair> getInventory(){ return inventory; }

    public void addToInventory(ItemNumberPair item) { inventory.add(item); }

    public int getInventorySize(){ return inventory.size(); }

    public void setInventory(ArrayList<ItemNumberPair> newInventory) { inventory = newInventory; }

    public void obtainFood(int food) { this.food += food; }

    public void obtainFuel(int fuel) { this.fuel += fuel; }

    public void obtainAmmo(int ammo) { this.ammo += ammo; }

    public void useFood(int food) { this.food -= food; }

    public void useFuel(int fuel) { this.fuel -= fuel; }

    public void useAmmo(int ammo) { this.ammo -= ammo; }

    public int getHealth() { return this.health; }

    public int getMaxHealth(){ return maxHealth; }

    public void setMaxHealthAndHeal(int newMax){

        maxHealth = newMax;

        this.setHead(newMax / 5);
        this.setlArm(newMax / 5);
        this.setrArm(newMax / 5);
        this.setlLeg(newMax / 5);
        this.setrLeg(newMax / 5);

        this.updateHealth();

    }

    public void setMaxHealth(int newMax){ maxHealth = newMax; }

    public void healEveryone(int health){
        if(head > 0){
            head += health;
        }
        if(lArm > 0){
            lArm += health;
        }
        if(rArm > 0){
            rArm += health;
        }
        if(lLeg > 0){
            lLeg += health;
        }
        if(rLeg > 0){
            rLeg += health;
        }
        this.updateHealth();
    }

    public void takeDamage(int dmg) {
        Random rd = new Random();
        int limb = rd.nextInt(4);

        switch (limb) {
            case 0:
                this.head -= dmg;
                break;
            case 1:
                this.lArm -= dmg;
                break;
            case 2:
                this.rArm -= dmg;
                break;
            case 3:
                this.lLeg -= dmg;
                break;
            case 4:
                this.rLeg -= dmg;
                break;
            default:
                break;
        }
        this.updateHealth();
    }

    public boolean containsItem(Item item){
        for(int i = 0; i < inventory.size(); i++){
            if(inventory.get(i).getItem().getName().equals(item.getName())){
                return true;
            }
        } return false;
    }

    public void updateItemNumber(Item item, int num){
        for(int i = 0; i < inventory.size(); i++){
            if(inventory.get(i).getItem().getName().equals(item.getName())){
                inventory.get(i).setNumber(num);
                break;
            }
        }
    }

    public void addFood(int newFood){ food += newFood; }

    public void updateHealth() {
        this.health = this.head + this.lArm + this.rArm + this.lLeg + this.lArm;
    }
}