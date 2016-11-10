/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author isjani
 */
public class BlockTest {

    private Block b;
    private final int alkuX = 5;
    private final int alkuY = 0;

    public BlockTest() {
    }

    @Before
    public void setUp() {
        b = new Block(alkuX, alkuY);
    }

    @Test
    public void moveDown1() {
        int y = b.getY();
        b.move(Direction.DOWN);
        assertEquals(b.getY(), y + 1);
    }

    @Test
    public void moveRight1() {
        int x = b.getX();
        b.move(Direction.RIGHT);
        assertEquals(b.getX(), x + 1);
    }
    
    @Test
    public void moveLeft1() {
        int x = b.getX();
        b.move(Direction.LEFT);
        assertEquals(b.getX(), x -1);
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
