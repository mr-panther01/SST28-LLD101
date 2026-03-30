package elevatorSystem;

import java.util.TreeSet;

public class Elevator {
    private String id;
    private int currentFloor;
    private Direction currentDirection;
    private ElevatorState state;
    private DoorState doorState;
    
    // Up requests sorted ascending, Down requests sorted descending
    private TreeSet<Integer> upRequests;
    private TreeSet<Integer> downRequests;

    public Elevator(String id) {
        this.id = id;
        this.currentFloor = 0; // Ground floor
        this.currentDirection = Direction.IDLE;
        this.state = ElevatorState.IDLE;
        this.doorState = DoorState.CLOSED;
        this.upRequests = new TreeSet<>();
        this.downRequests = new TreeSet<>((a, b) -> b.compareTo(a));
    }

    public synchronized void addRequest(Request request) {
        if (request.getDirection() == Direction.UP) {
            upRequests.add(request.getDesiredFloor());
        } else {
            downRequests.add(request.getDesiredFloor());
        }
        // Wake up the elevator thread if it was idle
        notifyAll(); 
    }

    public void run() {
        while (true) {
            synchronized (this) {
                while (upRequests.isEmpty() && downRequests.isEmpty()) {
                    try {
                        this.currentDirection = Direction.IDLE;
                        this.state = ElevatorState.IDLE;
                        wait(); // Wait for new requests
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }
            processRequests();
        }
    }

    private void processRequests() {
        if (currentDirection == Direction.UP || currentDirection == Direction.IDLE) {
            processUpRequests();
            processDownRequests();
        } else {
            processDownRequests();
            processUpRequests();
        }
    }

    private void processUpRequests() {
        while (!upRequests.isEmpty()) {
            int targetFloor = upRequests.pollFirst();
            moveToFloor(targetFloor);
        }
        if (!downRequests.isEmpty()) {
            currentDirection = Direction.DOWN;
        }
    }

    private void processDownRequests() {
        while (!downRequests.isEmpty()) {
            int targetFloor = downRequests.pollFirst();
            moveToFloor(targetFloor);
        }
        if (!upRequests.isEmpty()) {
            currentDirection = Direction.UP;
        }
    }

    private void moveToFloor(int targetFloor) {
        this.state = ElevatorState.MOVING;
        System.out.println("Elevator " + id + " moving to floor " + targetFloor);
        this.currentFloor = targetFloor;
        this.state = ElevatorState.STOPPED;
        operateDoors();
    }

    private void operateDoors() {
        this.doorState = DoorState.OPEN;
        System.out.println("Elevator " + id + " doors OPEN at floor " + currentFloor);
        // Simulate boarding time
        this.doorState = DoorState.CLOSED;
        System.out.println("Elevator " + id + " doors CLOSED.");
    }

    // Getters needed for the Dispatcher
    public String getId() { return id; }
    public int getCurrentFloor() { return currentFloor; }
    public Direction getCurrentDirection() { return currentDirection; }
    public ElevatorState getState() { return state; }
}