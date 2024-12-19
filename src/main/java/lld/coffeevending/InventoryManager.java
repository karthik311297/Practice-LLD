package lld.coffeevending;

import java.util.HashMap;
import java.util.Map;

public class InventoryManager {

    private Map<Ingredient, Integer> ingredientsQuantity;

    public synchronized void consumeRecipeAndUpdateInventory(Map<Ingredient, Integer> recipe) throws IngredientsNotSufficientException
    {
        if(!isIngredientSufficientForRecipe(recipe)) throw new IngredientsNotSufficientException();
        for(Map.Entry<Ingredient, Integer> ingredient : recipe.entrySet())
        {
            Ingredient i = ingredient.getKey();
            int qty = ingredient.getValue();
            ingredientsQuantity.put(i, ingredientsQuantity.get(i) - qty);
        }
    }

    private boolean isIngredientSufficientForRecipe(Map<Ingredient, Integer> recipe)
    {
        for(Map.Entry<Ingredient, Integer> ingredient : recipe.entrySet())
        {
            Ingredient i = ingredient.getKey();
            int qty = ingredient.getValue();
            if(qty > ingredientsQuantity.get(i)) return false;
        }
        return true;
    }

    public void initializeInventory()
    {
        ingredientsQuantity = new HashMap<>();
        ingredientsQuantity.put(Ingredient.MILK, 5);
        ingredientsQuantity.put(Ingredient.COFFEE_BEANS, 5);
        ingredientsQuantity.put(Ingredient.SUGAR, 5);
        ingredientsQuantity.put(Ingredient.CREAM, 2);
    }
}
