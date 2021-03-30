import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main{

    private static String orderString;
    private static String mealName = "";
    private static ArrayList itemIDs = new ArrayList();

    public static void main(String[] args) {

        System.out.println("Welcome to Evive Restaurant !");

        while(true){
            Scanner sc = new Scanner(System.in);
            System.out.println("\nChoose a meal and its food items: ");

            orderString = sc.nextLine();
            validateInput();

            Meal meal = null;
                switch (mealName) {
                    case "Breakfast":
                        meal = new Breakfast(itemIDs);
                        meal.makeOrder(itemIDs);
                        break;
                    case "Lunch":
                        meal = new Lunch(itemIDs);
                        meal.makeOrder(itemIDs);
                        break;
                    case "Dinner":
                        meal = new Dinner(itemIDs);
                        meal.makeOrder(itemIDs);
                        break;
                    default:
                        System.out.println("Invalid meal type: please choose again");
                        break;
                }
            }
        }


    // checks if the input is in correct format, i.e meal name orderID_1, orderID_2..
    private static boolean validateInput() {
        String [] orderDetails = orderString.split(" ");
        if(orderDetails.length > 1){
            itemIDs = new ArrayList(Arrays.asList(orderDetails[1].split(",")));
            Collections.sort(itemIDs);
        }
        mealName = orderDetails[0];
        return true;
    }

}
