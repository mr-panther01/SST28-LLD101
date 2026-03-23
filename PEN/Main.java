package PEN;

public class Main {
    public static void main(String[] args) {
        Ink blueGel = new Ink("Blue", "Pilot", InkType.GEL);
        Nib fineNib = new Nib(0.5, "Fine");
        Refill refill = new Refill(blueGel, fineNib, true);

        GelPen gelPen = new GelPen("G-2", "Pilot", refill);
        gelPen.setPrice(2.99);

        gelPen.start();
        gelPen.write();

        // Refill test
        Ink blackGel = new Ink("Black", "Pilot", InkType.GEL);
        Refill newRefill = new Refill(blackGel, fineNib, true);
        gelPen.refill(newRefill);

        gelPen.write();
        gelPen.close();
    }
}
