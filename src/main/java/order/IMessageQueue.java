package order;

public interface IMessageQueue {
    void add(String messageText);
    String remove();
    int size();
    boolean isEmpty();
    void clear();
}
