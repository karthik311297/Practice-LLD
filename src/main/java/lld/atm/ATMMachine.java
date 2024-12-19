package lld.atm;

import lld.atm.exception.InsufficientFundsException;
import lld.atm.exception.InvalidCredentialException;

import java.nio.charset.StandardCharsets;

public class ATMMachine
{
    private CashDispenser dispenser;
    private BankService bankService;
    private static final ATMMachine INSTANCE = new ATMMachine();

    public ATMMachine()
    {
        dispenser = new CashDispenser();
        bankService = BankService.getInstance();
    }

    public static ATMMachine getInstance()
    {
        return INSTANCE;
    }

    public void getBalance(String accountNumber, String pin)
    {
        try
        {
            long bal = bankService.getBalance(accountNumber, pin.getBytes(StandardCharsets.UTF_8));
            System.out.println("Your Balance: " + bal);
        }
        catch (InvalidCredentialException e)
        {
            System.out.println("Invalid pin");
        }
    }

    public void withdraw(String accountNumber, String pin, long withdrawAmount)
    {
        try
        {
            bankService.withdraw(accountNumber, pin.getBytes(StandardCharsets.UTF_8), withdrawAmount);
            dispenser.dipense(withdrawAmount);
        }
        catch (InvalidCredentialException e)
        {
            System.out.println("Invalid pin");
        }
        catch (InsufficientFundsException e)
        {
            System.out.println("Insufficient funds");
        }
    }

    public void deposit(String accountNumber, String pin,long depositAmount)
    {
        try
        {
            bankService.deposit(accountNumber, pin.getBytes(StandardCharsets.UTF_8), depositAmount);
            System.out.println("Amount deposited successfully");
        }
        catch (InvalidCredentialException e)
        {
            System.out.println("Invalid pin");
        }
    }
}
