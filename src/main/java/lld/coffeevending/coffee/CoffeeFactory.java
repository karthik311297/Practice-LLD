package lld.coffeevending.coffee;

public class CoffeeFactory {

    public static Coffee getCoffee(CoffeeType coffeeType)
    {
        return switch (coffeeType)
        {
            case LATTE -> new LatteCoffee(20);
            case ESPRESSO -> new EspressoCoffee(30);
            default -> new CappucinoCoffee(40);
        };
    }
}
