package lld.splitwise.service;

import lld.splitwise.Expense;
import lld.splitwise.Group;
import lld.splitwise.User;
import lld.splitwise.expense.SplitStrategy;
import lld.splitwise.expense.exception.InvalidSplitException;
import lld.splitwise.value.Value;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class GroupService {

    private final ReentrantLock groupExpenseMapLock = new ReentrantLock();
    private final Map<String, Group> groupMap = new HashMap<>();
    private final Map<String, List<Expense>> groupExpensesMap = new HashMap<>();
    private static final GroupService INSTANCE = new GroupService();

    private GroupService() {
    }

    public static GroupService getInstance() {
        return INSTANCE;
    }

    public synchronized Group createGroup(String groupId, List<User> users) {
        Group group = new Group(groupId);
        group.addUsers(users);
        groupMap.put(groupId, group);
        return group;
    }

    private List<User> getGroupUsers(String groupId)
    {
        return groupMap.get(groupId)
                .getUsers();
    }

    public void addExpense(String groupId, int amount, User paidBy, SplitStrategy splitStrategy, Map<String, Value> userOweValues)
            throws InvalidSplitException {
        List<User> groupUsers = getGroupUsers(groupId);
        Expense expense = new Expense(splitStrategy, paidBy, amount, groupUsers);
        expense.setUserOweValues(userOweValues);
        Map<String, Integer> balances = expense.getUserBalances();
        for(Map.Entry<String, Integer> e : balances.entrySet())
        {
            String userID = e.getKey();
            int amt = e.getValue();
            User user = UserService.getInstance().getUser(userID);
            user.updateOwesAmount(paidBy.getId(), amt);
            paidBy.updateReceivesAmount(userID, amt);
        }
        try
        {
            groupExpenseMapLock.lock();
            if(!groupExpensesMap.containsKey(groupId)) groupExpensesMap.put(groupId, new ArrayList<>());
            groupExpensesMap.get(groupId).add(expense);
        }
        finally
        {
            groupExpenseMapLock.unlock();
        }
    }

    public void showGroupExpenses(String groupId)
    {
        System.out.println("Group Expenses for group " + groupId);
        System.out.println("--------------------");
        List<Expense> expenses = groupExpensesMap.get(groupId);
        for(Expense e : expenses)
        {
            System.out.println(e);
        }
        System.out.println("\n");
    }
}
