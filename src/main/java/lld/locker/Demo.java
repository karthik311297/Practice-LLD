package lld.locker;

public class Demo {
    /*
    Problem statement:
        Design a Locker management system for an ecommerce store.
        While performing a purchase, the users can opt for their packages to get delivered at a Locker system close to their place.
        The delivery person will place the package in a locker.
        An otp will be generated and sent to the user once the package is added to the locker.
        As per the user’s convenience, they can visit the locker, enter the otp and get their parcel.
        Further, if the users want to return the goods, they can visit the locker and place the item.
        Delivery guy will get an otp which can be used to unlock the locker.

    Following are the expectations from the system:
        Person asks the system to allocate a locker for a given package. Assume random allocation for now.
        The system should be extensible and accommodate allocation of locker based on the size of the input package
        The system must generate a code/otp and send it to the user along with the locker details
        The user can enter the otp & locker details to unlock the locker
        Once the package is taken out, the locker can be allocated for any other order
        Users can use the locker for returning an item. OTP/code will be sent to the delivery person in this case
        Operations/Admin can view & vacate all lockers which are in use for more than 3 days

    Evaluation criteria:
        Code readability
        Extensibility & re-usability
        Modularity
        Testability
     */

    /*
        User

        DeliveryExecutive

        Package
            Size packageSize

        enum Size - SMALL, MEDIUM, LARGE

        Locker
            id
            Size lockerSize
            Order order
            OTP keepOrder(Order) - user is notified
            Order unlock(OTP)
            OTP returnOrder(Order) - delivery executive is notified
            isFree()
            currentOTP;

        Order
            Package
            User
            DeliveryExecutive

        OTP
            value
            validity

        OTPGenerator

        OTPValidator

        OrderService
            createOrder()
            id findLockerAndDeliverOrder()
            returnOrder()

        LockerService
            createLocker()
            List<> getAllLockers()
            getLockerById()

        -------------------------------------- LockerFindStrategy--------------------------------
        /                          |                                 \                          \
RandomStrategy               LocationBasedRandomStrategy         SizeBasedStrategy       LocationAndSizeBasedStrategy

           method to implement -
                getOptimalLockerForPackage(List<Lockers> l, Package p)

     */
}
