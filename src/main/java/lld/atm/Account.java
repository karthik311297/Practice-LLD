package lld.atm;

import java.io.Serializable;

public class Account implements Serializable {

    private long balance;
    private String accountNumber;

    public Account(long balance, String accountNumber) {
        this.balance = balance;
        this.accountNumber = accountNumber;
    }

    public long getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
