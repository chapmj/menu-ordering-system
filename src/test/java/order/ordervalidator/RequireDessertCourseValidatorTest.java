package order.ordervalidator;

import menu.Course;
import menu.CourseType;
import menu.MenuItem;
import menu.MenuType;
import order.InvalidOrderMessageQueueSvc;
import order.Order;
import order.OrderFactory;
import order.OrderFormatVerifier;
import order.ordervalidator.RequireDessertCourseValidator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static menu.CourseType.*;
import static menu.MenuType.*;
import static org.junit.jupiter.api.Assertions.*;


class RequireDessertCourseValidatorTest {

    @Test
    void validateTest() {
        OrderFactory orderFactory = new OrderFactory(new OrderFormatVerifier());
        Course mainCourse = new Course(Main);
        Course sideCourse = new Course(Side);
        Course drinkCourse = new Course(Drink);
        Course dessertCourse = new Course(Dessert);
        Order order = new Order(Breakfast , mainCourse, sideCourse, drinkCourse, dessertCourse);


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