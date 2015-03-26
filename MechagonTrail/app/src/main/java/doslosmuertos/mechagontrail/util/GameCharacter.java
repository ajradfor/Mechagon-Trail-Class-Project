package doslosmuertos.mechagontrail.util;

import java.lang.String;

public class GameCharacter {
    private String name = "Default";
    private int health = 100;
    private double hunger = 0.0;
    private double fatigue = 0.0;
    private boolean diseased = false;
    private String disease = "healthy";

    public String getName(){
        return name;
    }
    public void setName(String newName){
        name = newName;
    }
    public int getHealth(){
        return health;
    }
    public void setHealth(int newHealth){
        health = newHealth;
    }
    public double getHunger(){
        return hunger;
    }
    public void setHunger(double newHunger){
        hunger = newHunger;
    }
    public double getFatigue(){
        return fatigue;
    }
    public void setFatigue(double newFatigue){
        fatigue = newFatigue;
    }
    public boolean getDiseased(){
        return diseased;
    }
    public void setDiseased(boolean isDiseased){
        diseased = isDiseased;
    }
    public String getDisease(){
        return disease;
    }

    public void setDisease(String newDisease) {
        disease = newDisease;
    }
}