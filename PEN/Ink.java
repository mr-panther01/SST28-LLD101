package PEN;
class Ink {
    private String color;
    private String brand;
    private InkType type;

    public Ink(String color, String brand, InkType type) {
        this.color = color;
        this.brand = brand;
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public InkType getType() {
        return type;
    }

    public void setType(InkType type) {
        this.type = type;
    }
}