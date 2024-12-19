package lld.coffeevending.coffee;

import lld.coffeevending.Ingredient;

import java.util.HashMap;
import java.util.Map;

public class CappucinoCoffee extends Coffee{

    public CappucinoCoffee(int price) {
        super(price);
    }

    @Override
    public Map<Ingredient, Integer> getRecipe() {
        Map<Ingredient, Integer> recipe = new HashMap<>();
        recipe.put(Ingredient.MILK, 2);
        recipe.put(Ingredient.COFFEE_BEANS, 3);
        recipe.put(Ingredient.SUGAR, 2);
        recipe.put(Ingredient.CREAM, 1);
        return recipe;
    }
}
