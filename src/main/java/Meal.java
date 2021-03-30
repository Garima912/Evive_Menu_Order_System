import java.util.ArrayList;
import java.util.HashMap;

//Parent class to all the three meal types: Breakfast, Lunch an Dinner classes
public class Meal {

    protected HashMap<String,Integer> frequencies = new HashMap<>();

    //returns a map with item IDs as key and their respective quantities ordered as value.
    protected HashMap<String, Integer> computeFrequencies(ArrayList<String> itemIds){

        for(String s: itemIds){
            if(!frequencies.containsKey(s)){
                frequencies.put(s,1);
                continue;
            }
            else{
                frequencies.put(s, frequencies.get(s)+1); //increase quantity of an item
            }
        }
        return  frequencies;
    }

    // checks if the input order has valid multiple main, sides, drinks or desert items
    protected boolean isValidMultiple(ArrayList<String> itemIds){
        return true;
    }

    //validates that the order has at least a main and a side dish
    protected boolean hasCompulsoryItems(ArrayList<String> itemIds){
        if(itemIds.contains("1") && itemIds.contains("2")){
            return true;
        }
        return false;
    }

    // validates that the order meets the menu rules using the above validation functions
    protected boolean validateOrderInput(ArrayList<String> itemIds){
        if( hasCompulsoryItems(itemIds) && isValidMultiple(itemIds)){
            return true;
        }
        return false;
    }

    // prints out the order if it is valid or else returns meaningful error messages
    public void makeOrder(ArrayList<String> itemIds){

    }

}
