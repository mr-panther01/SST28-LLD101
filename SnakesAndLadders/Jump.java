package SnakesAndLadders;

public abstract class Jump {
    int start;
    int end;

    public Jump(int start, int end) {
        this.start = start;
        this.end = end;
    }
    public int getEnd() { return end; }
    public int getStart() { return start; }
}

class Snake extends Jump {
    public Snake(int start, int end) { super(start, end); }
}

class Ladder extends Jump {
    public Ladder(int start, int end) { super(start, end); }
}