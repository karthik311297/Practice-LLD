package lld.coffeevending;

import lld.coffeevending.coffee.Coffee;
import lld.coffeevending.coffee.CoffeeFactory;
import lld.coffeevending.coffee.CoffeeType;

public class CoffeeVendingMachine {

    private static final CoffeeVendingMachine INSTANCE = new CoffeeVendingMachine();

    private final CoffeeDispenser coffeeDispenser;
    private final PaymentSlot paymentSlot;
    private final InventoryManager inventoryManager;

    public static CoffeeVendingMachine getInstance() {
        return INSTANCE;
    }

    private CoffeeVendingMachine()
    {
        coffeeDispenser = new CoffeeDispenser();
        paymentSlot = new PaymentSlot();
        inventoryManager = new InventoryManager();
        inventoryManager.initializeInventory();
    }

    public void displayMenu()
    {
        for(CoffeeType coffeeType : CoffeeType.values())
        {
            System.out.println(coffeeType.name() + ", price: " + CoffeeFactory.getCoffee(coffeeType).getPrice());
        }
    }

    public int buyCoffee(CoffeeType coffeeType, int payment)
    {
        Coffee coffee = CoffeeFactory.getCoffee(coffeeType);
        try
        {
            int change  = paymentSlot.payAndGetChange(payment, coffee.getPrice());
            inventoryManager.consumeRecipeAndUpdateInventory(coffee.getRecipe());
            coffeeDispenser.dispense(coffee);
            return change;
        }
        catch (InsufficientPaymentException e)
        {
            System.out.println("Payment is insufficient, returning the amount");
            return paymentSlot.returnAmount(payment);
        }
        catch (IngredientsNotSufficientException e)
        {
            System.out.println("Sufficient ingredients are not available, returning the full amount");
            return paymentSlot.returnAmount(payment);
        }
    }
}
