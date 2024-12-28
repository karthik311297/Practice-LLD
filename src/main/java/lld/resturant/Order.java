package lld.resturant;

import java.util.List;

public class Order {
    private int id;
    private List<OrderItem> orderItems;
    private OrderStatus orderStatus;

    public Order(int id, List<OrderItem> orderItems) {
        this.id = id;
        this.orderItems = orderItems;
        this.orderStatus = OrderStatus.PENDING;
    }

    public int getId() {
        return id;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void display()
    {
        System.out.println("---------------");
        for(OrderItem item : orderItems)
        {
            System.out.println(item);
        }
        System.out.println("---------------");
    }


}
