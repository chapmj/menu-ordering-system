package order.ordervalidator;

import menu.MenuItem;
import order.IMessageQueue;
import order.Order;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static menu.CourseType.Main;

public class RestrictDuplicateMainCourseValidator implements IOrderValidator {

    @Override
    public boolean validate(Order order) {
        Objects.requireNonNull(order);
        return order.getMenuItems(Main).size() <= 1;
    }

    @Override
    public void reportFailureToMessageQueue(Order order, IMessageQueue invalidMessageQueue) {
        Collection<MenuItem> mainCourses = order.getMenuItems(Main);

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
