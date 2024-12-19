package lld.splitwise;

import lld.splitwise.expense.SplitStrategy;
import lld.splitwise.expense.exception.InvalidSplitException;
import lld.splitwise.value.Value;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Expense {
    private SplitStrategy splitStrategy;
    private User paidBy;
    private int netAmount;
    private List<User> usersInvolved;
    private Optional<Map<String, Value>> userOweValues;

    public Expense(SplitStrategy splitStrategy, User paidBy, int netAmount, List<User> usersInvolved) {
        this.splitStrategy = splitStrategy;
        this.paidBy = paidBy;
        this.netAmount = netAmount;
        this.usersInvolved = usersInvolved;
    }

    public void setUserOweValues(Map<String, Value> userOweValuesMap) {
        this.userOweValues = Optional.ofNullable(userOweValuesMap);
    }

    public SplitStrategy getSplitStrategy() {
        return splitStrategy;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public int getNetAmount() {
        return netAmount;
    }

    public List<User> getUsersInvolved() {
        return usersInvolved;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "splitStrategy=" + splitStrategy.getClass().getSimpleName() +
                ", paidBy=" + paidBy +
                ", netAmount=" + netAmount +
                ", usersInvolved=[" + String.join(", ", usersInvolved
                .stream()
                .map(User::toString)
                .collect(Collectors.joining(", "))) + "]" +
                '}';
    }

    public Map<String, Integer> getUserBalances() throws InvalidSplitException {
        return splitStrategy.splitAndReturnBalances(paidBy, usersInvolved, netAmount, userOweValues);
    }
}
