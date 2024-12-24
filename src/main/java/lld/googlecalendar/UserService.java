package lld.googlecalendar;

import java.util.HashMap;
import java.util.Map;

public class UserService {

    private final Map<String, User> userMap;
    private static final UserService instance = new UserService();

    private UserService() {
        this.userMap = new HashMap<>();
    }

    public static UserService getInstance() {
        return instance;
    }

    public void createUser(String emailId) {
        User user = new User(emailId);
        userMap.put(emailId, user);
    }

    public User getUser(String emailId) {
        return userMap.get(emailId);
    }
}
