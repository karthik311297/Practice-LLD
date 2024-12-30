package lld.elevator2;

import java.util.PriorityQueue;
import java.util.Queue;

public class LOOKElevatorRequestProcessor implements ElevatorRequestProcessor{

    private PriorityQueue<Request> upMinHeap;
    private PriorityQueue<Request> downMaxHeap;
    private Queue<Request> pending;
    private final ElevatorController elevatorController;

    public LOOKElevatorRequestProcessor(ElevatorController elevatorController) {
        this.elevatorController = elevatorController;
    }

    @Override
    public void takeRequest(FloorRequest floorRequest, int destFloor) {
        synchronized (this) {
            if (elevatorController.getElevator().getCurrentDirection() == Direction.IDLE) {
                if (floorRequest.getDirection() == Direction.DOWN) {
                    downMaxHeap.add(new Request(floorRequest.getFloor(), destFloor));
                } else if (floorRequest.getDirection() == Direction.UP) {
                    upMinHeap.add(new Request(floorRequest.getFloor(), destFloor));
                }
            } else if (elevatorController.getElevator().getCurrentDirection() == Direction.UP) {
                if (floorRequest.getDirection() == Direction.DOWN || (floorRequest.getDirection() == Direction.UP
                        && floorRequest.getFloor() < elevatorController.getElevator().getCurrentFloor())) {
                    pending.add(new Request(floorRequest.getFloor(), destFloor));
                } else {
                    upMinHeap.add(new Request(floorRequest.getFloor(), destFloor));
                }
            } else if (elevatorController.getElevator().getCurrentDirection() == Direction.DOWN) {
                if (floorRequest.getDirection() == Direction.UP || (floorRequest.getDirection() == Direction.DOWN
                        && floorRequest.getFloor() > elevatorController.getElevator().getCurrentFloor())) {
                    pending.add(new Request(floorRequest.getFloor(), destFloor));
                } else {
                    downMaxHeap.add(new Request(floorRequest.getFloor(), destFloor));
                }
            }
        }
    }

    public Request getNextRequest()
    {
        if (elevatorController.getElevator().getCurrentDirection() == Direction.UP)
        {
            if(upMinHeap.isEmpty())
            {

            }
            return upMinHeap.poll();
        }
        else if (elevatorController.getElevator().getCurrentDirection() == Direction.DOWN)
        {
            if(downMaxHeap.isEmpty())
            {

            }
            return downMaxHeap.poll();
        }
        return null;
    }
}
