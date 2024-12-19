package lld.splitwise;

import java.util.HashMap;
import java.util.Map;

public class User {
    private final String id;
    private final Map<String, Integer> owesAmountTo;
    private final Map<String, Integer> receivesAmountFrom;

    public User(String id) {
        this.id = id;
        owesAmountTo = new HashMap<>();
        receivesAmountFrom = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public Map<String, Integer> getReceivesAmountFrom() {
        return receivesAmountFrom;
    }

    public Map<String, Integer> getOwesAmountTo() {
        return owesAmountTo;
    }

    public synchronized void updateOwesAmount(String userId, int amountOwed)
    {
        owesAmountTo.put(userId, owesAmountTo.getOrDefault(userId, 0) + amountOwed);
    }

    public synchronized void updateReceivesAmount(String userId, int amountToBeReceived)
    {
        receivesAmountFrom.put(userId, receivesAmountFrom.getOrDefault(userId, 0) + amountToBeReceived);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                '}';
    }
}
