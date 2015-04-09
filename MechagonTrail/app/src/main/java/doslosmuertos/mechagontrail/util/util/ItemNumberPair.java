package doslosmuertos.mechagontrail.util.util;

import doslosmuertos.mechagontrail.util.Item;

/**
 * Created by brian on 4/8/15.
 *
 * This was just a way for me to keep track of how much of an item you have.
 * constructor takes an Item - The item, and an int - The number of that item that you have
 */
public class ItemNumberPair {

    /**
     *
     * @param newItem - The item to be put in the pair
     * @param newNumber - the number of that item that you have
     */
    public ItemNumberPair(Item newItem, int newNumber){
        item = newItem;
        number = newNumber;
    }

    private Item item;
    private int number;

    public int getNumber(){ return number; }

    public void setNumber(int newNumber){ number = newNumber; }

    public Item getItem(){ return item; }

    public void setItem(Item newItem){ item = newItem; }

}
