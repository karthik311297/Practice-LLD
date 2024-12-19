package lld.atm;

import lld.atm.exception.InsufficientFundsException;
import lld.atm.exception.InvalidCredentialException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankService
{
    private static final BankService INSTANCE = new BankService();
    private Map<String, Account> accountDetails;
    private Map<String, byte[]> accountPins;

    private BankService()
    {
        accountDetails = new HashMap<>();
        accountPins = new HashMap<>();
    }

    public static BankService getInstance()
    {
        return INSTANCE;
    }

    public long getBalance(String accountNumber, byte[] pin) throws InvalidCredentialException
    {
        validatePin(pin, accountNumber);
        return accountDetails.get(accountNumber).getBalance();
    }

    public void withdraw(String accountNumber, byte[] pin, long withdrawAmount) throws InsufficientFundsException, InvalidCredentialException
    {
        validatePin(pin, accountNumber);
        Account account = accountDetails.get(accountNumber);
        synchronized (account)
        {
            long curBalance = account.getBalance();
            if (withdrawAmount <= curBalance) {
                curBalance -= withdrawAmount;
                account.setBalance(curBalance);
                accountDetails.put(accountNumber, account);
            }
            else
            {
                throw new InsufficientFundsException("");
            }
        }
    }

    public void deposit(String accountNumber, byte[] pin, long depositAmount) throws InvalidCredentialException
    {
        validatePin(pin, accountNumber);
        Account account = accountDetails.get(accountNumber);
        synchronized (account)
        {
            long curBalance = account.getBalance();
            curBalance += depositAmount;
            account.setBalance(curBalance);
            accountDetails.put(accountNumber, account);
        }
    }

    private void validatePin(byte[] pin, String accountNumber) throws InvalidCredentialException
    {
        if(!Arrays.equals(pin, accountPins.get(accountNumber))) throw new InvalidCredentialException("");
    }

    // Only for testing
    public void bulkAddAccounts(List<Account> accounts, List<byte[]> pins)
    {
        for (int i = 0;i<accounts.size();i++)
        {
            Account a = accounts.get(i);
            byte[] pin = pins.get(i);
            accountDetails.put(a.getAccountNumber(), a);
            accountPins.put(a.getAccountNumber(), pin);
        }
    }
}
