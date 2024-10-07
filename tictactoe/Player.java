package tictactoe;

public enum Player {
    NOUGHTS('O'), CROSSES('X'), TRIANGLES('T'); // Third player added

    private final char symbol;

    public char getSymbol() {
        return this.symbol;
    }

    Player(char ch) {
        this.symbol = ch;
    }
}
