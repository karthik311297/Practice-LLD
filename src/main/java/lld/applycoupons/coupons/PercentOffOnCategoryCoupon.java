package lld.applycoupons.coupons;

import lld.applycoupons.Category;
import lld.applycoupons.Product;
import lld.applycoupons.ShoppingCart;
import lld.applycoupons.exception.CouponAlreadyAppliedException;

public class PercentOffOnCategoryCoupon implements Coupon {

    private final double offPercent;
    private final Category category;

    public PercentOffOnCategoryCoupon(double offPercent, Category category) {
        this.offPercent = offPercent;
        this.category = category;
    }

    @Override
    public boolean apply(ShoppingCart shoppingCart) throws CouponAlreadyAppliedException {
        if (shoppingCart.getCouponsApplied().contains(this)) throw new CouponAlreadyAppliedException();
        for (Product p : shoppingCart.getProducts()) {
            if (p.getCategory() == category) {
                double discount = (p.getPrice() * offPercent) / 100;
                double cartTotalAfterDeduction = shoppingCart.getCartTotal() - discount;
                shoppingCart.setCartTotal(cartTotalAfterDeduction);
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return offPercent + " Percent Off on category " + Category.FASHION + " Coupon";
    }
}
