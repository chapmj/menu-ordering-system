package order.ordervalidator;

import order.IMessageQueue;
import order.Order;

public class AlwaysTrueValidator implements IOrderValidator {

    @Override
    public boolean validate(Order order) {
        return true;
    }

    @Override
    public void reportFailureToMessageQueue(Order order, IMessageQueue invalidMessageQueue) {
        //intentionally blank
    }
}
