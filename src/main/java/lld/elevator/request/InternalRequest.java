package lld.elevator.request;

import lld.elevator.Direction;

public class InternalRequest extends Request {

    public InternalRequest(int floor, Direction direction) {
        super(floor, direction, RequestType.INTERNAL);
    }
}
