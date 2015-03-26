package doslosmuertos.mechagontrail.util;

public class GameState{

    public GameCharacter playerCharacter;
    public GameCharacter leftArm;
    public GameCharacter rightArm;
    public GameCharacter leftLeg;
    public GameCharacter rightLeg;

    private double pace;
    private double meals;

    public GameStats stats;

    public GameState(){
        playerCharacter = new GameCharacter();
        leftArm = new GameCharacter();
        rightArm = new GameCharacter();
        leftLeg = new GameCharacter();
        rightLeg = new GameCharacter();

        pace = 1.0;
        meals = 1.0;

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
}