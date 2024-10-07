package tictactoe;

public class Board {
    private Player[][] grid;
    private int size;

    public Board(int size) {
        this.size = size;
        grid = new Player[size][size];
    }

    public boolean isValidMove(int row, int col) {
        return grid[row][col] == null;
    }

    public void makeMove(int row, int col, Player player) {
        grid[row][col] = player;
    }

    public boolean isBoardFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (grid[i][j] == null) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkWin(Player player, int consecutiveToWin) {
        return checkRows(player, consecutiveToWin) || checkCols(player, consecutiveToWin) || checkDiagonals(player, consecutiveToWin);
    }

    private boolean checkRows(Player player, int consecutiveToWin) {
        for (int i = 0; i < size; i++) {
            int count = 0;
            for (int j = 0; j < size; j++) {
                if (grid[i][j] == player) {
                    count++;
                    if (count == consecutiveToWin) return true;
                } else {
                    count = 0;
                }
            }
        }
        return false;
    }

    private boolean checkCols(Player player, int consecutiveToWin) {
        for (int j = 0; j < size; j++) {
            int count = 0;
            for (int i = 0; i < size; i++) {
                if (grid[i][j] == player) {
                    count++;
                    if (count == consecutiveToWin) return true;
                } else {
                    count = 0;
                }
            }
        }
        return false;
    }

    private boolean checkDiagonals(Player player, int consecutiveToWin) {
        return checkDiagonal(player, consecutiveToWin, true) || checkDiagonal(player, consecutiveToWin, false);
    }

    private boolean checkDiagonal(Player player, int consecutiveToWin, boolean leftToRight) {
        for (int i = 0; i <= size - consecutiveToWin; i++) {
            for (int j = 0; j <= size - consecutiveToWin; j++) {
                int count = 0;
                for (int k = 0; k < consecutiveToWin; k++) {
                    int row = leftToRight ? i + k : i + k;
                    int col = leftToRight ? j + k : size - j - k - 1;
                    if (grid[row][col] == player) {
                        count++;
                        if (count == consecutiveToWin) return true;
                    } else {
                        break;
                    }
                }
            }
        }
        return false;
    }

    public Player[][] getGrid() {
        return grid;
    }

    public int getSize() {
        return size;
    }
}
