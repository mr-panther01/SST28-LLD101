package PEN;

abstract class Pen {
    private String name;
    private String brand;
    private double price;
    private Refill refill;
    private boolean isOpen = false;

    public Pen(String name, String brand, Refill refill) {
        this.name = name;
        this.brand = brand;
        this.refill = refill;
    }

    public void start() {
        this.isOpen = true;
        System.out.println(name + " is now open and ready to write.");
    }

    public void close() {
        this.isOpen = false;
        System.out.println(name + " is now closed.");
    }

    public void refill(Refill newRefill) {
        if (this.refill.isRefillable()) {
            this.refill = newRefill;
            System.out.println("Pen refilled successfully.");
        } else {
            System.out.println("This pen is use-and-throw; it cannot be refilled.");
        }
    }

    public abstract void write();

    // Getters and Setters
    public Refill getRefill() { return refill; }
    public boolean isOpen() { return isOpen; }
}