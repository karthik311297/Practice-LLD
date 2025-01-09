package lld.elevator3.request;

import lld.elevator3.Direction;
import lld.elevator3.User;

public class ExternalRequest extends Request {

    private User user;

    public ExternalRequest(int floor, Direction direction, User user) {
        super(floor, direction, RequestType.EXTERNAL);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
