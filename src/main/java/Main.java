import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main{

    private static String orderString;
    private static String mealName = "";  //Breakfast, Lunch or Dinner
    private static ArrayList itemIDs = new ArrayList();

    public static void main(String[] args) {

        System.out.println("Welcome to Evive Restaurant !");

        while(true){
            Scanner sc = new Scanner(System.in);
            System.out.println("\nChoose a meal and its food items: ");
            orderString = sc.nextLine();
            processInput();  // separate out the meal name and the list of food item IDs

            Meal meal = null;
                switch (mealName) {
                    case "Breakfast":
                        meal = new Breakfast(itemIDs); // create a Breakfast meal type object
                        meal.makeOrder(itemIDs);      // process and prints the order
                        break;
                    case "Lunch":
                        meal = new Lunch(itemIDs);  // create a Lunch meal type object
                        meal.makeOrder(itemIDs);   // process and prints the order
                        break;
                    case "Dinner":
                        meal = new Dinner(itemIDs);  // create a Dinner meal type object
                        meal.makeOrder(itemIDs);   // process and prints the order
                        break;
                    default:
                        System.out.println("Invalid meal type: please choose again");
                        break;
                }
            }
        }


    //separates out the meal type name and the list of item IDs from the user input
    private static boolean processInput() {
        String [] orderDetails = orderString.split(" ");
        if(orderDetails.length > 1){
            itemIDs = new ArrayList(Arrays.asList(orderDetails[1].split(",")));
            Collections.sort(itemIDs);
        }
        mealName = orderDetails[0];
        return true;
    }

}
