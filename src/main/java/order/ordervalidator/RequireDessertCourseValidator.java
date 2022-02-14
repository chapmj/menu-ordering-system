package order.ordervalidator;

import order.IMessageQueue;
import order.Order;

import java.util.Objects;

import static menu.CourseType.Dessert;

public class RequireDessertCourseValidator implements IOrderValidator {

    @Override
    public boolean validate(Order order) {
        Objects.requireNonNull(order);
        return !order.getMenuItems(Dessert).isEmpty();
    }

    @Override
    public void reportFailureToMessageQueue(Order order, IMessageQueue invalidMessageQueue) {
        invalidMessageQueue.add("Dessert is missing");

    }
}
