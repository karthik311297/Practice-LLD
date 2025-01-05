package lld.applycoupons;

import lld.applycoupons.coupons.Coupon;
import lld.applycoupons.exception.CouponAlreadyAppliedException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ShoppingCart {

    private double cartTotal;
    private List<Product> products;
    private Set<Coupon> couponsApplied;

    public ShoppingCart() {
        this.cartTotal = 0;
        this.products = new ArrayList<>();
        this.couponsApplied = new HashSet<>();
    }

    public void setCartTotal(double cartTotal) {
        this.cartTotal = cartTotal;
    }

    public double getCartTotal() {
        return cartTotal;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Set<Coupon> getCouponsApplied() {
        return couponsApplied;
    }

    public synchronized void addProduct(Product product)
    {
        products.add(product);
        cartTotal += product.getPrice();
    }

    public void applyCoupon(Coupon coupon)
    {
        try {
            coupon.apply(this);
            couponsApplied.add(coupon);
        } catch (CouponAlreadyAppliedException e) {
            System.out.println(e.getMessage());
        }
    }

    public void checkout()
    {
        System.out.println("--------------------");
        System.out.print("Products in cart : ");
        System.out.println(products.stream()
                .map(Product::toString)
                .collect(Collectors.joining(",")));
        if(!couponsApplied.isEmpty()) {
            System.out.print("Coupons Applied : ");
            System.out.println(couponsApplied.stream()
                    .map(Coupon::toString)
                    .collect(Collectors.joining(",")));
        }
        System.out.println("Total amount to be paid : " + cartTotal);
        System.out.println("--------------------");
    }
}
