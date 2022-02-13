package order.ordervalidator;

import order.IMessageQueue;
import order.Order;

public interface IOrderValidator {
    boolean validate(Order order);
    void reportFailureToMessageQueue(Order order, IMessageQueue invalidMessageQueue);
}
