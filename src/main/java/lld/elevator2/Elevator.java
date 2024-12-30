package lld.elevator2;

public class Elevator {

    private int numFloors;
    private int capacity;
    private int currentLoad;
    private int currentFloor;
    private Direction currentDirection;

    public Elevator(int numFloors, int capacity) {
        this.numFloors = numFloors;
        this.capacity = capacity;
        this.currentFloor = 0;
        this.currentLoad = 0;
        this.currentDirection = Direction.IDLE;
    }

    public int getNumFloors() {
        return numFloors;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCurrentLoad() {
        return currentLoad;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }
}
