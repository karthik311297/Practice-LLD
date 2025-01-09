package lld.elevator3;

import java.util.ArrayList;
import java.util.List;

public class Building {
    private List<Floor> floors;
    private ElevatorController elevatorController;

    public Building(int numFloors, ElevatorController elevatorController) {
        this.elevatorController = elevatorController;
        this.floors = new ArrayList<>();
        for (int i = 0; i <= numFloors; i++) floors.add(new Floor(i, this));
    }

    public Floor getFloor(int floorNumber) {
        return floors.get(floorNumber);
    }

    public ElevatorController getElevatorController() {
        return elevatorController;
    }
}
