package doslosmuertos.mechagontrail.util;

import java.util.ArrayList;

import doslosmuertos.mechagontrail.util.util.ItemNumberPair;

public class GameState {

    public GameCharacter playerCharacter;
    public GameCharacter leftArm;
    public GameCharacter rightArm;
    public GameCharacter leftLeg;
    public GameCharacter rightLeg;

    public String eventText;

    private GameMech mech;

    private int pace;
    private int meals;
    private int day;
    private double distance;

    public GameStats stats;

    public GameState(){
        playerCharacter = new GameCharacter();
        leftArm = new GameCharacter();
        rightArm = new GameCharacter();
        leftLeg = new GameCharacter();
        rightLeg = new GameCharacter();

        mech = new GameMech(100);

        pace = 2;
        meals = 2;
        day = 0;
        distance = 0.0;

        stats  = new GameStats();

    }

    /*
    0 = Pilot
    1 = Mechanic
    2 = Doctor
    3 = Banker
    4 = Hobo
     */
    private void pickProfession(int job){

        if (job == 0) {
            this.stats.setProfession("Pilot");
            this.stats.setCash(250);
            this.stats.setRepairChance(1.25);
            this.stats.setHealChance(0.75);
            this.stats.setHitChance(2);
            this.stats.setDamage(6);
            this.stats.setScoreBonus(1.0);
        }
        else if(job == 1) {
            this.stats.setProfession("Mechanic");
            this.stats.setCash(500);
            this.stats.setRepairChance(2);
            this.stats.setHealChance(0.75);
            this.stats.setHitChance(1.25);
            this.stats.setDamage(5);
            this.stats.setScoreBonus(0.5);
        }
        else if(job == 2) {
            this.stats.setProfession("Doctor");
            this.stats.setCash(750);
            this.stats.setRepairChance(.75);
            this.stats.setHealChance(2);
            this.stats.setHitChance(1.0);
            this.stats.setDamage(5);
            this.stats.setScoreBonus(0.5);
        }
        else if(job == 3) {
            this.stats.setProfession("Banker");
            this.stats.setCash(1000);
            this.stats.setRepairChance(.75);
            this.stats.setHealChance(0.75);
            this.stats.setHitChance(1.0);
            this.stats.setDamage(5);
            this.stats.setScoreBonus(0.5);
        }
        else if(job == 4) {
            this.stats.setProfession("Hobo");
            this.stats.setCash(100);
            this.stats.setRepairChance(.75);
            this.stats.setHealChance(0.75);
            this.stats.setHitChance(.75);
            this.stats.setDamage(4);
            this.stats.setScoreBonus(2);
        }
    }

    public int advanceDay() { return day++; }

    public int getPace(){
        return pace;
    }

    public int getMeals(){
        return meals;
    }

    public int getDay() { return day; }

    public double getDistance() { return distance; }

    public void setPace(int newPace){
        pace = newPace;
    }

    public void setMeals(int newMeals){
        meals = newMeals;
    }

    public void setDay(int newDay) { day = newDay; }

    public void setDistance(double newDistance) { distance = newDistance; }

    public void increaseDistance(double add) { distance += add; }

    public void increaseDay(int add) { day += add; }

    public GameMech getMech() { return mech; }
}