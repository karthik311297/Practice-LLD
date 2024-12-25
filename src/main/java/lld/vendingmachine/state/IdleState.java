package lld.vendingmachine.state;

import lld.vendingmachine.Money;
import lld.vendingmachine.Product;
import lld.vendingmachine.VendingMachine;

public class IdleState implements VMState {

    private VendingMachine vendingMachine;

    public IdleState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectProduct(Product product) {
        if (vendingMachine.getInventoryManager().isAvailable(product, 1)) {
            vendingMachine.setSelectedProduct(product);
            vendingMachine.setCurrentState(vendingMachine.getReadyState());
        } else {
            System.out.println("Insufficient product stock");
        }
    }

    @Override
    public void insertMoney(Money money) {
        System.out.println("Please select a product first");
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Please select a product first");
    }

    @Override
    public void returnChange() {
        System.out.println("Please select a product first");
    }
}
