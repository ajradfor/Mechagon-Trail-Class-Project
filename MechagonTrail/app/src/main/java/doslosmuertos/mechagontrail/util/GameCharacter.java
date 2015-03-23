package doslosmuertos.mechagontrail.util;

import java.lang.String;

public class GameCharacter{
    private String name = "Default";
    private int health = 100;
    private double hunger = 0.0;
    private double fatigue = 0.0;
    private boolean diseased = false;
    private String disease = "healthy";

    public String getName(){
        return this.name;
    }
    public void setName(String newName){
        this.name = newName;
    }
    public int getHealth(){
        return this.health;
    }
    public void setHealth(int newHealth){
        this.health = newHealth;
    }
    public double getHunger(){
        return this.hunger;
    }
    public void setHunger(double newHunger){
        this.hunger = newHunger;
    }
    public double getFatigue(){
        return this.fatigue;
    }
    public void setFatigue(double newFatigue){
        this.fatigue = newFatigue;
    }
    public boolean getDiseased(){
        return this.diseased;
    }
    public void setDiseased(boolean isDiseased){
        this.diseased = isDiseased;
    }
    public String getDisease(){
        return this.disease;
    }
    public void setDisease(String newDisease) {
        this.disease = newDisease;
    }
}