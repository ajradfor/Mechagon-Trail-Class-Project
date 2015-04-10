package doslosmuertos.mechagontrail.util;
import java.util.Random;

//GameEvent has methods that facilitate events happening in the game
public class GameEvent {

    //Picks a random event
    public static void randomEvent(GameState game){
        String description="";
        GameStats stats= game.stats;


        Random rand = new Random();
        int i = rand.nextInt(5);
        if(i==0){
            description=sick(pickCharacter(game));
        }
        if (i==1){
            description=Robbed(stats);
        }
        if(i==2){
            description=FindMoney(stats);
        }
        if(i==3){
            description=LooseFood(game);
        }
        if(i==4){
            description=FindFood(game);
        }
        game.eventText=description;
    }//end of the happen method

    //the target character gets space dysentery
    public static String sick(GameCharacter target){
         String s="Unfortunately " + target.getName() + " has gotten space dysentery";
         target.setDiseased(true);
         target.setDisease("Space Dysentery");
         return s;
    }   //end of sick method

    //this method returns a string detailing a run in with bandits who will steal up to 50 cash.  the model will be changed acordingly
    public static String Robbed(GameStats states){
        if(states.getCash()>=0){
            return "You run into some space bandits, but once they realize you have no money they leave you alone.";
        }//end of if you have no money
        String s="You get robbed by Space bandits. Oh No!  They steal ";
        Random rand = new Random();
        int steal = rand.nextInt(49)+1;
        if(steal>states.getCash()){
            s=s+ "all of your space cash";
            states.setCash(0);
        }else{
            s=s+steal+ " space dollars from you.";
            steal = states.getCash()- steal;
            states.setCash(steal);
        }
        return s;
    }//end of getting robbed

    //this method returns a string detailing the event where you loose up to 50 food.  the model will be changed acordingly
    public static String LooseFood(GameState states){
        GameMech mech = states.getMech();
        String s="You are visited by space pandas";
        if(mech.getFood()>=0){
            return s+ ".";
        }//end of if you have no food
        Random rand = new Random();
        int steal = rand.nextInt(49)+1;
        if(steal>mech.getFood()){
            s=s+ " and they eat all of your food.";
           mech.setFood(0);
        }else{
            s=s+" and they eat some of your food.";
            steal = mech.getFood()- steal;
           mech.setFood(steal);
        }
        return s;
    }//end of Loosing food.

    //An event where you find money
    public static String FindMoney(GameStats states){
        Random rand = new Random();
        int found = rand.nextInt(49)+1;
        String s="The inflation fairy shows up and gives you "+ found + " space dollars.";
        found=states.getCash()+found;
        return s;
    }// end of find method

    //An event where you find Food.
    public static String FindFood(GameState states){
        GameMech mech = states.getMech();
        Random rand = new Random();
        int found = rand.nextInt(49)+1;
        String s="Looks like it's your lucky day.  " + found+ " food rations materialize out of thin air.";
        found=mech.getFood()+found;
        return s;
    }// end of findfood method

    //This method returns a random character who's health is greater than 0.  Or returns the player character if everyone is dead
    public static GameCharacter pickCharacter(GameState game){
        GameCharacter target;
        // to avoid an infinite for loop we are checking that at least one member of the team is still alive
        if(game.playerCharacter.getHealth()<=0&&game.leftArm.getHealth()<=0&&game.rightArm.getHealth()<=0&&game.leftLeg.getHealth()<=0&&game.rightLeg.getHealth()<=0){
            return game.playerCharacter;
        }// end of if everyone is dead

        do {
            Random rand = new Random();
            int i = rand.nextInt(5);
            if (i == 0) {
                target = game.playerCharacter;
            } else if (i == 1) {
                target = game.leftArm;
            } else if (i == 2) {
                target = game.rightArm;
            } else if (i == 3) {
                target = game.leftLeg;
            } else {
                target = game.rightLeg;
            }
        }while(target.getHealth()<=0);
        return target;
    }//end of pick character method

}//end of game event class
