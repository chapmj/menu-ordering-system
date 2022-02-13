package order.ordervalidator;


import menu.MenuItem;
import order.IMessageQueue;
import order.Order;

import java.util.List;
import java.util.Objects;

public class RequireWaterValidator implements IOrderValidator {

    @Override
    public boolean validate(Order order) {
        Objects.requireNonNull(order);

        List<MenuItem> drinks = order.getDrinkCourses();

        Objects.requireNonNull(drinks);
        for(MenuItem menuItem : drinks) {
            if(menuItem.toString().equals("Water")) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void reportFailureToMessageQueue(Order order, IMessageQueue invalidMessageQueue) {
        invalidMessageQueue.add("Water is missing");
    }

}
