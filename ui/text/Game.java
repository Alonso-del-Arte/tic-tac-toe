package ui.text;

import tictactoe.Board;
import tictactoe.Player;

import javax.swing.*;

public class Game {
    private Board board;
    private Player[] players;
    private int currentPlayerIndex;
    private int consecutiveToWin;

    public Game() {
        setupGame();
        playGame();
    }

    private void setupGame() {
        String[] options = {"2-Player (3x3)", "3-Player (4x4)"};
        int choice = JOptionPane.showOptionDialog(null, "Welcome to Tic Tac Toe!\nSelect game mode:",
                "Options", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        if (choice == 0) {
            players = new Player[]{Player.NOUGHTS, Player.CROSSES}; // 2 players
            board = new Board(3); // Initialize board size to 3x3
            consecutiveToWin = 3; // Need 3 consecutive symbols to win
        } else if (choice == 1) {
            players = new Player[]{Player.NOUGHTS, Player.CROSSES, Player.TRIANGLES}; // 3 players
            board = new Board(4); // Initialize board size to 4x4
            consecutiveToWin = 4; // Need 4 consecutive symbols to win
        }

        currentPlayerIndex = 0;
    }

    public void playGame() {
        while (true) {
            printBoard();

            Player currentPlayer = players[currentPlayerIndex];
            JOptionPane.showMessageDialog(null, "Player " + currentPlayer.getSymbol() + "'s turn.");

            int row = getInput("Enter row (0-" + (board.getSize() - 1) + "): ");
            int col = getInput("Enter col (0-" + (board.getSize() - 1) + "): ");

            if (board.isValidMove(row, col)) {
                board.makeMove(row, col, currentPlayer);

                if (board.checkWin(currentPlayer, consecutiveToWin)) {
                    printBoard();
                    JOptionPane.showMessageDialog(null, "Player " + currentPlayer.getSymbol() + " wins!");
                    break;
                } else if (board.isBoardFull()) {
                    printBoard();
                    JOptionPane.showMessageDialog(null, "It's a draw!");
                    break;
                } else {
                    currentPlayerIndex = (currentPlayerIndex + 1) % players.length; // Cycle to the next player
                }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid move! Try again.");
            }
        }
    }

    private int getInput(String prompt) {
        String input = JOptionPane.showInputDialog(prompt);
        return Integer.parseInt(input);
    }

    private void printBoard() {
        Player[][] grid = board.getGrid();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (grid[i][j] != null) {
                    sb.append(grid[i][j].getSymbol()).append(" ");
                } else {
                    sb.append("- ");
                }
            }
            sb.append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    public static void main(String[] args) {
        new Game();
    }
}
