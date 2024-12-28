package lld.resturant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BillingCounter {

    private Map<Integer, Order> orderItemsMap;

    public BillingCounter() {
        orderItemsMap = new HashMap<>();
    }

    public synchronized void addOrder(Order order) {
        orderItemsMap.put(order.getId(), order);
    }

    public void generateBillForOrder(int orderId) {
        int total = 0;
        Order order = orderItemsMap.get(orderId);
        List<OrderItem> orderItems = order.getOrderItems();
        for (OrderItem orderItem : orderItems) {
            total += (orderItem.getQty() * orderItem.getMenuItem().getPrice());
        }
        System.out.println("Generating bill for order id : " + orderId);
        order.display();
        System.out.println("Total to be paid : " + total);
    }
}
