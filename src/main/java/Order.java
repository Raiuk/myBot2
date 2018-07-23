import java.util.Date;

public class Order {
    static int quantity = 1;
    int id;
    Client client;
    Date bTime;
    Date eTime;
    String order;
    int testTime;

    Order() {
        this.id = quantity;
        ++quantity;
    }

    @Override
    public String toString() {
        return "Order: " + id + " - " + client.toString() + " - " + order + " - " + testTime;
    }
}
