package lld.elevator;

import lld.elevator.strategy.ClosestElevatorStrategy;
import lld.elevator.strategy.ElevatorFindStrategy;

import java.util.ArrayList;
import java.util.List;

public class ElevatorSystem {

    private List<Elevator> elevators;
    private ElevatorFindStrategy elevatorFindStrategy;
    private static final ElevatorSystem INSTANCE = new ElevatorSystem();

    private ElevatorSystem()
    {
        this.elevators = new ArrayList<>();
        this.elevatorFindStrategy = new ClosestElevatorStrategy();
    }

    public static ElevatorSystem getInstance()
    {
        return INSTANCE;
    }

    public void addNewElevator(Elevator elevator)
    {
        elevators.add(elevator);
        elevator.start();
    }

    public Elevator findBestElevator(Request request)
    {
        return elevatorFindStrategy.findBestElevator(this.elevators, request);
    }
}
