/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.domain;

import java.util.List;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import tetris.logiikka.Direction;
import tetris.logiikka.Rotation;

/**
 *
 * @author isjani
 *
 *
 */
/*
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
    public void rotateT() {
        create(Formation.T);
        int[] rotX = new int[]{alkuX - 1, alkuX, alkuX, alkuX};
        int[] rotY = new int[]{alkuY + 1, alkuY, alkuY + 2, alkuY + 1};
        List<Block> list = p.getBlocks();
        assertTrue(p.rotate(Rotation.LEFT));
        assertEquals(4, checkRotationHits(rotX, rotY));
        assertTrue(p.rotate(Rotation.RIGHT));
        assertTrue(hasNotMoved(Formation.T));
    }

    @Test
    public void rotateS() {
        create(Formation.S);
        int[] rotX = new int[]{alkuX + 1, alkuX + 1, alkuX, alkuX};
        int[] rotY = new int[]{alkuY + -1, alkuY, alkuY - 2, alkuY - 1};
        List<Block> list = p.getBlocks();
        
        assertTrue(p.rotate(Rotation.LEFT));
        assertEquals(4, checkRotationHits(rotX, rotY));
        assertTrue(p.rotate(Rotation.RIGHT));
        assertTrue(hasNotMoved(Formation.S));
    }

    @Test
    public void rotateI() {
        create(Formation.I);
        int[] rotX = new int[]{alkuX, alkuX, alkuX, alkuX};
        int[] rotY = new int[]{alkuY, alkuY + 1, alkuY - 1, alkuY - 2};
        List<Block> list = p.getBlocks();
        
        assertTrue(p.rotate(Rotation.LEFT));
        assertEquals(4, checkRotationHits(rotX, rotY));
        assertTrue(p.rotate(Rotation.RIGHT));
        assertTrue(hasNotMoved(Formation.I));
    }

    @Test
    public void rotateZ() {
        create(Formation.Z);
        int[] rotX = new int[]{alkuX + 1, alkuX, alkuX, alkuX + 1};
        int[] rotY = new int[]{alkuY - 1, alkuY - 1, alkuY, alkuY - 2};
        List<Block> list = p.getBlocks();
        
        assertTrue(p.rotate(Rotation.LEFT));
        assertEquals(4, checkRotationHits(rotX, rotY));
        assertTrue(p.rotate(Rotation.RIGHT));
        assertTrue(hasNotMoved(Formation.Z));
    }

    @Test
    public void rotateJ() {
        create(Formation.J);
        int[] rotX = new int[]{alkuX + 1, alkuX - 1, alkuX, alkuX + 1};
        int[] rotY = new int[]{alkuY - 1, alkuY - 1, alkuY - 1, alkuY};
        List<Block> list = p.getBlocks();
        assertTrue(p.rotate(Rotation.LEFT));
        assertEquals(4, checkRotationHits(rotX, rotY));
        assertTrue(p.rotate(Rotation.RIGHT));
        assertTrue(hasNotMoved(Formation.J));

    }

    public int checkRotationHits(int[] rotX, int[] rotY) {
        List<Block> list = p.getBlocks();
        int hits = 0;
        for (int i = 0; i < 4; i++) {
            int x = list.get(i).getX();
            int y = list.get(i).getY();
            for (int j = 0; j < 4; j++) {
                if (rotX[j] == x && rotY[j] == y) {
                    hits++;
                    rotX[j] = -9;
                    rotY[j] = -9;
                }
            }
        }
        return hits;
    }

    @Test
    public void rotateO() {
        create(Formation.O);
        int[] creationLocationX = CreateLocationsX(Formation.O);
        int[] creationLocationY = CreateLocationsY(Formation.O);
        assertTrue(p.rotate(Rotation.LEFT));
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
        assertTrue(hits == 4);
    }

    @Test
    public void rotateL() {
        create(Formation.L);
        int[] rotX = new int[]{alkuX, alkuX + 1, alkuX + 1, alkuX - 1};
        int[] rotY = new int[]{alkuY - 1, alkuY - 2, alkuY - 1, alkuY - 1};
        List<Block> list = p.getBlocks();
        assertTrue(p.rotate(Rotation.LEFT));
        assertEquals(4, checkRotationHits(rotX, rotY));
        assertTrue(p.rotate(Rotation.RIGHT));
        assertTrue(hasNotMoved(Formation.L));
    }

    private boolean hasNotMoved(Formation form) {
        int[] creationLocationX = CreateLocationsX(form);
        int[] creationLocationY = CreateLocationsY(form);
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
        return hits == 4;
    }

    @Test
    public void testL() {
        assertTrue(create(Formation.L));
        int amount = 3;
        movePiece(amount, Direction.DOWN);
        assertTrue(HasMovedFromCreation(amount, Direction.DOWN, Formation.L));
        movePiece(amount, Direction.UP);
        assertTrue(HasMovedFromCreation(0, Direction.DOWN, Formation.L));
        movePiece(amount, Direction.LEFT);
        assertTrue(HasMovedFromCreation(amount, Direction.LEFT, Formation.L));
        movePiece(amount, Direction.RIGHT);
        assertTrue(HasMovedFromCreation(0, Direction.LEFT, Formation.L));
    }

    @Test
    public void testO() {
        assertTrue(create(Formation.O));
        int amount = 3;
        movePiece(amount, Direction.DOWN);
        assertTrue(HasMovedFromCreation(amount, Direction.DOWN, Formation.O));
        movePiece(amount, Direction.UP);
        assertTrue(HasMovedFromCreation(0, Direction.DOWN, Formation.O));
        movePiece(amount, Direction.LEFT);
        assertTrue(HasMovedFromCreation(amount, Direction.LEFT, Formation.O));
        movePiece(amount, Direction.RIGHT);
        assertTrue(HasMovedFromCreation(0, Direction.LEFT, Formation.O));
    }

    @Test
    public void testI() {
        assertTrue(create(Formation.I));
        int amount = 3;
        movePiece(amount, Direction.DOWN);
        assertTrue(HasMovedFromCreation(amount, Direction.DOWN, Formation.I));
        movePiece(amount, Direction.UP);
        assertTrue(HasMovedFromCreation(0, Direction.DOWN, Formation.I));
        movePiece(amount, Direction.LEFT);
        assertTrue(HasMovedFromCreation(amount, Direction.LEFT, Formation.I));
        movePiece(amount, Direction.RIGHT);
        assertTrue(HasMovedFromCreation(0, Direction.LEFT, Formation.I));
    }

    @Test
    public void testT() {
        assertTrue(create(Formation.T));
        int amount = 3;
        movePiece(amount, Direction.DOWN);
        assertTrue(HasMovedFromCreation(amount, Direction.DOWN, Formation.T));
        movePiece(amount, Direction.UP);
        assertTrue(HasMovedFromCreation(0, Direction.DOWN, Formation.T));
        movePiece(amount, Direction.LEFT);
        assertTrue(HasMovedFromCreation(amount, Direction.LEFT, Formation.T));
        movePiece(amount, Direction.RIGHT);
        assertTrue(HasMovedFromCreation(0, Direction.LEFT, Formation.T));
    }

    @Test
    public void testS() {
        assertTrue(create(Formation.S));
        int amount = 3;
        movePiece(amount, Direction.DOWN);
        assertTrue(HasMovedFromCreation(amount, Direction.DOWN, Formation.S));
        movePiece(amount, Direction.UP);
        assertTrue(HasMovedFromCreation(0, Direction.DOWN, Formation.S));
        movePiece(amount, Direction.LEFT);
        assertTrue(HasMovedFromCreation(amount, Direction.LEFT, Formation.S));
        movePiece(amount, Direction.RIGHT);
        assertTrue(HasMovedFromCreation(0, Direction.LEFT, Formation.S));
    }

    @Test
    public void testZ() {
        assertTrue(create(Formation.Z));
        int amount = 3;
        movePiece(amount, Direction.DOWN);
        assertTrue(HasMovedFromCreation(amount, Direction.DOWN, Formation.Z));
        movePiece(amount, Direction.UP);
        assertTrue(HasMovedFromCreation(0, Direction.DOWN, Formation.Z));
        movePiece(amount, Direction.LEFT);
        assertTrue(HasMovedFromCreation(amount, Direction.LEFT, Formation.Z));
        movePiece(amount, Direction.RIGHT);
        assertTrue(HasMovedFromCreation(0, Direction.LEFT, Formation.Z));
    }

    @Test
    public void testJ() {
        assertTrue(create(Formation.J));
        int amount = 3;
        movePiece(amount, Direction.DOWN);
        assertTrue(HasMovedFromCreation(amount, Direction.DOWN, Formation.J));
        movePiece(amount, Direction.UP);
        assertTrue(HasMovedFromCreation(0, Direction.DOWN, Formation.J));
        movePiece(amount, Direction.LEFT);
        assertTrue(HasMovedFromCreation(amount, Direction.LEFT, Formation.J));
        movePiece(amount, Direction.RIGHT);
        assertTrue(HasMovedFromCreation(0, Direction.LEFT, Formation.J));
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

    private boolean create(Formation form) {
        p = new Piece(alkuX, alkuY, form);
        int[] creationLocationX = CreateLocationsX(form);
        int[] creationLocationY = CreateLocationsY(form);
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

    private boolean HasMovedFromCreation(int many, Direction dir, Formation form) {
        int[] creationLocationX = CreateLocationsX(form);
        int[] creationLocationY = CreateLocationsY(form);
        List<Block> list = p.getBlocks();
        int hits = 0;
        for (int i = 0; i < 4; i++) {
            int x = list.get(i).getX();
            int y = list.get(i).getY();
            for (int j = 0; j < 4; j++) {
                if (creationLocationX[j] + (many * dir.moveX) == x && creationLocationY[j] + (many * dir.moveY) == y) {
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
