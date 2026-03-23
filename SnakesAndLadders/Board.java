package SnakesAndLadders;

import java.util.*;

public class Board {
    private int size;
    private Cell[][] cells;

    public Board(int n, DifficultyLevel level) {
        this.size = n;
        initializeBoard(n);
        addJumps(n, level);
    }

    private void initializeBoard(int n) {
        cells = new Cell[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    private void addJumps(int n, DifficultyLevel level) {
        Random rand = new Random();
        int maxPos = n * n;

        // Add n Ladders
        for (int i = 0; i < n; i++) {
            int start = rand.nextInt(maxPos - 1) + 1;
            int end = rand.nextInt(maxPos - start) + start + 1;
            if (end >= maxPos) end = maxPos - 1;
            
            if (getCell(start).getJump() == null) {
                getCell(start).setJump(new Ladder(start, end));
            } else { i--; } // Retry if cell occupied
        }

        // Add n Snakes
        for (int i = 0; i < n; i++) {
            int start = rand.nextInt(maxPos - 2) + 2;
            int end = rand.nextInt(start - 1) + 1;
            
            if (getCell(start).getJump() == null) {
                getCell(start).setJump(new Snake(start, end));
            } else { i--; }
        }
    }

    public Cell getCell(int pos) {
        int r = (pos - 1) / size;
        int c = (pos - 1) % size;
        return cells[r][c];
    }
    
    public int getSize() { return size * size; }
}