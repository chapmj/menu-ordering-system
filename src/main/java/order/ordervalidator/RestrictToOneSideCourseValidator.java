package order.ordervalidator;

import order.IMessageQueue;
import order.Order;

import java.util.Objects;

public class RestrictToOneSideCourseValidator implements IOrderValidator {
    @Override
    public boolean validate(Order order) {
        Objects.requireNonNull(order);
        return order.getSideCourses().size() <= 1;
    }

    @Override
    public void reportFailureToMessageQueue(Order order, IMessageQueue invalidMessageQueue) {
        invalidMessageQueue.add("Side cannot be ordered more than once");

    }
}
