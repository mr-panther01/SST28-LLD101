package PEN;

class Refill {
    private Ink ink;
    private Nib nib;
    private boolean isRefillable;

    public Refill(Ink ink, Nib nib, boolean isRefillable) {
        this.ink = ink;
        this.nib = nib;
        this.isRefillable = isRefillable;
    }

    public Ink getInk() {
        return ink;
    }

    public void setInk(Ink ink) {
        this.ink = ink;
    }

    public Nib getNib() {
        return nib;
    }

    public void setNib(Nib nib) {
        this.nib = nib;
    }

    public boolean isRefillable() {
        return isRefillable;
    }

    public void setRefillable(boolean refillable) {
        isRefillable = refillable;
    }
}