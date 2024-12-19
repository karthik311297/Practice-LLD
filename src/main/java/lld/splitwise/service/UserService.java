package lld.splitwise.service;

import lld.splitwise.User;

import java.util.HashMap;
import java.util.Map;

public class UserService {

    private final Map<String, User> userMap = new HashMap<>();
    private static final UserService INSTANCE = new UserService();

    private UserService() {
    }

    public static UserService getInstance() {
        return INSTANCE;
    }

    public void showUserBalances(String userId)
    {
        User user = userMap.get(userId);
        Map<String, Integer> owesAmountTo = user.getOwesAmountTo();
        Map<String, Integer> receivesAmountFrom = user.getReceivesAmountFrom();
        System.out.println("User balances for user " + userId + "  ");
        System.out.println("----------Owes-----------");
        for(Map.Entry<String, Integer> e : owesAmountTo.entrySet())
        {
            System.out.println("Owes Rs." + e.getValue() + " to user " + e.getKey());
        }
        System.out.println("----------Receives-----------");
        for(Map.Entry<String, Integer> e : receivesAmountFrom.entrySet())
        {
            System.out.println("Has to receive Rs." + e.getValue() + " from user " + e.getKey());
        }
        System.out.println("\n");
    }

    public synchronized User createUser(String userId)
    {
        User user = new User(userId);
        userMap.put(userId, user);
        return user;
    }

    public User getUser(String userId)
    {
        return userMap.get(userId);
    }
}
