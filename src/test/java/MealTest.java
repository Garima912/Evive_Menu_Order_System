import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

public class MealTest {
    public Meal mealType;
    public String[] itemIds;

    @Test
    public void computeFrequencies_Test(){
        itemIds = new String[]{"1","2","3","3","3"};
        HashMap<String,Integer> myFreqMap = new HashMap<>();
        myFreqMap.put("1",1);
        myFreqMap.put("2",1);
        myFreqMap.put("3",3);
        ArrayList<String> inputIds = new ArrayList<String>(Arrays.asList(itemIds));
        mealType = new Lunch(inputIds);
        assertEquals(myFreqMap, mealType.computeFrequencies(inputIds),"Order's food item frequencies do not match");
    }

    // tests the output of a simple breakfast order
    @Test
    public void breakfast_oneTest(){
        itemIds = new String[]{"1","2","3"};
        ArrayList<String> inputIds = new ArrayList<String>(Arrays.asList(itemIds));
        Breakfast mealType = new Breakfast(inputIds);
        mealType.makeOrder(inputIds);
        assertEquals("Eggs,Toast,Coffee", mealType.printOrder(),"Incorrect breakfast order");

    }

    @Test
    public void multipleItemsBreakfast_Test(){
        itemIds= new String[]{"1","2","3","3","3","3"};
        ArrayList<String> inputIds = new ArrayList<String>(Arrays.asList(itemIds));
        Breakfast mealType = new Breakfast(inputIds);
        mealType.makeOrder(inputIds);
        assertEquals("Eggs,Toast,Coffee(4)", mealType.printOrder(),"Incorrect output when ordered multiple items");
    }

    @Test
    public void multipleItemsBreakfast_2Test(){
        itemIds= new String[]{"1","2","2","2","2","3"};
        ArrayList<String> inputIds = new ArrayList<String>(Arrays.asList(itemIds));
        mealType = new Breakfast(inputIds);
        assertEquals(false, mealType.isValidMultiple(inputIds),"Incorrect output when ordered multiple items");
    }

    @Test
    public void multipleItemsLunch_Test(){
        itemIds= new String[]{"1","2","2","2","2","3"};
        ArrayList<String> inputIds = new ArrayList<String>(Arrays.asList(itemIds));
        mealType = new Lunch(inputIds);
        assertEquals(true, mealType.isValidMultiple(inputIds),"Incorrect output when ordered multiple items");
    }

    @Test
    public void multipleItemsLunch_twoTest(){
        itemIds= new String[]{"1", "1", "1,","2","2","3"};
        ArrayList<String> inputIds = new ArrayList<String>(Arrays.asList(itemIds));
        mealType = new Lunch(inputIds);
        assertEquals(false, mealType.isValidMultiple(inputIds),"Incorrect output when ordered multiple items");
    }

    @Test
    public void missingItemsLunch_Test(){
        itemIds= new String[]{"1"};
        ArrayList<String> inputIds = new ArrayList<String>(Arrays.asList(itemIds));
        mealType = new Lunch(inputIds);
        assertEquals(false, mealType.hasCompulsoryItems(inputIds),"Incorrect output when side dish is missing");
    }

    @Test
    public void missingItemsBreakfast_Test(){
        itemIds= new String[]{};
        ArrayList<String> inputIds = new ArrayList<String>(Arrays.asList(itemIds));
        mealType = new Breakfast(inputIds);
        assertEquals(false, mealType.hasCompulsoryItems(inputIds),"Incorrect output when main and sides are missing");
    }

    @Test
    public void missingItemsDinner_Test(){
        itemIds= new String[]{"1","2"};
        ArrayList<String> inputIds = new ArrayList<String>(Arrays.asList(itemIds));
        mealType = new Dinner(inputIds);
        assertEquals(false, mealType.hasCompulsoryItems(inputIds),"Incorrect output when Dessert not ordered in Dinner");
    }

    @Test
    public void waterBreakfast_Test(){
        itemIds = new String[]{"1","2"};
        ArrayList<String> inputIds = new ArrayList<String>(Arrays.asList(itemIds));
        Breakfast mealType = new Breakfast(inputIds);
        mealType.makeOrder(inputIds);
        assertEquals("Eggs,Toast,Water",mealType.printOrder(),"Water not provided when no drink is ordered");
    }

    @Test
    public void waterLunch_Test(){
        itemIds = new String[]{"1","2"};
        ArrayList<String> inputIds = new ArrayList<String>(Arrays.asList(itemIds));
        Lunch mealType = new Lunch(inputIds);
        mealType.makeOrder(inputIds);
        assertEquals("Salad,Chips,Water",mealType.printOrder(),"Water not provided when no drink is ordered");
    }

    @Test
    public void dinnerOrder_Test(){
        itemIds = new String[]{"1","2","3","4"};
        ArrayList<String> inputIds = new ArrayList<String>(Arrays.asList(itemIds));
        Dinner mealType = new Dinner(inputIds);
        mealType.makeOrder(inputIds);
        assertEquals("Steak,Potatoes,Wine,Water,Cake", mealType.printOrder(),"Incorrect output for dinner meal type");
    }

    @Test
    public void dinnerOrder_twoTest(){
        itemIds = new String[]{"1","2","4"};
        ArrayList<String> inputIds = new ArrayList<String>(Arrays.asList(itemIds));
        Dinner mealType = new Dinner(inputIds);
        mealType.makeOrder(inputIds);
        assertEquals("Steak,Potatoes,Water,Cake", mealType.printOrder(),"Water not provided when Drink not ordered in dinner");
    }

    @Test
    public void dinnerMultipleItems_Test(){
        itemIds = new String[]{"1","2","2","4","4"};
        ArrayList<String> inputIds = new ArrayList<String>(Arrays.asList(itemIds));
        mealType = new Dinner(inputIds);
        assertEquals(false,mealType.isValidMultiple(inputIds), "No multiple items allowed in Dinner");
    }

    @Test
    public void dinnerValidateInput_Test(){
        itemIds= new String[]{};
        ArrayList<String> inputIds = new ArrayList<String>(Arrays.asList(itemIds));
        mealType = new Dinner(inputIds);
        assertEquals(false, mealType.validateOrderInput(inputIds),"Incorrect output for an empty dinner order");
    }

}
