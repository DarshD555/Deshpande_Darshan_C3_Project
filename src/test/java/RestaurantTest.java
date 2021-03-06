import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;
    //REFACTORED ALL THE REPEATED LINES OF CODE BELOW
    @BeforeEach
    public void beforeEachTest(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }
    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, USED THE CONCEPT OF MOCKING, BECAUSE I RAN INTO TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        Clock clock = Clock.fixed(Instant.parse("2021-09-20T08:15:30.00Z"), ZoneId.of("UTC"));
        LocalTime localTime = LocalTime.now(clock);
        Restaurant restaurant1 = Mockito.spy(restaurant);
        Mockito.doReturn(localTime).when(restaurant1).getCurrentTime();
        restaurant1.displayDetails();
        boolean isOpen = restaurant1.isRestaurantOpen();
        assertTrue(isOpen);

        //WROTE UNIT TEST CASE HERE
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        Clock clock = Clock.fixed(Instant.parse("2021-09-20T11:15:30.00Z"), ZoneId.of("UTC"));
        LocalTime localTime = LocalTime.now(clock);
        Restaurant restaurant1 = Mockito.spy(restaurant);
        Mockito.doReturn(localTime).when(restaurant1).getCurrentTime();
        restaurant1.displayDetails();
        boolean isOpen = restaurant1.isRestaurantOpen();
        assertFalse(isOpen);

        //WROTE UNIT TEST CASE HERE

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    // REFACTORING DONE ABOVE WITH BEFORE EACH ANNOTATION

    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    // >>>>>>>>>>>>>>>>>>> AFTER "PART 3: FAILING TEST CASE", NOW "PART 3: SOLUTION" USING TDD BELOW <<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_items_from_menu_should_return_and_display_total_order_value_of_selected_items_from_the_menu(){
    Item item = null;
    restaurant.addToMenu("Sweet corn soup",119);
    restaurant.addToMenu("Vegetable lasagne", 269);
    restaurant.addToMenu("Sizzling brownie",319);
    assertNotNull(restaurant.getTotalOrderCost());

    }
    // Passing test cases for the implemented methods.
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}

