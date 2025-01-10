package lld.elevator;

public class ElevatorCar {

    private Direction curDir;

    private int curFloor;

    private int capacity;

    public ElevatorCar(int capacity) {
        this.capacity = capacity;
        this.curDir = Direction.NONE;
        this.curFloor = 0;
    }

    public Direction getCurDir() {
        return curDir;
    }

    public void setCurDir(Direction curDir) {
        this.curDir = curDir;
    }

    public int getCurFloor() {
        return curFloor;
    }

    public void setCurFloor(int curFloor) {
        this.curFloor = curFloor;
    }

    public int getCapacity() {
        return capacity;
    }
}
