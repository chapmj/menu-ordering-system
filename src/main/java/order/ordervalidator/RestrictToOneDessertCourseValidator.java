package order.ordervalidator;

import order.IMessageQueue;
import order.Order;

import java.util.Objects;

public class RestrictToOneDessertCourseValidator implements IOrderValidator {

    @Override
    public boolean validate(Order order) {
        Objects.requireNonNull(order);
        return order.getDessertCourses().size() <= 1;
    }

    @Override
    public void reportFailureToMessageQueue(Order order, IMessageQueue invalidMessageQueue) {
        invalidMessageQueue.add("Unable to process: Dessert cannot be ordered more than once");
    }
}
