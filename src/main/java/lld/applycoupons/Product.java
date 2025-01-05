package lld.applycoupons;

public class Product {
    private double price;
    private Category category;
    private String name;

    public Product(double price, Category category, String name) {
        this.price = price;
        this.category = category;
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Product{" +
                "price=" + price +
                ", category=" + category +
                ", name='" + name + '\'' +
                '}';
    }
}
