package order;

import menu.MenuItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class OrderPrinterSvc {


    private OrderPrinterSvc() { }

    private static OrderPrinterSvc instance;

    public static OrderPrinterSvc getInstance() {
        if(instance == null) {
            instance = new OrderPrinterSvc();
        }

        return instance;
    }

    public String print(Order order) {
        IMessageQueue invalidMessageQueue = InvalidOrderMessageQueueSvc.getInstance();
        String output;
        if (invalidMessageQueue.size() > 0) {
            output = formatInvalidMessages(invalidMessageQueue);
        }
        else {
            output = formatValidOrders(order);
        }

        System.out.println(output);
        return output;
    }

    private String formatInvalidMessages(IMessageQueue invalidMessageQueue) {
        StringBuilder sb = new StringBuilder();

        String message;
        if(!invalidMessageQueue.isEmpty()) {
            sb.append("Unable to process: ");
            message = invalidMessageQueue.remove();
            sb.append(message);
        }


        while (!invalidMessageQueue.isEmpty()) {
            message = invalidMessageQueue.remove();
            sb.append(", ");

            String messageHead = message.substring(0, 1).toLowerCase();
            sb.append(messageHead);

            String messageTail = message.substring(1);
            sb.append(messageTail);

        }

        return sb.toString();

    }

    private String formatValidOrders(Order order) {

        List<List<MenuItem>> courses = new ArrayList<>();
        courses.add(order.getMainCourses());
        courses.add(order.getSideCourses());
        courses.add(order.getDrinkCourses());
        courses.add(order.getDessertCourses());

        List<String> allItemsList = new ArrayList<>();
        for(List<MenuItem> course : courses) {
            HashMap<MenuItem, Integer> tally = new LinkedHashMap<>();//use linked to preserve ordering
            for(MenuItem menuItem : course) {
                if (tally.containsKey(menuItem)) {
                    int count = tally.get(menuItem);
                    tally.put(menuItem, count + 1);
                }
                else {
                    tally.put(menuItem, 1);
                }
            }

            List<MenuItem> keys = new ArrayList<>();
            tally.forEach((k, v) -> keys.add(k));

            for(MenuItem item : keys) {
                Integer count = tally.get(item);
                if(count > 1) {
                    allItemsList.add(String.format("%s(%d)", item.toString(), count));
                }
                else {
                    allItemsList.add(item.toString());
                }
            }
        }

        return String.join(", ", allItemsList);
    }

}
