/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.domain;

import tetris.domain.MoveResult;
import tetris.domain.Block;
import tetris.domain.Piece;
import tetris.domain.Formation;
import tetris.domain.Field;
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
        assertEquals(0, f.getNumberOfBlocksOnRow(height - 1));
        f.freezePiece(p);
        assertEquals(2, f.getNumberOfBlocksOnRow(height - 1));
        assertEquals(2, f.getNumberOfBlocksOnRow(height - 2));
        assertEquals(0, f.getNumberOfBlocksOnRow(height - 3));

        Block[][] blocks = f.getFrozenBlocks();

        assertEquals(true, blocks[height - 1][width / 2] != null);
        assertEquals(true, blocks[height - 1][width / 2 + 1] != null);
        assertEquals(true, blocks[height - 2][width / 2 + 1] != null);
        assertEquals(true, blocks[height - 2][width / 2] != null);

        assertEquals(true, blocks[height - 3][width / 2 + 1] == null);
    }

    @Test
    public void spotIsVacantWorks() {
        assertEquals(true, f.spotIsVacant(width / 2, height - 1));
        assertEquals(true, f.spotIsVacant(width / 2 + 1, height - 1));
        assertEquals(true, f.spotIsVacant(width / 2, height - 2));
        assertEquals(true, f.spotIsVacant(width / 2, height - 2));
        f.freezePiece(p);
        assertEquals(false, f.spotIsVacant(width / 2, height - 1));
        assertEquals(false, f.spotIsVacant(width / 2 + 1, height - 1));
        assertEquals(false, f.spotIsVacant(width / 2, height - 2));
        assertEquals(false, f.spotIsVacant(width / 2, height - 2));
    }

    @Test
    public void dropRowsWorks() {
        p = new Piece(0, height - 2, Formation.O);
        f.freezePiece(p);
        p = new Piece(2, height - 2, Formation.O);
        f.freezePiece(p);
        p = new Piece(4, height - 2, Formation.O);
        f.freezePiece(p);
        p = new Piece(6, height - 2, Formation.O);
        f.freezePiece(p);
        assertEquals(width - width / 5, f.getNumberOfBlocksOnRow(height - 2));
        assertEquals(width - width / 5, f.getNumberOfBlocksOnRow(height - 1));
        p = new Piece(8, height - 2, Formation.O);
        MoveResult mR= f.freezePiece(p);
        assertEquals(0, f.getNumberOfBlocksOnRow(height - 2));
        assertEquals(0, f.getNumberOfBlocksOnRow(height - 1));
        
        assertTrue(mR.gameWon);
        assertTrue(f.isEmpty());
    }

    @After
    public void tearDown() {
    }

}
