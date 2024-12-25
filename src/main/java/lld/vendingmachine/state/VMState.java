package lld.vendingmachine.state;

import lld.vendingmachine.Money;
import lld.vendingmachine.Product;

public interface VMState {

    public void selectProduct(Product product);

    public void insertMoney(Money money);

    public void dispenseProduct();

    public void returnChange();
}
