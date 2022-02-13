package order.ordervalidator;

import order.IMessageQueue;
import order.Order;

import java.util.Objects;

public class RequireMainCourseValidator implements IOrderValidator {
    @Override
    public boolean validate(Order order) {
        Objects.requireNonNull(order);
        return !order.getMainCourses().isEmpty();
    }

    @Override
    public void reportFailureToMessageQueue(Order order, IMessageQueue invalidMessageQueue) {
        invalidMessageQueue.add("Main is missing");

    }
}
