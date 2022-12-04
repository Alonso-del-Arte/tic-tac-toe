package tictactoe;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * The test class PlayerTest.
 *
 * @author  Alonso del Arte
 * @version 0.1
 */
public class PlayerTest {
    
    @Test
    void testGetSymbol() {
        System.out.println("getSymbol");
        assertEquals('O', Player.NOUGHTS.getSymbol());
        assertEquals('X', Player.CROSSES.getSymbol());
    }

}
