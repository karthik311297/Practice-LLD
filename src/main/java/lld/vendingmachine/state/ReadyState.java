package lld.vendingmachine.state;

import lld.vendingmachine.Money;
import lld.vendingmachine.Product;
import lld.vendingmachine.VendingMachine;

public class ReadyState implements VMState{


    private VendingMachine vendingMachine;

    public ReadyState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectProduct(Product product) {
        System.out.println("Product already selected, please insert money");
    }

    @Override
    public void insertMoney(Money money) {
        vendingMachine.updateTotalMoney(money);
        if(!isEnoughMoney()) return;
        vendingMachine.setCurrentState(vendingMachine.getDispenseState());
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Please insert sufficient money for the selected product first");
    }

    @Override
    public void returnChange() {
        System.out.println("Please insert money for the selected product first");
    }

    private boolean isEnoughMoney()
    {
        int tot = vendingMachine.getTotalMoney();
        return tot >= vendingMachine.getSelectedProduct().getPrice();
    }
}
