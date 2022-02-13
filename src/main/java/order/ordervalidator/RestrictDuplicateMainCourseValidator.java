package order.ordervalidator;

import menu.MenuItem;
import order.IMessageQueue;
import order.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RestrictDuplicateMainCourseValidator implements IOrderValidator {

    @Override
    public boolean validate(Order order) {
        Objects.requireNonNull(order);
        return order.getMainCourses().size() <= 1;
    }

    @Override
    public void reportFailureToMessageQueue(Order order, IMessageQueue invalidMessageQueue) {
        List<MenuItem> mainCourses = order.getMainCourses();

        List<String> sortedItems = mainCourses.stream().map(MenuItem::toString).collect(Collectors.toList());
        List<String> duplicates = new ArrayList<>();

        String prev = "zzNOMATCH";
        for(String menuItem : sortedItems) {
            if(menuItem.equals(prev)) {
                duplicates.add(menuItem);
            }
            else {
                prev = menuItem;
            }
        }

        for(String duplicateItem : duplicates) {
            invalidMessageQueue.add(String.format("%s cannot be ordered more than once", duplicateItem));
        }
    }
}
