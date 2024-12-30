package lld.elevator2;

public interface ElevatorRequestProcessor {
    void takeRequest(FloorRequest floorRequest, int destFloor);
}
