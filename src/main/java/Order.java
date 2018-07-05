import java.util.Date;

public class Order {
    int id;
    Client client;
    Date bTime;
    Date eTime;
    String order;
    int testTime;

    @Override
    public String toString() {
        return "Order: " + id + " - " + client.toString() + " - " + bTime + " - " + eTime + " - " + order + " - " + testTime;
    }
}
