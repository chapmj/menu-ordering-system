package order.ordervalidator;

import order.IMessageQueue;
import order.Order;

import java.util.Objects;

public class RequireDessertCourseValidator implements IOrderValidator {

    @Override
    public boolean validate(Order order) {
        Objects.requireNonNull(order);
        return !order.getDessertCourses().isEmpty();
    }

    @Override
    public void reportFailureToMessageQueue(Order order, IMessageQueue invalidMessageQueue) {
        invalidMessageQueue.add("Dessert is missing");

    }
}
