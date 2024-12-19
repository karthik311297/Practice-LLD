package lld.elevator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElevatorDemo {

    /*
    The elevator system should consist of multiple elevators serving multiple floors.
    Each elevator should have a capacity limit and should not exceed it.
    Users should be able to request an elevator from any floor and select a destination floor.
    The elevator system should efficiently handle user requests and optimize the movement of elevators to minimize waiting time.
    The system should prioritize requests based on the direction of travel and the proximity of the elevators to the requested floor.
    The elevators should be able to handle multiple requests concurrently and process them in an optimal order.
    The system should ensure thread safety and prevent race conditions when multiple threads interact with the elevators.
     */

    /*
     ElevatorSystem - List<Elevator> eves
        requestElevator(Request r)
        boardElevator(Elevator e) - updates elevator capacity
        exitElevator(Elevator e) - updates elevator capacity

     Elevator - current floor, capacity

     Direction - up, down

     Request - src floor, dest floor, id


     */
    public static void main(String[] args) {
    }
}
