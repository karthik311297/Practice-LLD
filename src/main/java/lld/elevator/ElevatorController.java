package lld.elevator;

import lld.elevator.request.*;

import java.util.PriorityQueue;

public class ElevatorController {
    private PriorityQueue<Request> upRequestsMinHeap;
    private PriorityQueue<Request> downRequestsMaxHeap;
    private ElevatorCar elevatorCar;
    private final Object condition = new Object();

    public ElevatorController(ElevatorCar elevatorCar) {
        this.elevatorCar = elevatorCar;
        this.upRequestsMinHeap = new PriorityQueue<>((a, b) -> a.getFloor() - b.getFloor());
        this.downRequestsMaxHeap = new PriorityQueue<>((a, b) -> b.getFloor() - a.getFloor());
    }

    public void runElevator() throws InterruptedException {
        while (true) {
            while (!upRequestsMinHeap.isEmpty() || !downRequestsMaxHeap.isEmpty()) {
                processRequests();
            }
            synchronized (condition)
            {
                System.out.println("Elevator is idle at floor " + this.elevatorCar.getCurFloor() + " and waiting for requests");
                this.elevatorCar.setCurDir(Direction.NONE);
                condition.wait();
            }
        }
    }

    private synchronized void processRequests() {
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
            Request request = upRequestsMinHeap.poll();
            this.elevatorCar.setCurFloor(request.getFloor());
            System.out.println("Elevator is at floor " + this.elevatorCar.getCurFloor());
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
            Request request = downRequestsMaxHeap.poll();
            this.elevatorCar.setCurFloor(request.getFloor());
            System.out.println("Elevator is at floor " + this.elevatorCar.getCurFloor());
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
        synchronized (condition) {
            condition.notifyAll();
        }
    }

    public synchronized void addDownRequest(Request request) {
        downRequestsMaxHeap.add(request);
        synchronized (condition) {
            condition.notifyAll();
        }
    }

}
