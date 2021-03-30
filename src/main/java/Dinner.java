import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Dinner extends Meal{
    private ArrayList<Order> orders;
    private ArrayList<String> orderItems;
    private HashMap<String, String> compulsory;

    public Dinner(ArrayList<String> orderItems) {
        this.orderItems = orderItems;
        compulsory = new HashMap<>();
        compulsory.put("1","Main");
        compulsory.put("2", "Side");
        compulsory.put("4","Desert");
//        frequencies = computeFrequencies(orderItems);

    }

    @Override
    protected boolean isValidMultiple(ArrayList<String> itemIds) {
        frequencies = computeFrequencies(orderItems);
        for(Map.Entry<String,Integer> item: frequencies.entrySet()){
            if(item.getValue() > 1){
                return false;
            }
        }
        return true;
    }

    private void printInvalidMultiples(){
        String [] allItems = {"Main","Side","Drink","Desert"};

        for(Map.Entry<String,Integer> item: frequencies.entrySet()){
            if(item.getValue() > 1){
                System.out.print(allItems[Integer.valueOf(item.getKey())-1] + " cannot be ordered more than once ");
            }
        }
    }

    private void printMissingItems(){
        for(Map.Entry<String,String> itemID: compulsory.entrySet()){
            if(!orderItems.contains(itemID.getKey())){
                System.out.print(itemID.getValue() + " is missing,");
            }
        }

    }

    @Override
    protected boolean hasCompulsoryItems(ArrayList<String> itemIds) {

        for(Map.Entry<String,String> itemID: compulsory.entrySet()){
            if(!itemIds.contains(itemID.getKey())){
                return false;
            }
        }
        return true;
    }

    @Override
    public void makeOrder(ArrayList<String> itemIds) {

        orders = new ArrayList<>();
        FoodItem mainDish = new FoodItem("Steak");
        FoodItem sideDish =  new FoodItem("Potatoes");
        FoodItem drinks = new FoodItem("Wine");
        FoodItem desert = new FoodItem("Cake");
        FoodItem water = new FoodItem("Water");

        if(validateOrderInput(itemIds)){
            for(String item: itemIds){
                switch (item){
                    case "1":
                        orders.add(mainDish);
                        break;
                    case "2":
                        orders.add(sideDish);
                        break;
                    case "3":
                        orders.add(drinks);          // add side dish in the orders
                        orders.add(water);
                        break;
                    case "4":
                        if(!orders.contains(drinks)){
                            orders.add(water);
                        }
                        orders.add(desert);
                        break;
                }
            }
            printOrder();
        }
        else{
            System.out.print("Unable to process: ");
            if(!hasCompulsoryItems(orderItems)){
                printMissingItems();
            }
            if(!isValidMultiple(orderItems)){
                printInvalidMultiples();
            }
        }

    }
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
