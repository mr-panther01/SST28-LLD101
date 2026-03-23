package SnakesAndLadders;

public class Player {
    private String name;
    private int position = 0;
    private boolean hasWon = false;

    public Player(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int nextPos) {
        this.position = nextPos;
    }

    public boolean isHasWon() {
        return hasWon;
    }

    public void setHasWon(boolean hasWon) {
        this.hasWon = hasWon;
    }

    public String getName() {
        return name;
    }
}
