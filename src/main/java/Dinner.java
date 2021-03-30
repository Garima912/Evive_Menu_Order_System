import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Dinner extends Meal{
    private ArrayList<Order> orders;  // contains objects of each FoodItem that has been ordered
    private ArrayList<String> orderItems;
    private HashMap<String, String> compulsory; // contains Dinner compulsory Menu items mapped with their respective names

    public Dinner(ArrayList<String> orderItems) {
        this.orderItems = orderItems;
        compulsory = new HashMap<>();
        compulsory.put("1","Main");
        compulsory.put("2", "Side");
        compulsory.put("4","Dessert");
    }

    @Override
    protected boolean isValidMultiple(ArrayList<String> itemIds) {
        frequencies = computeFrequencies(orderItems);   // map each ItemId with its quantities

        for(Map.Entry<String,Integer> item: frequencies.entrySet()){//traverse in the map to look for keys having >1 values
            if(item.getValue() > 1){
                return false;  // no multiple items are allowed in Dinner
            }
        }
        return true;
    }

    // This method is used to print out any error messages when user orders multiple items
    private void printInvalidMultiples(){
        String [] allItems = {"Main","Side","Drink","Dessert"};

        for(Map.Entry<String,Integer> item: frequencies.entrySet()){
            if(item.getValue() > 1){
                System.out.print(allItems[Integer.valueOf(item.getKey())-1] + " cannot be ordered more than once ");
            }
        }
    }

    //This method is used to print out any error messages when user doesn't order a compulsory item: a main ,side and dessert dish
    private void printMissingItems(){
        for(Map.Entry<String,String> itemID: compulsory.entrySet()){
            if(!orderItems.contains(itemID.getKey())){
                System.out.print(itemID.getValue() + " is missing,");
            }
        }

    }

    // Dinner has a different implementation of this method from Lunch and Breakfast since dessert is compulsory as well
    @Override
    protected boolean hasCompulsoryItems(ArrayList<String> itemIds) {

        for(Map.Entry<String,String> itemID: compulsory.entrySet()){  // traverse through the compulsory items map
            if(!itemIds.contains(itemID.getKey())){  //the ordered item Ids does not contain the compulsory item Id
                return false;
            }
        }
        return true;
    }

    //Processes and prints the order if valid
    @Override
    public void makeOrder(ArrayList<String> itemIds) {

        orders = new ArrayList<>();
        //create objects of the three food items being offered in Lunch meal
        FoodItem mainDish = new FoodItem("Steak");
        FoodItem sideDish =  new FoodItem("Potatoes");
        FoodItem drinks = new FoodItem("Wine");
        FoodItem desert = new FoodItem("Cake");
        FoodItem water = new FoodItem("Water");  // create a seperate object for water since it will be added to the order by default

        if(validateOrderInput(itemIds)){   // order is valid: follows all menu rules
            for(String item: itemIds){    // traverse through the list of ordered IDs
                switch (item){
                    case "1":
                        orders.add(mainDish);  // add main dish to the order list
                        break;
                    case "2":
                        orders.add(sideDish);    // add side dish to the order list
                        break;
                    case "3":
                        orders.add(drinks);          // add drink and water to the order list
                        orders.add(water);
                        break;
                    case "4":
                        if(!orders.contains(drinks)){  // Add water to the order, even if no drink is ordered
                            orders.add(water);
                        }
                        orders.add(desert);   // add the dessert to the order
                        break;
                }
            }
            System.out.println(printOrder());
        }
        else{                                 // deal with invalid cases: invalid multiples and missing out must-have items for order
            System.out.print("Unable to process: ");
            if(!hasCompulsoryItems(orderItems)){
                printMissingItems();
            }
            if(!isValidMultiple(orderItems)){
                printInvalidMultiples();
            }
        }

    }

    //This method comes in most use when trying to test the returned order
    public String printOrder(){
        String order = "";
        for (int i=0; i<orders.size(); i++) {
            order+= orders.get(i).getDishName();
            if (i!=orders.size()-1){
                order+= ",";
            }
        }
        return order;
    }
}
