package order.ordervalidator;

import order.IMessageQueue;
import order.Order;

import java.util.Objects;

import static menu.CourseType.Main;

public class RequireMainCourseValidator implements IOrderValidator {
    @Override
    public boolean validate(Order order) {
        Objects.requireNonNull(order);
        return !order.getMenuItems(Main).isEmpty();
    }

    @Override
    public void reportFailureToMessageQueue(Order order, IMessageQueue invalidMessageQueue) {
        invalidMessageQueue.add("Main is missing");

    }
}
