package tictactoe;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    public void testGetSymbol() {
        // Test if the symbols are correctly assigned to each player
        assertEquals('O', Player.NOUGHTS.getSymbol());
        assertEquals('X', Player.CROSSES.getSymbol());
        assertEquals('T', Player.TRIANGLES.getSymbol());
    }
}
