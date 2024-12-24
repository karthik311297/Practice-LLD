package lld.elevator;

public class Elevator {

    private RequestProcessor requestProcessor;
    private int capacity;

    private int currentFloor;

    private Direction currentDirection;

    public Elevator(int capacity) {
        this.capacity = capacity;
        this.currentFloor = 0;
        this.currentDirection = Direction.IDLE;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(Direction currentDirection) {
        this.currentDirection = currentDirection;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public void start()
    {
        requestProcessor = new RequestProcessor(this);
        new Thread(requestProcessor).start();
    }

    public void serveRequest(Request request)
    {
        requestProcessor.takeRequest(request);
    }

    public boolean isAtCapacity()
    {
        return requestProcessor.isAtCapacity();
    }
}
