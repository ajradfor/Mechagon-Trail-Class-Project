package doslosmuertos.mechagontrail.util;
import java.util.Random;

//GameEvent has methods that facilitate events happening in the game
public class GameEvent {

    //Picks a random event
    public static void randomEvent(GameState game){
        String description="";
        GameStats stats= game.stats;

        description=sick(pickCharacter(game));


        game.eventText=description;
    }//end of the happen meathod

    //the target character gets space dysentery
    public static String sick(GameCharacter target){
         String s="Unfortunatly " + target.getName() + " has gotten space dysentery";
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

    //An event where you find money
    public static String find(GameStats states){
        Random rand = new Random();
        int found = rand.nextInt(49)+1;
        found=states.getCash()+found;
        String s="The inflation fairy shows up and gives you "+ found + " space dollars.";
        return s;
    }// end of find method

    //This method returns a random character who's health is greater than 0.  Or returns the player character if everyone is dead
    public static GameCharacter pickCharacter(GameState game){
        GameCharacter target;
        // to avoid an infinet for loop we are checking that at least one member of the team is still alive
        if(game.playerCharacter.getHealth()<=0&&game.leftArm.getHealth()<=0&&game.rightArm.getHealth()<=0&&game.leftLeg.getHealth()<=0&&game.rightLeg.getHealth()<=0){
            return game.playerCharacter;
        }// end of if eaveryone is dead

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
    }//end of pick character meathod

}//end of game event class
