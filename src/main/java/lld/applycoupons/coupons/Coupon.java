package lld.applycoupons.coupons;

import lld.applycoupons.exception.CouponAlreadyAppliedException;
import lld.applycoupons.ShoppingCart;

public interface Coupon {
    boolean apply(ShoppingCart shoppingCart) throws CouponAlreadyAppliedException;


}
