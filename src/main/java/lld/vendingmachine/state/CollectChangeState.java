package lld.vendingmachine.state;

import lld.vendingmachine.Money;
import lld.vendingmachine.Product;
import lld.vendingmachine.VendingMachine;

public class CollectChangeState implements VMState{

    private VendingMachine vendingMachine;

    public CollectChangeState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectProduct(Product product) {
        System.out.println("Product collect the change first");
    }

    @Override
    public void insertMoney(Money money) {
        System.out.println("Product collect the change first");
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Product collect the change first");
    }

    @Override
    public void returnChange() {
        int change = vendingMachine.getTotalMoney() - vendingMachine.getSelectedProduct().getPrice();
        if (change > 0) System.out.println("Returning change: " + change);
        vendingMachine.setSelectedProduct(null);
        vendingMachine.getTreasury().addMoney(vendingMachine.getTotalMoney() - change);
        vendingMachine.resetTotalMoney();
        vendingMachine.setCurrentState(vendingMachine.getIdleState());
    }
}
