package lld.elevator.strategy;

import lld.elevator.Elevator;
import lld.elevator.Request;

import java.util.List;

public class ClosestElevatorStrategy implements ElevatorFindStrategy {

    @Override
    public Elevator findBestElevator(List<Elevator> elevators, Request request) {
        Elevator best = elevators.get(0);
        int start = request.getSourceFloor();
        int dist = Integer.MAX_VALUE;
        for(Elevator e : elevators)
        {
            if((!e.isAtCapacity()) && Math.abs(e.getCurrentFloor() - start) < dist)
            {
                dist = Math.min(dist, Math.abs(e.getCurrentFloor() - start));
                best = e;
            }
        }
        return best;
    }
}
