public class FoodItem implements Order {
    private int quantity = 0;
    private String dishName;

    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }


    public FoodItem(String meal) {
        this.dishName = meal;
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
