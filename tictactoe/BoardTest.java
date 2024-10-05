package tictactoe;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class BoardTest.
 * Tests for the Board class and its functionality.
 */
public class BoardTest {

    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board(4); // Initialize a 4x4 board for testing
    }

    @Test
    public void testMakeMoveAndIsValidMove() {
        // Test making a move and checking if the move is valid
        assertTrue(board.isValidMove(0, 0));
        board.makeMove(0, 0, Player.NOUGHTS);
        assertFalse(board.isValidMove(0, 0)); // After a move is made, it should no longer be valid
    }

    @Test
    public void testIsBoardFull() {
        // Test if the board correctly identifies when it is full
        assertFalse(board.isBoardFull()); // Initially, the board should not be full

        // Fill the board and check again
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board.makeMove(i, j, Player.NOUGHTS);
            }
        }
        assertTrue(board.isBoardFull());
    }

    @Test
    public void testCheckWin() {
        // Test winning condition for rows, columns, and diagonals
        board.makeMove(0, 0, Player.CROSSES);
        board.makeMove(0, 1, Player.CROSSES);
        board.makeMove(0, 2, Player.CROSSES);
        board.makeMove(0, 3, Player.CROSSES);
        assertTrue(board.checkWin(Player.CROSSES, 4)); // Check for a win with 4 in a row
    }

    @Test
    public void testNoWin() {
        // Test when there is no winner
        board.makeMove(0, 0, Player.CROSSES);
        board.makeMove(0, 1, Player.CROSSES);
        board.makeMove(0, 2, Player.CROSSES);
        board.makeMove(1, 3, Player.NOUGHTS); // This breaks the consecutive line
        assertFalse(board.checkWin(Player.CROSSES, 4));
    }
}
