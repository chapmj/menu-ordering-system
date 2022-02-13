package order;

import java.util.ArrayDeque;
import java.util.Queue;

public class InvalidOrderMessageQueueSvc implements IMessageQueue {
    //Global singleton for capturing order failure messages

    private static InvalidOrderMessageQueueSvc instance;
    Queue<String> invalidOrderMessages;

    private InvalidOrderMessageQueueSvc() {
        invalidOrderMessages = new ArrayDeque<>();
    }

    public static InvalidOrderMessageQueueSvc getInstance() {
        if(instance == null) {
            instance = new InvalidOrderMessageQueueSvc();
        }
        return instance;
    }

    public static void destroyInstance() {
        instance = null;
    }

    public void add(String failureMessage) {
        invalidOrderMessages.add(failureMessage);
    }

    public String remove() {
        return invalidOrderMessages.remove();
    }

    public int size() {
        return invalidOrderMessages.size();
    }

    public void clear() {
        invalidOrderMessages.clear();
    }

    @Override
    public boolean isEmpty() {
        return invalidOrderMessages.isEmpty();
    }
}
