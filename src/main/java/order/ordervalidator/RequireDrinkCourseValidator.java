package order.ordervalidator;

import order.IMessageQueue;
import order.Order;

import java.util.Objects;

public class RequireDrinkCourseValidator implements IOrderValidator {
    @Override
    public boolean validate(Order order) {
        Objects.requireNonNull(order);
        return !order.getDrinkCourses().isEmpty();
    }

    @Override
    public void reportFailureToMessageQueue(Order order, IMessageQueue invalidMessageQueue) {
        invalidMessageQueue.add("Drink is missing");

    }
}
