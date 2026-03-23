package SnakesAndLadders;

import java.util.*;

public class Game {
    private Board board;
    private Dice dice;
    private Deque<Player> players = new LinkedList<>();
    private List<Player> winners = new ArrayList<>();

    public Game(int n, int numPlayers, DifficultyLevel level) {
        this.board = new Board(n, level);
        this.dice = new Dice();
        for (int i = 1; i <= numPlayers; i++) {
            players.add(new Player("Player " + i));
        }
    }

    public void launch() {
        while (players.size() >= 2) {
            Player currentPlayer = players.pollFirst();
            int roll = dice.roll();
            int nextPos = currentPlayer.getPosition() + roll;

            if (nextPos <= board.getSize()) {
                // Check for Snake or Ladder
                Cell cell = board.getCell(nextPos == 0 ? 1 : nextPos);
                if (cell.getJump() != null) {
                    nextPos = cell.getJump().getEnd();
                }
                currentPlayer.setPosition(nextPos);
            }

            if (currentPlayer.getPosition() == board.getSize()) {
                currentPlayer.setHasWon(true);
                winners.add(currentPlayer);
                System.out.println(currentPlayer.getName() + " HAS WON!");
            } else {
                players.addLast(currentPlayer);
            }
        }
        System.out.println("Game Over. Final Player left: " + players.peek().getName());
    }
}