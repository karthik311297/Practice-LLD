package lld.atm;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ATMSystem
{

    private static final String PIN1 = "1234";
    private static final String PIN2 = "2345";
    private static final String ACC1 = "1";
    private static final String ACC2 =  "2";

    public static void main(String[] args)
    {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<Account> accounts = Arrays.asList(new Account(2001, ACC1), new Account(1000, ACC2));
        List<byte[]> accountPins = Arrays.asList(PIN1.getBytes(StandardCharsets.UTF_8), PIN2.getBytes(StandardCharsets.UTF_8));
        BankService.getInstance().bulkAddAccounts(accounts, accountPins);

        ATMMachine atmMachine = ATMMachine.getInstance();

        atmMachine.getBalance(ACC1, PIN1);

        executorService.submit(() -> {
            atmMachine.withdraw(ACC1, PIN1, 1000);
        });
        executorService.submit(() -> {
            atmMachine.withdraw(ACC1, PIN1, 1000);
        });
        executorService.submit(() -> {
            atmMachine.withdraw(ACC1, PIN1, 1000);
        });
        executorService.submit(() -> {
            atmMachine.withdraw(ACC1, PIN1, 100);
        });

        atmMachine.getBalance(ACC1, PIN1);

        executorService.shutdown();
    }
}
