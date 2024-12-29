package lld.resturant;

import java.util.Arrays;
import java.util.List;

public class Resturant {

    private int orderIdSeq;
    private BillingCounter billingCounter;
    private OrderManager orderManager;
    private Menu menu;

    public static Resturant instance = new Resturant();

    public static Resturant getInstance() {
        return instance;
    }

    private Resturant() {
        this.billingCounter = new BillingCounter();
        this.orderManager = new OrderManager();
        menu = new Menu(Arrays.asList(new MenuItem("1", "idly", 30),
                new MenuItem("2", "vada", 40), new MenuItem("3", "Dosa", 50)));
        orderIdSeq = 0;
    }

    public Order placeOrder(List<OrderItem> orderItems) {
        synchronized (this) {
            orderIdSeq++;
        }
        Order order = new Order(orderIdSeq, orderItems);
        billingCounter.addOrder(order);
        orderManager.prepareOrder(order);
        return order;
    }

    public void getBillForOrder(int orderId)
    {
        billingCounter.generateBillForOrder(orderId);
    }

    public Menu getMenu()
    {
        return menu;
    }
}
