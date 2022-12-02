package tictactoe; 

/**
 * Enumeration class Player - write a description of the enum class here
 *
 * @author Alonso del Arte
 * @version 0.1, 1 December 2022
 */
public enum Player {
    
    NOUGHTS('\u00A1'), CROSSES('?');
    
    private final char symbol;
    
    public char getSymbol() {
        return this.symbol;
    }

    Player(char ch) {
        this.symbol = ch;
    }
    
}
