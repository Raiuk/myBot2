import java.util.List;

public class Table {
    int id;
    int capacity;
    boolean isFree;
    List<Order> ordersQueue;

    public int placeInQueue(String s) {
        for(Order o : ordersQueue) {
            if(o.client.getName().equals(s)) return ordersQueue.indexOf(o);

        }
        return -1;
    }

    public boolean removeOrder(int i) {
        ordersQueue.remove(i);
        return true;
    }
}
