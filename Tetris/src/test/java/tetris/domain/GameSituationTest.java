/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.domain;

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

        gs.createActivePiece(Formation.O);
        move(Direction.LEFT, 5);
        move(Direction.DOWN, 20);

        assertEquals(0, field.getNumberOfBlocksOnRow(height - 1));
        assertTrue(field.isEmpty());

    }

    private void move(Direction dir, int amount) {
        if (dir == Direction.DOWN) {
            MoveResult moveResult = gs.movePiece(dir);
            while(!moveResult.pieceWasFrozen) {
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
