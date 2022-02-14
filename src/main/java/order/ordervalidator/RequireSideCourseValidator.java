package order.ordervalidator;

import menu.CourseType;
import order.IMessageQueue;
import order.Order;

import java.util.Objects;

import static menu.CourseType.*;
import static menu.CourseType.Main;

public class RequireSideCourseValidator implements IOrderValidator {

    @Override
    public boolean validate(Order order) {
        Objects.requireNonNull(order);
        return !order.getMenuItems(Side).isEmpty();
    }

    @Override
    public void reportFailureToMessageQueue(Order order, IMessageQueue invalidMessageQueue) {
        invalidMessageQueue.add("Side is missing");
    }

}
