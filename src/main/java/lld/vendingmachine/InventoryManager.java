package lld.vendingmachine;

import java.util.HashMap;
import java.util.Map;

public class InventoryManager {

    private Map<String, Integer> productQuantitiesMap;

    public InventoryManager() {
        this.productQuantitiesMap = new HashMap<>();
    }

    public boolean isAvailable(Product product, int quantity)
    {
        int qty = productQuantitiesMap.getOrDefault(product.getName(), 0);
        return qty > 0;
    }

    public void deductInventory(Product product, int quantity)
    {
        int qty = productQuantitiesMap.get(product.getName());
        synchronized (this)
        {
            qty -= quantity;
            productQuantitiesMap.put(product.getName(), qty);
        }
    }

    public void restockProduct(Product product, int quantity)
    {
        int qty = productQuantitiesMap.getOrDefault(product.getName(), 0);
        synchronized (this)
        {
            qty += quantity;
            productQuantitiesMap.put(product.getName(), qty);
        }
    }
}
