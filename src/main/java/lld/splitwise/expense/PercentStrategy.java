package lld.splitwise.expense;

import lld.splitwise.User;
import lld.splitwise.expense.exception.InvalidSplitException;
import lld.splitwise.value.Value;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PercentStrategy implements SplitStrategy{

    @Override
    public Map<String, Integer> splitAndReturnBalances(User payer, List<User> users, int netAmount,
                                                       Optional<Map<String, Value>> userOweValues) throws InvalidSplitException
    {
        if(userOweValues.isEmpty()) {
            throw new InvalidSplitException("Percent split strategy requires each users owe percentage to be specified");
        }
        Map<String, Integer> splitMap = new HashMap<>();
        Map<String, Value> userOweValuesMap = userOweValues.get();
        int totalPercent = userOweValuesMap.values()
                .stream()
                .map(Value::get)
                .mapToInt(Integer::intValue)
                .sum();
        if (totalPercent >= 100) {
            throw new InvalidSplitException("Percent split strategy net percent improper");
        }
        for(User u : users)
        {
            if (u.getId().equals(payer.getId())) continue;
            int per = userOweValuesMap.get(u.getId()).get();
            int amt = (per*netAmount/100);
            splitMap.put(u.getId(),amt);
        }
        return splitMap;
    }
}
