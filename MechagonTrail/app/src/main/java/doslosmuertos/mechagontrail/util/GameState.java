package doslosmuertos.mechagontrail;

public class GameState{

    public GameCharacter playerCharacter;
    public GameCharacter character2;
    public GameCharacter character3;
    public GameCharacter character4;
    public GameCharacter character5;

    private double pace;
    private double meals;

    public GamesStats stats;

    public GameState(){
        playerCharacter = new GameCharacter();
        character2 = new GameCharacter();
        character3 = new GameCharacter();
        character4 = new GameCharacter();
        character5 = new GameCharacter();

        pace = 1.0;
        meals = 1.0;

        stats  = new GameStats();
    }

    private void pickProfession(){

    }


        }