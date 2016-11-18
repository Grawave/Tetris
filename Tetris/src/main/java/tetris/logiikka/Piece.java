/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.logiikka;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author isjani
 */
public class Piece {

    private List<Block> blocks;
    private Block pivot;

    public Piece(int x, int y, Formation f) {
        this.blocks = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Block b = new Block(x + f.xVal[i], y + f.yVal[i]);
            blocks.add(b);
            if (i == f.pivotIndex) {
                pivot = b;
            }
        }
    }

    public boolean rotate(Rotation rot) {
        if (pivot == null) {
            return true;
        }
        for (Block block : blocks) {
            rotateBlock(block, rot);
        }
        return true;
    }

    public void rotateBlock(Block b, Rotation rot) {
        int xp = pivot.getX();
        int yp = pivot.getY();
        int x = b.getX();
        int y = b.getY();

        int relX = x - xp;
        int relY = y - yp;

        int newRelX = (rot.xMultiplier[0] * relX + rot.xMultiplier[1] * relY);
        int newRelY = (rot.yMultiplier[0] * relX + rot.yMultiplier[1] * relY);

        int newX = newRelX + xp;
        int newY = newRelY + yp;

        b.setCoordinates(newX, newY);
    }

    public void move(Direction dir) {
        for (Block b : blocks) {
            b.move(dir);
        }
    }

    public List<Block> getBlocks() {
        return this.blocks;
    }
}
