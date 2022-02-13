import menu.*;
import order.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MainTest {
    static OrderFactory orderFactory;

    @BeforeAll
    static void init() {
        MenuSvcInit.initialize();
        OrderValidatorSvcInit.initialize();
        orderFactory = new OrderFactory(new OrderFormatVerifier());
    }


    @Test
    public void safeInputBreakfastTest() {
        String input = "Breakfast 1,2,3";
        Order order = orderFactory.create(input);
        assertNotNull(order);
        OrderValidatorSvc.getInstance().validate(order);
        String output = OrderPrinterSvc.getInstance().print(order);
        assertEquals("Eggs, Toast, Coffee", output);
    }

    @Test
    public void unorderedInputTest() {
        String input = "Breakfast 2,3,1";
        Order order = orderFactory.create(input);
        assertNotNull(order);
        OrderValidatorSvc.getInstance().validate(order);
        String output = OrderPrinterSvc.getInstance().print(order);
        assertEquals("Eggs, Toast, Coffee", output);
    }

    @Test
    public void multipleDrinksBreakfastTest() {
        String input = "Breakfast 1,2,3,3,3";
        Order order = orderFactory.create(input);
        assertNotNull(order);
        OrderValidatorSvc.getInstance().validate(order);
        String output = OrderPrinterSvc.getInstance().print(order);
        assertEquals("Eggs, Toast, Coffee(3)", output);
    }

    @Test
    public void missingSideBreakfastTest() {
        String input = "Breakfast 1";
        Order order = orderFactory.create(input);
        assertNotNull(order);
        OrderValidatorSvc.getInstance().validate(order);
        String output = OrderPrinterSvc.getInstance().print(order);
        assertEquals("Unable to process: Side is missing", output);
    }

    @Test
    public void safeInputLunchTest() {
        String input = "Lunch 1,2,3";
        Order order = orderFactory.create(input);
        assertNotNull(order);
        OrderValidatorSvc.getInstance().validate(order);
        String output = OrderPrinterSvc.getInstance().print(order);
        assertEquals("Sandwich, Chips, Soda", output);
    }

    @Test
    public void noDrinkLunchTest() {
        String input = "Lunch 1,2";
        Order order = orderFactory.create(input);
        assertNotNull(order);
        OrderValidatorSvc.getInstance().validate(order);
        String output = OrderPrinterSvc.getInstance().print(order);
        assertEquals("Sandwich, Chips, Water", output);
    }

    @Test
    public void multiSandwichLunchTest() {
        String input = "Lunch 1, 1,2, 3";
        Order order = orderFactory.create(input);
        assertNotNull(order);
        OrderValidatorSvc.getInstance().validate(order);
        String output = OrderPrinterSvc.getInstance().print(order);
        assertEquals("Unable to process: Sandwich cannot be ordered more than once", output);
    }

    @Test
    public void multiSideNoDrinkLunchTest() {
        String input = "Lunch 1,2,2";
        Order order = orderFactory.create(input);
        assertNotNull(order);
        OrderValidatorSvc.getInstance().validate(order);
        String output = OrderPrinterSvc.getInstance().print(order);
        assertEquals("Sandwich, Chips(2), Water", output);
    }

    @Test
    public void noOrdersBreakfastTest() {
        String input = "Breakfast";
        Order order = orderFactory.create(input);
        assertNotNull(order);
        OrderValidatorSvc.getInstance().validate(order);
        String output = OrderPrinterSvc.getInstance().print(order);
        assertEquals("Unable to process: Main is missing, side is missing", output);
    }

    @Test
    public void noOrdersLunchTest() {
        String input = "Lunch";
        Order order = orderFactory.create(input);
        assertNotNull(order);
        OrderValidatorSvc.getInstance().validate(order);
        String output = OrderPrinterSvc.getInstance().print(order);
        assertEquals("Unable to process: Main is missing, side is missing", output);
    }

    @Test
    public void noOrdersDinnerTest() {
        String input = "Dinner";
        Order order = orderFactory.create(input);
        assertNotNull(order);
        OrderValidatorSvc.getInstance().validate(order);
        String output = OrderPrinterSvc.getInstance().print(order);
        assertEquals("Unable to process: Main is missing, side is missing, dessert is missing", output);
    }

    @Test
    public void safeInputDinnerTest() {
        String input = "Dinner 1,2,3,4";
        Order order = orderFactory.create(input);
        assertNotNull(order);
        OrderValidatorSvc.getInstance().validate(order);
        String output = OrderPrinterSvc.getInstance().print(order);
        assertEquals("Steak, Potatoes, Wine, Water, Cake", output);
    }

    @Test
    public void missingDessertDinnerTest() {
        String input = "Dinner 1,2,3";
        Order order = orderFactory.create(input);
        assertNotNull(order);
        OrderValidatorSvc.getInstance().validate(order);
        String output = OrderPrinterSvc.getInstance().print(order);
        assertEquals("Unable to process: Dessert is missing", output);
    }

    @Test void longItemId() {

        Menu dinnerMenu = MenuSvc.Get("Dinner");
        Course course = dinnerMenu.getCourse("Dessert");
        course.add(new MenuItem("987654321", "Gummy Bears"));

        String input = "Dinner 1, 2, 3, 987654321";
        Order order = orderFactory.create(input);
        assertNotNull(order);
        OrderValidatorSvc.getInstance().validate(order);
        String output = OrderPrinterSvc.getInstance().print(order);
        assertEquals("Steak, Potatoes, Wine, Water, Gummy Bears", output);


    }


}