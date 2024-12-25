package lld.vendingmachine;

import lld.vendingmachine.state.*;

public class VendingMachine {

    private int totalMoney;
    private VMState currentState;
    private Product selectedProduct;
    private IdleState idleState;
    private DispenseState dispenseState;
    private ReadyState readyState;
    private CollectChangeState collectChangeState;
    private InventoryManager inventoryManager;
    private Treasury treasury;

    private static final VendingMachine instance = new VendingMachine();

    public static VendingMachine getInstance() {
        return instance;
    }

    private VendingMachine() {
        totalMoney = 0;
        inventoryManager = new InventoryManager();
        treasury = new Treasury();
        this.idleState = new IdleState(this);
        this.readyState = new ReadyState(this);
        this.dispenseState = new DispenseState(this);
        this.collectChangeState = new CollectChangeState(this);
        this.currentState = idleState;
    }

    public void selectProduct(Product product) {
        currentState.selectProduct(product);
    }

    public void insertMoney(Money money) {
        currentState.insertMoney(money);
    }

    public void dispenseProduct() {
        currentState.dispenseProduct();
    }

    public void returnChange() {
        currentState.returnChange();
    }

    public IdleState getIdleState() {
        return idleState;
    }

    public DispenseState getDispenseState() {
        return dispenseState;
    }

    public ReadyState getReadyState() {
        return readyState;
    }

    public CollectChangeState getCollectChangeState() {
        return collectChangeState;
    }

    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }

    public Treasury getTreasury() {
        return treasury;
    }

    public VMState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(VMState currentState) {
        this.currentState = currentState;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public void updateTotalMoney(Money money) {
        totalMoney += money.getValue();
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public void resetTotalMoney() {
        this.totalMoney = 0;
    }
}
