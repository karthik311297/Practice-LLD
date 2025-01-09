package lld.elevator3;

import lld.elevator3.request.*;

import java.util.PriorityQueue;

public class ElevatorController {
    private PriorityQueue<Request> upRequestsMinHeap;
    private PriorityQueue<Request> downRequestsMaxHeap;
    private ElevatorCar elevatorCar;

    public ElevatorController(ElevatorCar elevatorCar) {
        this.elevatorCar = elevatorCar;
        this.upRequestsMinHeap = new PriorityQueue<>((a, b) -> a.getFloor() - b.getFloor());
        this.downRequestsMaxHeap = new PriorityQueue<>((a, b) -> b.getFloor() - a.getFloor());
    }

    public void runElevator() {
        while (!upRequestsMinHeap.isEmpty() || !downRequestsMaxHeap.isEmpty()) {
            processRequests();
        }
        System.out.println("Elevator reached floor" + this.elevatorCar.getCurFloor());
        this.elevatorCar.setCurDir(Direction.NONE);
    }

    private void processRequests() {
        if (this.elevatorCar.getCurDir() == Direction.UP || this.elevatorCar.getCurDir() == Direction.NONE) {
            this.elevatorCar.setCurDir(Direction.UP);
            processUpRequests();
            processDownRequests();
        } else {
            this.elevatorCar.setCurDir(Direction.DOWN);
            processDownRequests();
            processUpRequests();
        }
    }

    private void processUpRequests() {
        while (!upRequestsMinHeap.isEmpty()) {
            System.out.println("Elevator reached floor" + this.elevatorCar.getCurFloor());
            Request request = upRequestsMinHeap.poll();
            this.elevatorCar.setCurFloor(request.getFloor());
            if (request.getRequestType() == RequestType.EXTERNAL) {
                clickButton((ExternalRequest) request, Direction.UP);
            }
        }
        if (!downRequestsMaxHeap.isEmpty()) {
            this.elevatorCar.setCurDir(Direction.DOWN);
        } else {
            this.elevatorCar.setCurDir(Direction.NONE);
        }
    }

    private void processDownRequests() {
        while (!downRequestsMaxHeap.isEmpty()) {
            System.out.println("Elevator reached floor" + this.elevatorCar.getCurFloor());
            Request request = downRequestsMaxHeap.poll();
            this.elevatorCar.setCurFloor(request.getFloor());
            if (request.getRequestType() == RequestType.EXTERNAL) {
                clickButton((ExternalRequest) request, Direction.DOWN);
            }
        }
        if (!upRequestsMinHeap.isEmpty()) {
            this.elevatorCar.setCurDir(Direction.UP);
        } else {
            this.elevatorCar.setCurDir(Direction.NONE);
        }
    }

    private void clickButton(ExternalRequest externalRequest, Direction direction) {
        if (direction == Direction.UP) {
            upRequestsMinHeap.add(new InternalRequest(externalRequest.getUser().getFloorInMind(), Direction.UP));
        } else if (direction == Direction.DOWN) {
            downRequestsMaxHeap.add(new InternalRequest(externalRequest.getUser().getFloorInMind(), Direction.DOWN));
        }
    }

    public synchronized void addUpRequest(Request request) {
        upRequestsMinHeap.add(request);
    }

    public synchronized void addDownRequest(Request request) {
        downRequestsMaxHeap.add(request);
    }

}
