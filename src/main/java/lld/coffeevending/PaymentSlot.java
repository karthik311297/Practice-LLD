package lld.coffeevending;

public class PaymentSlot
{
    public int payAndGetChange(int paid, int actualAmount) throws InsufficientPaymentException
    {
        if(paid < actualAmount) throw new InsufficientPaymentException();
        return returnChange(paid, actualAmount);
    }

    private int returnChange(int paid, int actualAmount)
    {
        return paid - actualAmount;
    }

    public int returnAmount(int amount)
    {
        return amount;
    }
}
