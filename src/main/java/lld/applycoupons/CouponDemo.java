package lld.applycoupons;

import lld.applycoupons.coupons.Coupon;
import lld.applycoupons.coupons.PercentOffCoupon;
import lld.applycoupons.coupons.PercentOffOnCategoryCoupon;

public class CouponDemo {
    /*

    "Given Shopping cart with products and coupons and calculate the net price after applying coupons on products.
    Coupons can be of different types with certain conditions.
    1.N% off that is 10% off for all the individual
    2.P% off on next item
    3.D% off on Nth item of Category T.
    Sequentially wants to apply all the coupons on the cart and get the Total amount."
     */

    /*
        Product - price, category, name


        ShoppingCart - List<Products>, cartTotal, Set<Coupon> couponsApplied

            addProduct()

            applyCoupon(Coupon)

            checkout()

        CouponAlreadyAppliedException

        CouponNotApplicableException


       D%OffOnNthItemOfCategoryT(D, N, T)
                 apply(ShoppingCart)
          |
          |
        Coupon---- P%OffOnParticularProduct(P, Product)
          |            apply(ShoppingCart)
          |
         N%OffCoupon(N)
            apply(ShoppingCart)

     */

    public static void main(String[] args) {
        ShoppingCart shoppingCart = new ShoppingCart();
        Product product1 = new Product(200, Category.HOME_UTILITY, "Pan");
        Product product2 = new Product(900, Category.ELECTRONICS, "HeadPhone");
        Product product3 = new Product(2000, Category.FASHION, "Jacket");
        shoppingCart.addProduct(product1);
        shoppingCart.addProduct(product2);
        shoppingCart.addProduct(product3);
        shoppingCart.checkout();
        Coupon coupon = new PercentOffCoupon(20);
        shoppingCart.applyCoupon(coupon);
        shoppingCart.checkout();
        Coupon coupon1 = new PercentOffOnCategoryCoupon(30, Category.FASHION);
        shoppingCart.applyCoupon(coupon1);
        shoppingCart.checkout();
    }
}
