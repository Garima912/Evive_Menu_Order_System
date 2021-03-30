import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Lunch extends Meal{

    private ArrayList<Order> orders;  // contains objects of each FoodItem that has been ordered
    private ArrayList<String> orderItems;
    private HashMap<String, String> compulsory; // contains Lunch compulsory Menu items mapped with their respective names
    private  HashMap<String,Integer> frequencies;

    public Lunch(ArrayList<String> orderItems) {
        this.orderItems =orderItems;
        compulsory = new HashMap<>();
        compulsory.put("1","Main");
        compulsory.put("2", "Side");
    }

    @Override
    protected boolean isValidMultiple(ArrayList<String> itemIds) {
        frequencies = computeFrequencies(orderItems);  // map each ItemId with its quantities
        for(Map.Entry<String,Integer> item: frequencies.entrySet()){   ////traverse in the map to look for keys except Side dish having >1 values
            if( !item.getKey().equals("2") && item.getValue() > 1){
                return false;  // invalid multiple found
            }
        }
        return true;   // all quantities in the order are valid
    }

    //checks whether an item has already been added to the final order
    boolean checkDuplicate(Order newOrder){
        for (Order order: orders) {
            if (newOrder.equals(order)){
                return true;
            }
        }
        return  false;
    }

    // This method is used to print out any error messages when user orders multiple items except Side dish
    private void printInvalidMultiples(){
        String [] allItems = {"Main","Side","Drink"};

        for(Map.Entry<String,Integer> item: frequencies.entrySet()){
            if( !item.getKey().equals("2") && item.getValue() > 1){
                System.out.print(allItems[Integer.valueOf(item.getKey())-1] + " cannot be ordered more than once, ");
            }
        }

    }

    //This method is used to print out any error messages when user doesn't order a compulsory item: a main and side dish
    private void printMissingItems(){
        for(Map.Entry<String,String> itemID: compulsory.entrySet()){
            if(!orderItems.contains(itemID.getKey())){
                System.out.print(itemID.getValue() + " is missing,");
            }
        }
    }

    //Processes and prints the order if valid
    @Override
    public void makeOrder(ArrayList<String> itemIds) {
        orders = new ArrayList<>();
        //create objects of the three food items being offered in Lunch meal
        FoodItem mainDish = new FoodItem("Salad");
        FoodItem sideDish =  new FoodItem("Chips");
        FoodItem drinks = new FoodItem("Water");   //default drink is water, in case no drink was explicitly ordered

        if(validateOrderInput(itemIds)){   // order is valid: follows all menu rules
            for(String item: itemIds){    // traverse through the list of ordered IDs
                switch (item){
                    case "1":
                        orders.add(mainDish);   // add main dish to the order
                        break;
                    case "2":
                        if(!checkDuplicate(sideDish)){   //Sides can be ordered multiple times, so just add once.
                            orders.add(sideDish);
                        }
                        orders.get(1).setQuantity(orders.get(1).getQuantity()+1);  // increment quantity if already added to the order
                        break;
                    case "3":
                        drinks =  new FoodItem("Soda");   //create a FoodItem object for Soda drink
                        orders.add(drinks);          // add the drink in the orders
                        break;
                }
            }
            if(!orders.contains(drinks)){    // no drink was ordered then add water to the order
                orders.add(drinks);
            }
            System.out.println(printOrder());
        }
        else{                                // deal with invalid cases: invalid multiples and missing out must-have items for order
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
