package PEN;
enum InkType { GEL, BALL, FOUNTAIN }



class Nib {
    private double radius;
    private String type;

    public Nib(double radius, String type) {
        this.radius = radius;
        this.type = type;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}