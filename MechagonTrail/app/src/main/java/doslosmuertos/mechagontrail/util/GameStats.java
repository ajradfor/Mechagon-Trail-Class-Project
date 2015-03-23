package doslosmuertos.mechagontrail.util;


public class GameStats {

    private int cash = 500;
    private double repairChance = 1.0;
    private double healChance = 1.0;
    private double hitChance = 1.0;
    private int damage = 5;
    private double endScoreBonus = 1.0;
    private String profession = "default";

    public int getCash(){
        return cash;
    }
    public double getRepairChance(){
        return repairChance;
    }
    public double getHealChance(){
        return healChance;
    }
    public double getHitChance(){
        return hitChance;
    }
    public int getDamage(){
        return damage;
    }
    public double getScoreBonus(){
        return endScoreBonus;
    }
    public String getProfession(){
        return profession;
    }
    public void setCash(int newCash){
        cash = newCash;
    }
    public void setRepairChance(double newRepairChance){
        repairChance = newRepairChance;
    }
    public void setHealChance(double newHealChance){
        healChance = newHealChance;
    }
    public void setHitChance(double newHitChance){
        hitChance = newHitChance;
    }
    public void setDamage(int newDamage){
        damage = newDamage;
    }
    public void setScoreBonus(double newScoreBonus) {
        endScoreBonus = newScoreBonus;
    }
    public void setProfession(String newProfession) {
        profession = newProfession;
    }
}