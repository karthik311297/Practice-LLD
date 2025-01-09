package lld.elevator3;

public class Elevator3Demo {

    /*
    Reference for implementation - https://tedweishiwang.github.io/journal/object-oriented-design-elevator.html
     */

    public static void main(String[] args) {
        ElevatorCar elevatorCar = new ElevatorCar(10);
        elevatorCar.setCurDir(Direction.DOWN);
        ElevatorController elevatorController = new ElevatorController(elevatorCar);
        Building building = new Building(10, elevatorController);

        building.getFloor(6).clickDown(new User(1));
        building.getFloor(2).clickUp(new User(8));
        building.getFloor(4).clickUp(new User(9));
        building.getFloor(10).clickUp(new User(15));
        building.getFloor(7).clickDown(new User(3));

        building.getElevatorController().runElevator();
    }
}
