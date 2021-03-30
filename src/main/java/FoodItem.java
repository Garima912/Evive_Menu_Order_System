
//This class will create objects of each main, side, drink and dessert dishes ordered in any meal type from the menu
public class FoodItem implements Order {
    private int quantity = 0;  //keep track of its quantity
    private String dishName;   //name of the food dish ordered

    public FoodItem(String meal) {
        this.dishName = meal;
    }

    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    public String getDishName() {
        if (quantity <=1){
            return dishName;
        }
        else{
            return dishName+"("+ quantity+")";
        }
    }

}
