package lld.coffeevending;

import lld.coffeevending.coffee.CoffeeType;

public class CoffeeDemo {
    /*
    The coffee vending machine should support different types of coffee, such as espresso, cappuccino, and latte.
    Each type of coffee should have a specific price and recipe (ingredients and their quantities).
    The machine should have a menu to display the available coffee options and their prices.
    Users should be able to select a coffee type and make a payment.
    The machine should dispense the selected coffee and provide change if necessary.
    The machine should track the inventory of ingredients and notify when they are running low.
    The machine should handle multiple user requests concurrently and ensure thread safety.
     */

    /*
        CoffeeDispenser
            dispense();

        PaymentSlot
            pay()
            getChange();

        CoffeeVendingMachine
            display()
            int buyCoffee(CoffeeType, amount)

        CoffeeFactory

        Coffee -> Espresso, Cappucino, latte
            getPrice()
            getReceipe();

        enum CoffeeType
            Espresso, Cappucino, latte

        enum Ingredient

        InventoryManager
            Map<Ingredient, Integer> ingQuant;

            updateInventory(Map<Ingredient, Integer> reciepe)

     */

    public static void main(String[] args) throws IngredientsNotSufficientException {
        CoffeeVendingMachine coffeeVendingMachine = CoffeeVendingMachine.getInstance();

        coffeeVendingMachine.displayMenu();
        int c1 = coffeeVendingMachine.buyCoffee(CoffeeType.ESPRESSO, 30);
        int c2 = coffeeVendingMachine.buyCoffee(CoffeeType.LATTE, 40);
        int c3 = coffeeVendingMachine.buyCoffee(CoffeeType.CAPPUCINO, 40);
        System.out.println("");
    }
}
