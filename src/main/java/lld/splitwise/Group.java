package lld.splitwise;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private String id;
    private List<User> users;

    public Group(String id) {
        this.id = id;
        users = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void addUsers(List<User> users) {
        this.users.addAll(users);
    }

    public List<User> getUsers() {
        return users;
    }
}
