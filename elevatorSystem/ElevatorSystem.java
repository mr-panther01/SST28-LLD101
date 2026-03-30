package elevatorSystem;


import java.util.ArrayList;
import java.util.List;

public class ElevatorSystem {
    private List<Elevator> elevators;
    private ElevatorDispatcher dispatcher;

    public ElevatorSystem(int numberOfElevators) {
        elevators = new ArrayList<>();
        for (int i = 0; i < numberOfElevators; i++) {
            Elevator elevator = new Elevator("E" + (i + 1));
            elevators.add(elevator);
            // In a real system, each elevator would run on its own thread
            new Thread(elevator::run).start(); 
        }
        dispatcher = new ElevatorDispatcher(elevators);
    }

    // Called when a user presses UP/DOWN in the hallway
    public void pressHallButton(int floor, Direction direction) {
        Request req = new Request(floor, floor, direction); // Hall requests want to be picked up at 'floor'
        dispatcher.submitHallRequest(req);
    }

    // Called when a user inside the elevator presses a destination floor number
    public void pressElevatorButton(String elevatorId, int destinationFloor) {
        Elevator elevator = getElevatorById(elevatorId);
        if (elevator != null) {
            Direction dir = (destinationFloor > elevator.getCurrentFloor()) ? Direction.UP : Direction.DOWN;
            Request req = new Request(elevator.getCurrentFloor(), destinationFloor, dir);
            elevator.addRequest(req);
        }
    }

    private Elevator getElevatorById(String id) {
        return elevators.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}