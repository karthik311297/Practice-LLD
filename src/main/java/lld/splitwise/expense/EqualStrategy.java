package lld.splitwise.expense;

import lld.splitwise.User;
import lld.splitwise.value.Value;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class EqualStrategy implements SplitStrategy{

    @Override
    public Map<String, Integer> splitAndReturnBalances(User payer, List<User> users, int netAmount,
                                                       Optional<Map<String, Value>> userOweValues) {

        Map<String, Integer> splitMap = new HashMap<>();
        int size = users.size();
        int oweAmount = netAmount/size;
        for(User u : users)
        {
            if(u.getId().equals(payer.getId())) continue;
            splitMap.put(u.getId(), oweAmount);
        }
        return splitMap;
    }
}
