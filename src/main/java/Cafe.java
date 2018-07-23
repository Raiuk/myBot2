import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static java.lang.Thread.sleep;

public class Cafe implements Runnable {
    List<Table> tableList = new ArrayList<Table>();
    Map<String, Integer> menu;
    static Object lock = new Object();
    Thread thrd;

    Client client1 = new Client();
    Client client2 = new Client();


    Order order1 = new Order();

    Order order2 = new Order();


    Table t1 = new Table();



    Cafe(String name) {
        client1.setName("Petr");
        client1.setPhoneNumber("34234141");

        order1.client = client1;
        order1.order = "fsdf";
        order1.testTime = 5;

        client2.setName("Ivan");
        client2.setPhoneNumber("12314123");

        order2.client = client2;
        order2.order = "fasdagsfa";
        order2.testTime = 10;

        t1.ordersQueue = new LinkedList<Order>();

        t1.ordersQueue.add(order1);
        t1.ordersQueue.add(order2);

        t1.capacity = 4;
        t1.id = 1;
        t1.isFree = true;

        tableList.add(t1);
        thrd = new Thread(this, name);
        thrd.start();
    }


    public void run() {
        int i = 0;
        while (true) {
            if(!t1.ordersQueue.isEmpty() && t1.ordersQueue.get(0) != null) {
                if (t1.ordersQueue.get(0).testTime == 0) {
                    System.out.println("Task: " + t1.ordersQueue.get(0).toString() + " was deleted. Tick - " + i);
                    System.out.println("----------------------------------");
                    t1.ordersQueue.remove(0);
                    if(!t1.ordersQueue.isEmpty() && t1.ordersQueue.get(0) != null)
                        System.out.println("Task: " + t1.ordersQueue.get(0).toString() + " is started. Tick - " + i);
                } else {
                    System.out.println("Tick - " + i + ". Task: " + t1.ordersQueue.get(0).toString());
                    --t1.ordersQueue.get(0).testTime;
                }
            }
            else System.out.println("Tick - " + i);
            try {
                sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ++i;
        }
        //System.out.println("-----------------------------------");
    }

    public String getCurrentTask() {
        if(!t1.ordersQueue.isEmpty() && t1.ordersQueue.get(0) != null) {
            return "Task: " + t1.ordersQueue.get(0).toString() + " is running.";
        }
        else return "The resource is free.";
    }

    public int placeInQueue(String s) {
        return t1.placeInQueue(s);
    }

    public boolean removeOrder(int i) {
        return t1.removeOrder(i);
    }


}
