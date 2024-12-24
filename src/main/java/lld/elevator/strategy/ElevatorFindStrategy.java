package lld.elevator.strategy;

import lld.elevator.Elevator;
import lld.elevator.Request;

import java.util.List;

public interface ElevatorFindStrategy {

    Elevator findBestElevator(List<Elevator> elevators, Request request);
}
