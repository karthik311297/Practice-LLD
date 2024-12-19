package lld.coffeevending;

import lld.coffeevending.coffee.Coffee;

public class CoffeeDispenser {
    public void dispense(Coffee coffee)
    {
        System.out.println("Dispensed coffee " + coffee.getClass().getSimpleName());
    }
}
