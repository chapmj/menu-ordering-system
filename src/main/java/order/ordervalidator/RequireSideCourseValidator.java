package order.ordervalidator;

import order.IMessageQueue;
import order.Order;

import java.util.Objects;

public class RequireSideCourseValidator implements IOrderValidator {

    @Override
    public boolean validate(Order order) {
        Objects.requireNonNull(order);
        return !order.getSideCourses().isEmpty();
    }

    @Override
    public void reportFailureToMessageQueue(Order order, IMessageQueue invalidMessageQueue) {
        invalidMessageQueue.add("Side is missing");
    }

}
