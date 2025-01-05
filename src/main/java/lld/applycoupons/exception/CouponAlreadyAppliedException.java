package lld.applycoupons.exception;

public class CouponAlreadyAppliedException extends Exception {

    public CouponAlreadyAppliedException() {
        super("The Coupon has already been applied to the cart");
    }
}
