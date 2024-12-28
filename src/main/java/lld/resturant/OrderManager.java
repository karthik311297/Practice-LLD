package lld.resturant;

public class OrderManager {

    private OrderProcessor orderProcessor;

    public OrderManager() {
        this.orderProcessor = new OrderProcessor(this);
        new Thread(() -> this.orderProcessor.run()).start();
    }

    public void prepareOrder(Order order) {
        orderProcessor.addOrderToQueue(order);
    }

    public void serveOrder(int orderId) {
        System.out.println("Order " + orderId + " is served ");
    }
}
