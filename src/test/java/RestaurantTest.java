import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class RestaurantTest {
    private Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time() {
        Restaurant restaurant = Mockito.spy(getRestaurant());

        when(restaurant.getCurrentTime()).thenReturn(LocalTime.parse("13:00:00"));

        assertTrue(restaurant.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time() {
        Restaurant restaurant = Mockito.spy(getRestaurant());

        when(restaurant.getCurrentTime()).thenReturn(LocalTime.parse("23:00:00"));

        assertFalse(restaurant.isRestaurantOpen());
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1() {
        Restaurant restaurant = getRestaurant();
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie", 319);
        assertEquals(initialMenuSize + 1, restaurant.getMenu().size());
    }

    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws ItemNotFoundException {
        Restaurant restaurant = getRestaurant();
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize - 1, restaurant.getMenu().size());
    }

    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        Restaurant restaurant = getRestaurant();
        assertThrows(ItemNotFoundException.class,
                () -> restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //<<<<<<<<<<<<<<<<<<<<<<<ORDER>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    @Test
    public void adding_no_item_to_order_should_return_order_total_as_0() {
        Restaurant restaurant = getRestaurant();

        assertEquals(restaurant.getOrderTotal(), 0);
    }

    @Test
    public void adding_items_to_order_should_return_correct_order_total() {
        Restaurant restaurant = getRestaurant();

        assertEquals(restaurant.getOrderTotal("Sweet corn soup", "Vegetable lasagne"), 119 + 269);
    }

    //<<<<<<<<<<<<<<<<<<<<<<<ORDER>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    private Restaurant getRestaurant() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        return restaurant;
    }
}