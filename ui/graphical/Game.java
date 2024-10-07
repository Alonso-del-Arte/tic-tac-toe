package ui.graphical;

import tictactoe.Board;
import tictactoe.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JFrame {
    private Board board;
    private Player[] players;
    private int currentPlayerIndex;
    private JButton[][] buttons;
    private int consecutiveToWin;
    private JPanel boardPanel;

    public Game() {
        setupGame();
        setTitle("Tic Tac Toe");
        setSize(500, 500);  // Set the window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);  // Make sure the window is visible
    }

    private void setupGame() {
        String[] options = {"2-Player (3x3)", "3-Player (4x4)"};
        int choice = JOptionPane.showOptionDialog(this, "Welcome to Tic Tac Toe!\nSelect game mode:",
                "Options", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        // Set the players and board size based on the user's choice
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
        createBoardUI(); // Create the board UI immediately after the game mode is selected
    }

    private void createBoardUI() {
        if (boardPanel != null) {
            remove(boardPanel); // Remove any existing board panel before creating a new one
        }

        int size = board.getSize();
        boardPanel = new JPanel(new GridLayout(size, size));  // Set up grid layout
        buttons = new JButton[size][size];  // Initialize button array

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                JButton button = new JButton("-");
                final int row = i;
                final int col = j;

                button.setFont(new Font("Arial", Font.PLAIN, 40));  // Increase the font size for visibility
                button.setFocusPainted(false);  // Remove focus border around buttons
                button.setPreferredSize(new Dimension(100, 100));  // Set size for each button

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        handleMove(row, col, button);  // Handle the player's move
                    }
                });

                buttons[i][j] = button;
                boardPanel.add(button);
            }
        }

        add(boardPanel, BorderLayout.CENTER);  // Add the board to the main frame
        validate();  // Refresh the frame to show the new board
    }

    private void handleMove(int row, int col, JButton button) {
        if (board.isValidMove(row, col)) {
            Player currentPlayer = players[currentPlayerIndex];
            board.makeMove(row, col, currentPlayer);  // Make the move on the board
            button.setText(String.valueOf(currentPlayer.getSymbol()));
            button.setEnabled(false);  // Disable the button after a move is made

            // Check if the current player has won
            if (board.checkWin(currentPlayer, consecutiveToWin)) {
                JOptionPane.showMessageDialog(this, "Player " + currentPlayer.getSymbol() + " wins!");
                resetGame();  // Reset the game after a win
            } else if (board.isBoardFull()) {
                JOptionPane.showMessageDialog(this, "It's a draw!");
                resetGame();  // Reset the game after a draw
            } else {
                currentPlayerIndex = (currentPlayerIndex + 1) % players.length;  // Cycle to the next player
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid move! Try again.");
        }
    }

    private void resetGame() {
        boardPanel.removeAll();  // Remove the current board
        setupGame();  // Reinitialize the game and prompt for new mode
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Game());  // Run the game in the Swing thread
    }
}
