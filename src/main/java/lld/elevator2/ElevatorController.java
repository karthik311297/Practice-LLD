package lld.elevator2;

import java.util.LinkedList;

public class ElevatorController {

    private Elevator elevator;
    private ElevatorRequestProcessor elevatorRequestProcessor;
    private LinkedList<Request> requestsToProcess;
    private final Object condition = new Object();

    public ElevatorController(Elevator elevator, ElevatorRequestProcessor elevatorRequestProcessor) {
        this.elevator = elevator;
        this.elevatorRequestProcessor = elevatorRequestProcessor;
        this.requestsToProcess = new LinkedList<>();
    }

    public void processElevatorRequest(FloorRequest floorRequest, int destFloor) throws InterruptedException
    {

    }

    public void runElevator() throws InterruptedException {
        while (true)
        {
            while (requestsToProcess.isEmpty())
            {
                waitForRequests();
            }

        }
    }

    private void waitForRequests() throws InterruptedException {
        synchronized (condition) {
            condition.wait();
        }
    }

    private void notifyElevatorToProcessRequest() {
        synchronized (condition) {
            condition.notifyAll();
        }
    }


    public Elevator getElevator() {
        return elevator;
    }
}
