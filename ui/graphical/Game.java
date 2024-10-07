package ui.graphical;

import tictactoe.Board;
import tictactoe.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Graphical Tic-Tac-Toe game for 3 players on a 4x4 grid using Swing.
 */
public class Game extends JFrame {
    private Board board;
    private JButton[][] buttons; // Buttons for the 4x4 grid
    private Player[] players = {Player.NOUGHTS, Player.CROSSES, Player.TRIANGLES}; // 3 players
    private int currentPlayerIndex;
    private JLabel statusLabel;

    public Game() {
        board = new Board(4); // Initialize board size to 4x4
        buttons = new JButton[4][4]; // 4x4 grid of buttons
        currentPlayerIndex = 0; // Start with the first player (NOUGHTS)
        initializeGUI();
    }

    private void initializeGUI() {
        setTitle("3-Player Tic-Tac-Toe on a 4x4 Grid");
        setSize(500, 550); // Adjust window size for a larger grid
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(4, 4)); // 4x4 grid

        // Create buttons for each cell
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                JButton button = new JButton("");
                button.setFont(new Font("Arial", Font.PLAIN, 40));
                buttons[i][j] = button;
                gridPanel.add(button);

                // Add action listener to handle player moves
                final int row = i;
                final int col = j;
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        handleMove(row, col);
                    }
                });
            }
        }

        statusLabel = new JLabel("Player " + players[currentPlayerIndex].getSymbol() + "'s turn");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 20));

        add(gridPanel, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void handleMove(int row, int col) {
        if (board.isValidMove(row, col)) {
            Player currentPlayer = players[currentPlayerIndex];
            board.makeMove(row, col, currentPlayer);
            buttons[row][col].setText(String.valueOf(currentPlayer.getSymbol()));

            if (board.checkWin(currentPlayer, 4)) { // Check for 4 in a row
                statusLabel.setText("Player " + currentPlayer.getSymbol() + " wins!");
                disableBoard(); // Game over, disable further moves
            } else if (board.isBoardFull()) {
                statusLabel.setText("It's a draw!");
                disableBoard(); // Game over, disable further moves
            } else {
                currentPlayerIndex = (currentPlayerIndex + 1) % 3; // Cycle to the next player
                statusLabel.setText("Player " + players[currentPlayerIndex].getSymbol() + "'s turn");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid move! Try again.");
        }
    }

    private void disableBoard() {
        // Disable all buttons
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Game());
    }
}
