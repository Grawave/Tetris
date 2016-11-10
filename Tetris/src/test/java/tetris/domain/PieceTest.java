/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.domain;

import java.util.List;
import org.junit.After;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author isjani
 * 
 * 
 */
/*
GENERAL NOTES TO ADVANCE: Refactor so that no copypaste. Create takes argument of Formation, from where 
it creates the piece, the same is true for HasMoved etc!
*/
public class PieceTest {

    private Piece p;
    private final int alkuX = 5;
    private final int alkuY = 0;

    public PieceTest() {
    }

    @Before
    public void setUp() {

    }

    @Test
    public void testL() {
        assertTrue(createL());
    }


    private int[] CreateLocationsY(Formation f) {
        int[] lY = f.yVal;
        int[] loc = new int[4];
        for (int i = 0; i < 4; i++) {
            loc[i] = alkuY + lY[i];
        }
        return loc;

    }

    private int[] CreateLocationsX(Formation f) {
        int[] lY = f.xVal;
        int[] loc = new int[4];
        for (int i = 0; i < 4; i++) {
            loc[i] = alkuX + lY[i];
        }
        return loc;

    }
    private boolean createL() {
        p = new Piece(alkuX, alkuY, Formation.L);
        int[] creationLocationX = CreateLocationsX(Formation.L);
        int[] creationLocationY = CreateLocationsY(Formation.L);
        List<Block> list = p.getBlocks();
        int hits = 0;
        for (int i = 0; i < 4; i++) {
            int x = list.get(i).getX();
            int y = list.get(i).getY();
            for (int j = 0; j < 4; j++) {
                if (creationLocationX[j] == x && creationLocationY[j] == y) {
                    hits++;
                    creationLocationX[j] = -9;
                    creationLocationY[j] = -9;
                }
            }
        }
        return (hits == 4);
    }

    @Test
    public void testO() {
        assertTrue(createO());
        int amount = 3;
        movePiece(amount, Direction.DOWN);
        assertTrue(oHasMovedFromCreation(amount, Direction.DOWN));
        movePiece(amount, Direction.UP);
        assertTrue(oHasMovedFromCreation(0, Direction.DOWN));
        movePiece(amount, Direction.LEFT);
        assertTrue(oHasMovedFromCreation(amount, Direction.LEFT));
        movePiece(amount, Direction.RIGHT);
        assertTrue(oHasMovedFromCreation(0, Direction.LEFT));
    }

    private boolean oHasMovedFromCreation(int many, Direction dir) {
        int[] creationLocationX = CreateLocationsX(Formation.O);
        int[] creationLocationY = CreateLocationsY(Formation.O);
        List<Block> list = p.getBlocks();
        int hits = 0;
        for (int i = 0; i < 4; i++) {
            int x = list.get(i).getX();
            int y = list.get(i).getY();
            for (int j = 0; j < 4; j++) {
                if (creationLocationX[j] + (many * dir.x) == x && creationLocationY[j] + (many * dir.y) == y) {
                    hits++;
                    creationLocationX[j] = -9;
                    creationLocationY[j] = -9;
                }
            }
        }
        return hits == 4;
    }

    private void movePiece(int many, Direction dir) {
        for (int i = 0; i < many; i++) {
            p.move(dir);
        }
    }



    private int[] oCreateLocationsY() {
        int[] oY = Formation.O.yVal;
        int[] ret = new int[4];
        for (int i = 0; i < 4; i++) {
            ret[i] = alkuY + oY[i];
        }
        return ret;
    }

    private boolean createO() {
        p = new Piece(alkuX, alkuY, Formation.O);
        int[] creationLocationX = CreateLocationsX(Formation.O);
        int[] creationLocationY = CreateLocationsY(Formation.O);
        List<Block> list = p.getBlocks();
        int hits = 0;
        for (int i = 0; i < 4; i++) {
            int x = list.get(i).getX();
            int y = list.get(i).getY();
            for (int j = 0; j < 4; j++) {
                if (creationLocationX[j] == x && creationLocationY[j] == y) {
                    hits++;
                    creationLocationX[j] = -9;
                    creationLocationY[j] = -9;
                }
            }
        }
        return (hits == 4);
    }

    @After
    public void tearDown() {
    }

}
