package lld.resturant;

import java.util.Arrays;

public class ResturantDemo {
    /*
    The restaurant management system should allow customers to place orders, view the menu, and make reservations.
    The system should manage the restaurant's inventory, including ingredients and menu items.
    The system should handle order processing, including order preparation, billing, and payment.
    The system should support multiple payment methods, such as cash, credit card, and mobile payments.
    The system should manage staff information, including roles, schedules, and performance tracking.
    The system should generate reports and analytics for management, such as sales reports and inventory analysis.
    The system should handle concurrent access and ensure data consistency.
     */

    /*
        Customer
            id

        Menu

        MenuItem
            id
            name
            price

        OrderItem
            MenuItem
            qty

        OrderManager
            placeOrder()

        OrderProcessor

        BillingCounter
            addOrder()
            generateBill()

        Order
            List<OrderItem>
            addOrderItem

        enum Ingredient


        RecipeRepository
            Map<menuitem id, Map<ingredient, quantity>> recipeMap
            Map<ingredient, quantity>> getRecipe(menuItem id)

        InventoryManager
                Map<ingredient, quantity> stock;
                checkIfAvailable(Map<ingredient, quantity> itemRecipe)
                deductFromInventory(Map<ingredient, quantity> itemRecipe)

     */


    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getId());
        Resturant resturant = Resturant.getInstance();
        Menu menu = resturant.getMenu();
        menu.display();
        resturant
                .placeOrder(Arrays.asList(new OrderItem(new MenuItem("1", "idly", 30), 2), new OrderItem(new MenuItem("2", "vada", 40), 1)));
        resturant
                .placeOrder(Arrays.asList(new OrderItem(new MenuItem("1", "idly", 30), 2), new OrderItem(new MenuItem("2", "vada", 40), 2)));
        resturant
                .placeOrder(Arrays.asList(new OrderItem(new MenuItem("1", "idly", 30), 2), new OrderItem(new MenuItem("2", "vada", 40), 3)));
        resturant
                .placeOrder(Arrays.asList(new OrderItem(new MenuItem("1", "idly", 30), 2), new OrderItem(new MenuItem("2", "vada", 40), 1)));
        resturant
                .placeOrder(Arrays.asList(new OrderItem(new MenuItem("1", "idly", 30), 2), new OrderItem(new MenuItem("2", "vada", 40), 2)));
        resturant.getBillForOrder(1);
        resturant.getBillForOrder(2);
        resturant.getBillForOrder(3);
        resturant.getBillForOrder(4);
        resturant.getBillForOrder(5);
    }
}
