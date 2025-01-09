package lld.elevator3.request;

import lld.elevator3.Direction;

public abstract class Request {
    private int floor;
    private Direction direction;
    private RequestType requestType;

    public Request(int floor, Direction direction, RequestType requestType) {
        this.floor = floor;
        this.direction = direction;
        this.requestType = requestType;
    }

    public int getFloor() {
        return floor;
    }

    public Direction getDirection() {
        return direction;
    }

    public RequestType getRequestType() {
        return requestType;
    }
}
