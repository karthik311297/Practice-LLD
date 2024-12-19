package lld.splitwise.expense;

import lld.splitwise.User;
import lld.splitwise.expense.exception.InvalidSplitException;
import lld.splitwise.value.Value;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface SplitStrategy {
    Map<String, Integer> splitAndReturnBalances(User payer, List<User> users, int netAmount,
                                                Optional<Map<String, Value>> userOweValues) throws InvalidSplitException;
}
