package lld.coffeevending.coffee;

import lld.coffeevending.Ingredient;

import java.util.Map;

public abstract class Coffee {

    private int price;

    public Coffee(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public abstract Map<Ingredient, Integer> getRecipe();
}
