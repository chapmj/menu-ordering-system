package order.ordervalidator;

import menu.MenuItem;
import order.InvalidOrderMessageQueueSvc;
import order.Order;
import order.OrderFactory;
import order.OrderFormatVerifier;
import order.ordervalidator.RequireDessertCourseValidator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class RequireDessertCourseValidatorTest {

    @Test
    void validateTest() {
        OrderFactory orderFactory = new OrderFactory(new OrderFormatVerifier());
        ArrayList<MenuItem> mainCourses = new ArrayList<>();
        ArrayList<MenuItem> sideCourses = new ArrayList<>();
        ArrayList<MenuItem> drinkCourses = new ArrayList<>();
        ArrayList<MenuItem> dessertCourses = new ArrayList<>();
        Order order = new Order("Breakfast" , mainCourses, sideCourses, drinkCourses, dessertCourses);


        RequireDessertCourseValidator validator = new RequireDessertCourseValidator();

        boolean actualResult = validator.validate(order);

        assertFalse(actualResult);
    }

    @Test
    void reportFailureToMessageQueueTest() {
        InvalidOrderMessageQueueSvc svc = InvalidOrderMessageQueueSvc.getInstance();
        RequireDessertCourseValidator validator = new RequireDessertCourseValidator();
        validator.reportFailureToMessageQueue(null, svc);

        String result = svc.remove();
        assertEquals(result, "Dessert is missing");

    }
}