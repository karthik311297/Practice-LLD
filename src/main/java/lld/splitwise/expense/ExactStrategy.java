package lld.splitwise.expense;

import lld.splitwise.User;
import lld.splitwise.expense.exception.InvalidSplitException;
import lld.splitwise.value.Value;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ExactStrategy implements SplitStrategy{

    @Override
    public Map<String, Integer> splitAndReturnBalances(User payer, List<User> users, int netAmount,
                                                       Optional<Map<String, Value>> userOweValues) throws InvalidSplitException
    {
        if(userOweValues.isEmpty()) {
            throw new InvalidSplitException("Exact split strategy requires each users owe amount to be specified");
        }
        Map<String, Integer> splitMap = new HashMap<>();
        Map<String, Value> userOweValuesMap = userOweValues.get();
        int sum = userOweValuesMap.values()
                .stream()
                .map(Value::get)
                .mapToInt(Integer::intValue)
                .sum();
        if (sum >= netAmount) {
            throw new InvalidSplitException("Exact split strategy net amount improper");
        }
        for(User u : users)
        {
            if (u.getId().equals(payer.getId())) continue;
            splitMap.put(u.getId(),
                    userOweValuesMap.get(u.getId()).get());
        }
        return splitMap;
    }
}
