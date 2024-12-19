package lld.splitwise;

import lld.splitwise.expense.EqualStrategy;
import lld.splitwise.expense.ExactStrategy;
import lld.splitwise.expense.PercentStrategy;
import lld.splitwise.expense.exception.InvalidSplitException;
import lld.splitwise.service.GroupService;
import lld.splitwise.service.UserService;
import lld.splitwise.value.Amount;
import lld.splitwise.value.Percent;

import java.util.Arrays;
import java.util.Map;

public class Demo {
    /*
    The system should allow users to create accounts and manage their profile information.
    Users should be able to create groups and add other users to the groups.
    Users should be able to add expenses within a group, specifying the amount, description, and participants.
    The system should automatically split the expenses among the participants based on their share.
    Users should be able to view their individual balances with other users and settle up the balances.
    The system should support different split methods, such as equal split, percentage split, and exact amounts.
    Users should be able to view their transaction history and group expenses.
    The system should handle concurrent transactions and ensure data consistency.
     */

    /*
    User - id, Map<UserId, Integer> owesAmountTo, Map<UserId, Integer> shouldReceiveAmountFrom

    UserService - Map<UserId, User>;
        createUser(Userid)
        showUserBalances(Userid)

    Group - id, List<User>
        getGroupUsers()

    GroupService - Map<GroupId, List<Expense>>, Map<GroupId, Group>
        createGroup(List<User>)
        addExpense(GroupID, amount, paidBy, SplitStrategy, Map<UserId, Value> userOweValues)
        getGroupExpenses(GroupId)
        getGroupUsers(GroupId)


    Expense - SplitStrategy, amount, paidBy, List<Users> usersInvolved, Map<UserId, Value> userOweValues
        Map<> getUserBalances()

    SplitStrategy
      /    |    \
    Equal Exact Percent
        splitAndReturnBalances(User payer, List<User>, int netAmount, Map<UserId, Value> userOweValues)

    Value - int value
    /   \
Percent Amount
    get();

      */

    public static void main(String[] args) throws InvalidSplitException {
        UserService userService = UserService.getInstance();
        GroupService groupService = GroupService.getInstance();
        User u1 = userService.createUser("u1");
        User u2 = userService.createUser("u2");
        User u3 = userService.createUser("u3");
        groupService.createGroup("g1", Arrays.asList(u1, u2, u3));
        groupService.addExpense("g1", 120, u1, new EqualStrategy(),null);
        groupService.addExpense("g1", 150, u2, new ExactStrategy(), Map.of("u1", new Amount(40), "u3", new Amount(60)));
        groupService.addExpense("g1", 200, u3, new PercentStrategy(), Map.of("u1", new Percent(30), "u2", new Percent(40)));
        groupService.showGroupExpenses("g1");
        userService.showUserBalances("u1");
        userService.showUserBalances("u2");
        userService.showUserBalances("u3");
    }
}
