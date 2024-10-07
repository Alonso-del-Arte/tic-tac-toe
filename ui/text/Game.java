package ui.text;

import tictactoe.Board;
import tictactoe.Player;

import java.util.Scanner;

public class Game {
    private Board board;
    private Player[] players = {Player.NOUGHTS, Player.CROSSES, Player.TRIANGLES}; // 3 players
    private int currentPlayerIndex;

    public Game() {
        board = new Board(4); // Initialize board size to 4x4
        currentPlayerIndex = 0; // Start with the first player
        playGame();
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printBoard();

            Player currentPlayer = players[currentPlayerIndex];
            System.out.println("Player " + currentPlayer.getSymbol() + "'s turn.");

            System.out.print("Enter row (0-3): ");
            int row = scanner.nextInt();

            System.out.print("Enter col (0-3): ");
            int col = scanner.nextInt();

            if (board.isValidMove(row, col)) {
                board.makeMove(row, col, currentPlayer);

                if (board.checkWin(currentPlayer, 4)) {
                    printBoard();
                    System.out.println("Player " + currentPlayer.getSymbol() + " wins!");
                    break;
                } else if (board.isBoardFull()) {
                    printBoard();
                    System.out.println("It's a draw!");
                    break;
                } else {
                    currentPlayerIndex = (currentPlayerIndex + 1) % 3; // Cycle to the next player
                }
            } else {
                System.out.println("Invalid move! Try again.");
            }
        }
        scanner.close();
    }

    private void printBoard() {
        Player[][] grid = board.getGrid();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (grid[i][j] != null) {
                    System.out.print(grid[i][j].getSymbol() + " ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        new Game();
    }
}
