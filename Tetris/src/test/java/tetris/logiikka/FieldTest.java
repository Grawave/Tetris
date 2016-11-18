/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.logiikka;

import tetris.logiikka.Piece;
import tetris.logiikka.Formation;
import tetris.logiikka.Field;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author isjani
 */
public class FieldTest {

    private Field f;
    private Piece p;
    private final int width = 10;
    private final int height = 20;

    public FieldTest() {
    }

    @Before
    public void setUp() {
        f = new Field(width, height);
        p = new Piece(width / 2, height - 2, Formation.O);
    }

    @Test
    public void freezePieceWorks() {
        assertEquals(0, f.getNumberOfBlocksOnRow(0));
        f.freezePiece(p);
        assertEquals(2, f.getNumberOfBlocksOnRow(0));
        assertEquals(2, f.getNumberOfBlocksOnRow(1));
        assertEquals(0, f.getNumberOfBlocksOnRow(2));

        Block[][] blocks = f.getFrozenBlocks();
        assertEquals(true, blocks[width / 2][height - 1] != null);
        assertEquals(true, blocks[width / 2 + 1][height - 1] != null);
        assertEquals(true, blocks[width / 2 + 1][height - 2] != null);
        assertEquals(true, blocks[width / 2][height - 2] != null);

        assertEquals(true, blocks[width / 2 + 1][height - 3] == null);
    }

    @Test
    public void spotIsVacantWorks() {
        assertEquals(true,f.spotIsVacant(width/2,height-1));
        assertEquals(true,f.spotIsVacant(width/2 +1,height-1));
        assertEquals(true,f.spotIsVacant(width/2,height-2));
        assertEquals(true,f.spotIsVacant(width/2,height-2));
        f.freezePiece(p);
        assertEquals(false,f.spotIsVacant(width/2,height-1));
        assertEquals(false,f.spotIsVacant(width/2 +1,height-1));
        assertEquals(false,f.spotIsVacant(width/2,height-2));
        assertEquals(false,f.spotIsVacant(width/2,height-2));
    }

    @After
    public void tearDown() {
    }

}
