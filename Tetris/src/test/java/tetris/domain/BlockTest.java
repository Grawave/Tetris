/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.domain;

import java.awt.Color;
import tetris.domain.Block;
import tetris.logiikka.Direction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import tetris.logiikka.Direction;
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
        b = new Block(alkuX, alkuY, Color.BLACK);
    }

    @Test
    public void moveDown1() {
        int y = b.getY();
        liikuta(1, Direction.DOWN);
        assertEquals(b.getY(), y + 1);
    }

    @Test
    public void moveRight1() {
        int x = b.getX();
        liikuta(1, Direction.RIGHT);
        assertEquals(b.getX(), x + 1);
    }

    @Test
    public void moveLeft1() {
        int x = b.getX();
        liikuta(1, Direction.LEFT);
        assertEquals(b.getX(), x - 1);
    }

    @After
    public void tearDown() {
    }

    private void liikuta(int monta, Direction dir) {
        for (int i = 0; i < monta; i++) {
            b.move(dir);
        }
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
