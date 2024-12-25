package lld.vendingmachine;

public class Demo {

    /*
    The vending machine should support multiple products with different prices and quantities.
    The machine should accept coins and notes of different denominations.
    The machine should dispense the selected product and return change if necessary.
    The machine should keep track of the available products and their quantities.
    The machine should handle multiple transactions concurrently and ensure data consistency.
    The machine should provide an interface for restocking products and collecting money.
    The machine should handle exceptional scenarios, such as insufficient funds or out-of-stock products.
     */

    /*
        Product - price, name

        InventoryManager - Map<Product name, Integer> quantities
            restock(Product, qty)
            checkAvailability(Product name, qty)
            updateInventory(Product name, qty)

        Treasury - Map<Denomination, List<Money>>
            collect()
            add(Money)

        VendingMachine - InventoryManager, totalPayment, selectedProduct, VendingMachineState
            selectProduct
            insertMoney
            dispenseProduct
            returnChange

        VendingMachineState
            Idle
            Ready
            Dispensing
            ReturnChange

        enum Money - TEN , TWENTY, FIFTY

     */
    public static void main(String[] args) {
        Product product1 = new Product("Lays", 30);
        Product product2 = new Product("Coke", 20);
        Product product3 = new Product("Parle-G", 30);
        Product product4 = new Product("Samosa", 60);
        VendingMachine vendingMachine = VendingMachine.getInstance();
        vendingMachine.getInventoryManager().restockProduct(product1, 3);
        vendingMachine.getInventoryManager().restockProduct(product2, 1);
        vendingMachine.getInventoryManager().restockProduct(product3, 1);
        vendingMachine.getInventoryManager().restockProduct(product4, 2);

        vendingMachine.selectProduct(product3);
        vendingMachine.insertMoney(Money.TEN);
        vendingMachine.dispenseProduct();
        vendingMachine.insertMoney(Money.TWENTY);
        vendingMachine.dispenseProduct();
        vendingMachine.returnChange();

        vendingMachine.selectProduct(product2);
        vendingMachine.insertMoney(Money.FIFTY);
        vendingMachine.dispenseProduct();
        vendingMachine.returnChange();

        vendingMachine.selectProduct(product2);
        vendingMachine.insertMoney(Money.FIFTY);
        vendingMachine.dispenseProduct();
        vendingMachine.returnChange();

        vendingMachine.getTreasury().collect();
    }
}
