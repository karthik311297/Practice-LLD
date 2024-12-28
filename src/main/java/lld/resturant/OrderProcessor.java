package lld.resturant;

import java.util.LinkedList;
import java.util.Queue;

public class OrderProcessor {

    private OrderManager orderManager;
    private Queue<Order> ordersToProcess;
    private final Object condition = new Object();

    public OrderProcessor(OrderManager orderManager) {
        ordersToProcess = new LinkedList<>();
        this.orderManager = orderManager;
    }

    public void run() {
        try {
            while (true) {
                while (ordersToProcess.isEmpty()) {
                    synchronized (condition) {
                        System.out.println("Waiting for orders");
                        condition.wait();
                    }
                }
                processOrders();
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public synchronized void addOrderToQueue(Order order) {
        ordersToProcess.add(order);
        synchronized (condition) {
            System.out.println("Order: " + order.getId() + " added to queue");
            condition.notifyAll();
        }
    }

    private synchronized void processOrders() {
        Order order = ordersToProcess.poll();
        sleep(5000);
        order.setOrderStatus(OrderStatus.COMPLETED);
        System.out.println("Order Processing for: " + order.getId() + " is completed by processor " + Thread.currentThread().getId());
        orderManager.serveOrder(order.getId());
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }


}
