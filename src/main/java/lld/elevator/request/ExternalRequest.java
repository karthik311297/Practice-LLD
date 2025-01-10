package lld.elevator.request;

import lld.elevator.Direction;
import lld.elevator.User;

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
