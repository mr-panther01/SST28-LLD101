package elevatorSystem;
import java.util.List;

public class ElevatorDispatcher {
    private List<Elevator> elevators;

    public ElevatorDispatcher(List<Elevator> elevators) {
        this.elevators = elevators;
    }

    public void submitHallRequest(Request request) {
        Elevator bestElevator = findOptimalElevator(request);
        if (bestElevator != null) {
            bestElevator.addRequest(request);
            System.out.println("Request assigned to Elevator " + bestElevator.getId());
        } else {
            System.out.println("No elevators available. Please wait.");
        }
    }

    private Elevator findOptimalElevator(Request request) {
        Elevator optimal = null;
        int minDistance = Integer.MAX_VALUE;

        for (Elevator elevator : elevators) {
            if (elevator.getState() == ElevatorState.MAINTENANCE) continue;

            int distance = Math.abs(elevator.getCurrentFloor() - request.getCurrentFloor());

            // Assign if IDLE
            if (elevator.getCurrentDirection() == Direction.IDLE) {
                if (distance < minDistance) {
                    minDistance = distance;
                    optimal = elevator;
                }
            } 
            // Assign if moving in the SAME direction AND is approaching the user's floor
            else if (elevator.getCurrentDirection() == request.getDirection()) {
                boolean approachingUp = (request.getDirection() == Direction.UP && elevator.getCurrentFloor() <= request.getCurrentFloor());
                boolean approachingDown = (request.getDirection() == Direction.DOWN && elevator.getCurrentFloor() >= request.getCurrentFloor());
                
                if (approachingUp || approachingDown) {
                    if (distance < minDistance) {
                        minDistance = distance;
                        optimal = elevator;
                    }
                }
            }
        }
        return optimal;
    }
}