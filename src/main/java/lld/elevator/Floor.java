package lld.elevator;

import lld.elevator.request.ExternalRequest;

public class Floor {
    private int number;
    private Building building;

    public Floor(int number, Building building) {
        this.number = number;
        this.building = building;
    }

    public int getNumber() {
        return number;
    }

    public void clickUp(User user)
    {
        building.getElevatorController().addUpRequest(new ExternalRequest(number, Direction.UP, user));
    }

    public void clickDown(User user)
    {
        building.getElevatorController().addDownRequest(new ExternalRequest(number, Direction.DOWN, user));
    }
}
