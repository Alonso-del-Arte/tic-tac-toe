package tictactoe;

public enum Player {
    NOUGHTS('O'), CROSSES('X'), TRIANGLES('T'); // 3 players

    private final char symbol;

    public char getSymbol() {
        return this.symbol;
    }

    Player(char ch) {
        this.symbol = ch;
    }
}
