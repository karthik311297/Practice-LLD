package lld.elevator;

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
        ElevatorSystem elevatorSystem = ElevatorSystem.getInstance();
        Elevator elevator1 = new Elevator(2);
        Elevator elevator2 = new Elevator(1);
        Request request1 = new Request(0, 3);
        Request request2 = new Request(3, 1);
        Request request3 = new Request(3, 8);
        Request request4 = new Request(6, 11);
        Request request5 = new Request(10, 12);
        elevatorSystem.addNewElevator(elevator1);
        elevatorSystem.addNewElevator(elevator2);

        elevatorSystem.findBestElevator(request1).serveRequest(request1);
        elevatorSystem.findBestElevator(request2).serveRequest(request2);
        elevatorSystem.findBestElevator(request3).serveRequest(request3);
        elevatorSystem.findBestElevator(request4).serveRequest(request4);
        elevatorSystem.findBestElevator(request5).serveRequest(request5);
    }
}
