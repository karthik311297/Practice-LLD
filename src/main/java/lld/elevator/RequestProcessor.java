package lld.elevator;

import java.util.LinkedList;
import java.util.Queue;

public class RequestProcessor implements Runnable {

    private final Elevator elevator;
    private final Queue<Request> requests;
    private final Object condition = new Object();
    private final Object condition2 = new Object();

    public RequestProcessor(Elevator elevator) {
        this.elevator = elevator;
        this.requests = new LinkedList<>();
    }

    @Override
    public void run() {
        while (true) {
            while (requests.isEmpty()) {
                synchronized (condition) {
                    System.out.println("Elevator " + elevator.hashCode() + " is idle");
                    waitOnCondition();
                }
            }
            synchronized (condition2) {
                Request request = requests.poll();
                processRequest(request);
                condition2.notifyAll();
            }
        }
    }

    private void waitOnCondition() {
        try {
            condition.wait();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void waitOnCondition2() {
        try {
            condition2.wait();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public void takeRequest(Request request) {
        while (isAtCapacity()) {
            System.out.println("Elevator " + elevator.hashCode() + " is at capacity");
            synchronized (condition2) {
                waitOnCondition2();
            }
        }
        synchronized (condition) {
            requests.add(request);
            condition.notifyAll();
        }
    }

    public boolean isAtCapacity() {
        return requests.size() == elevator.getCapacity();
    }

    private void processRequest(Request request) {
        int start = request.getSourceFloor();
        int end = request.getDestinationFloor();
        if (start < end) {
            elevator.setCurrentDirection(Direction.UP);
            for (int i = start; i < end; i++) {
                System.out.println("Elevator " + elevator.hashCode() + " at floor : " + i);
                elevator.setCurrentFloor(i);
                sleep();
            }
            System.out.println("Elevator " + elevator.hashCode() + " at floor : " + end);
            elevator.setCurrentFloor(end);
            elevator.setCurrentDirection(Direction.IDLE);
        } else {
            elevator.setCurrentDirection(Direction.DOWN);
            for (int i = start; i > end; i--) {
                elevator.setCurrentFloor(i);
                System.out.println("Elevator " + elevator.hashCode() + " at floor : " + i);
                sleep();
            }
            System.out.println("Elevator " + elevator.hashCode() + " at floor : " + end);
            elevator.setCurrentFloor(end);
            elevator.setCurrentDirection(Direction.IDLE);
        }
    }

    private static void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
