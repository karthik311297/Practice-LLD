package lld.resturant;

public class OrderItem {
    private MenuItem menuItem;
    private int qty;

    public OrderItem(MenuItem menuItem, int qty) {
        this.menuItem = menuItem;
        this.qty = qty;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public int getQty() {
        return qty;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "menuItem=" + menuItem +
                ", qty=" + qty +
                '}';
    }
}
