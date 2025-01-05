package lld.applycoupons.coupons;

import lld.applycoupons.Product;
import lld.applycoupons.ShoppingCart;
import lld.applycoupons.exception.CouponAlreadyAppliedException;

import java.util.Objects;

public class PercentOffCoupon implements Coupon
{
    private final double offPercent;

    public PercentOffCoupon(double offPercent) {
        this.offPercent = offPercent;
    }

    @Override
    public boolean apply(ShoppingCart shoppingCart) throws CouponAlreadyAppliedException {
        if (shoppingCart.getCouponsApplied().contains(this)) throw new CouponAlreadyAppliedException();
        for (Product p : shoppingCart.getProducts()) {
            double discount = (p.getPrice() * offPercent) / 100;
            double cartTotalAfterDeduction = shoppingCart.getCartTotal() - discount;
            shoppingCart.setCartTotal(cartTotalAfterDeduction);
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PercentOffCoupon that = (PercentOffCoupon) o;
        return offPercent == that.offPercent;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(offPercent);
    }

    @Override
    public String toString() {
        return  offPercent + " Percent Off Coupon";
    }
}
