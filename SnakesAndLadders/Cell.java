package SnakesAndLadders;

public class Cell {
    private Jump jump; // Can be null, a Snake, or a Ladder

    public Jump getJump() { return jump; }
    public void setJump(Jump jump) { this.jump = jump; }
}