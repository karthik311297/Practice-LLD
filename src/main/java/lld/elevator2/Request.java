package lld.elevator2;

public class Request {
    private int sourceFloor;
    private int destFloor;

    public Request(int sourceFloor, int destFloor) {
        this.sourceFloor = sourceFloor;
        this.destFloor = destFloor;
    }

    public int getSourceFloor() {
        return sourceFloor;
    }

    public int getDestFloor() {
        return destFloor;
    }
}
