package SnakesAndLadders;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Board Size (n): ");
        int n = sc.nextInt();
        System.out.print("Enter Number of Players: ");
        int x = sc.nextInt();
        System.out.print("Enter Difficulty (EASY/HARD): ");
        DifficultyLevel level = DifficultyLevel.valueOf(sc.next().toUpperCase());

        Game game = new Game(n, x, level);
        game.launch();
        sc.close();
    }
}