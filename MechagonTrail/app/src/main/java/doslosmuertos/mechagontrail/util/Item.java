package doslosmuertos.mechagontrail.util;

/**
 * Created by Brian on 4/8/2015.
 * This will be a super class for all inventory items, just made this so I can
 * make the inventory items because I can't sleep
 */
public class Item {

    //Name is just a way to reference what it is in text
    private String name;
    private int cost;

    public String getName(){ return name; }

    public void setName(String newName){ name = newName; }

    public int getCost(){ return cost; }

    public void setCost(int newCost){ cost = newCost; }

}
