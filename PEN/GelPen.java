package PEN;

class GelPen extends Pen {
    public GelPen(String name, String brand, Refill refill) {
        super(name, brand, refill);
    }

    @Override
    public void write() {
        if (!isOpen()) {
            System.out.println("Error: Open the cap first!");
            return;
        }
        System.out.println("Writing smoothly with " + getRefill().getInk().getType() + " ink.");
    }
}