package lld.elevator3.request;

import lld.elevator3.Direction;

public class InternalRequest extends Request {

    public InternalRequest(int floor, Direction direction) {
        super(floor, direction, RequestType.INTERNAL);
    }
}
