package lld.vendingmachine.state;

import lld.vendingmachine.Money;
import lld.vendingmachine.Product;
import lld.vendingmachine.VendingMachine;

public class DispenseState implements VMState{


    private VendingMachine vendingMachine;

    public DispenseState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectProduct(Product product) {
        System.out.println("Product collect the dispensed product first");
    }

    @Override
    public void insertMoney(Money money) {
        System.out.println("Product collect the dispensed product first");
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Dispensing product: " + vendingMachine.getSelectedProduct());
        vendingMachine.getInventoryManager().deductInventory(vendingMachine.getSelectedProduct(), 1);
        vendingMachine.setCurrentState(vendingMachine.getCollectChangeState());
    }

    @Override
    public void returnChange() {
        System.out.println("Product collect the dispensed product first");
    }

}

