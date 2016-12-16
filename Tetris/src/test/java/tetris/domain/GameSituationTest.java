/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.domain;

import java.awt.Color;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tetris.logiikka.Direction;
import tetris.logiikka.Rotation;

/**
 *
 * @author isjani
 */
public class GameSituationTest {

    private GameSituation gs;
    private Field field;

    private static final int width = 10;
    private static final int height = 20;

    public GameSituationTest() {
        field = new Field(width, height);
        gs = new GameSituation(field);
    }

    @Test
    public void getFieldAndPieceBlocksWorks() {
        gs.createActivePiece(Formation.O);
        MoveResult mR = gs.movePiece(Direction.DOWN);
        while (mR.pieceWasMoved) {
            mR = gs.movePiece(Direction.DOWN);
        }
        gs.createActivePiece();
        gs.movePiece(Direction.DOWN);
        gs.movePiece(Direction.DOWN);
        gs.movePiece(Direction.DOWN);
        Block[][] b = gs.getFieldAndPieceBlocks();

        int counter = 0;
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                if (b[i][j] != null) {
                    counter++;
                }
            }
        }
        assertEquals(8, counter);
    }
    @Test 
    public void resetWorks() {
        assertEquals(0,gs.getScore());
        gs.createActivePiece();
        MoveResult mR = gs.movePiece(Direction.DOWN);
        while(mR.pieceWasMoved) {
            mR = gs.movePiece(Direction.DOWN);
        }
        assertEquals(2,gs.getScore());
        gs.reset();
        assertEquals(0,gs.getScore());
    }

    @Test
    public void copyBlocksWorks() {
        Block[][] b = new Block[5][5];
        b[1][4] = new Block(3, 4, Color.BLACK);

        Block[][] copy = gs.copyBlocks(b, 5, 5);
        boolean copyWorked = true;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (b[i][j] != copy[i][j]) {
                    copyWorked = false;
                }
            }
        }
        assertTrue(copyWorked);
    }

    @Test
    public void rotatePieceAndBack() {
        gs.createActivePiece(Formation.I);
        assertTrue(gs.rotatePiece(Rotation.RIGHT));
        gs.movePiece(Direction.DOWN);
        assertTrue(gs.rotatePiece(Rotation.LEFT));
        assertTrue(gs.rotatePiece(Rotation.RIGHT));
        for (int i = 0; i < 10; i++) {
            gs.movePiece(Direction.LEFT);
        }
        assertTrue(!gs.rotatePiece(Rotation.RIGHT));

    }

    @Test
    public void fieldIsEmpty() {
        assertTrue(gs.fieldIsEmpty());
        gs.createActivePiece(Formation.I);
        MoveResult mR = gs.movePiece(Direction.DOWN);
        while (mR.pieceWasMoved) {
            mR = gs.movePiece(Direction.DOWN);
        }
        assertTrue(!gs.fieldIsEmpty());
    }

    @Test
    public void fourIOneO() {
        gs.createActivePiece(Formation.I);
        move(Direction.RIGHT, 5);
        move(Direction.DOWN, 20);

        gs.createActivePiece(Formation.I);
        move(Direction.RIGHT, 5);
        move(Direction.DOWN, 20);

        gs.createActivePiece(Formation.I);
        move(Direction.LEFT, 2);
        move(Direction.DOWN, 20);

        gs.createActivePiece(Formation.I);
        move(Direction.LEFT, 2);
        move(Direction.DOWN, 20);

        assertTrue(gs.getScore() == 8);
        gs.createActivePiece(Formation.O);
        move(Direction.LEFT, 5);
        move(Direction.DOWN, 20);

        assertEquals(0, field.getNumberOfBlocksOnRow(height - 1));
        assertTrue(field.isEmpty());
        assertTrue(gs.getScore() > 0);
    }

    private void move(Direction dir, int amount) {
        if (dir == Direction.DOWN) {
            MoveResult moveResult = gs.movePiece(dir);
            while (!moveResult.pieceWasFrozen) {
                moveResult = gs.movePiece(dir);
            }
        } else {

            for (int i = 0; i < amount; i++) {
                gs.movePiece(dir);
            }
        }
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
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
