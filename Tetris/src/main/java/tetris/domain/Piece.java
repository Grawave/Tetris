/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.domain;

import tetris.domain.Formation;
import tetris.domain.Block;
import java.util.ArrayList;
import java.util.List;
import tetris.logiikka.Direction;
import tetris.logiikka.Rotation;

/**
 * Piece consists of four blocks.
 *
 * @author isjani
 */
public class Piece {

    private final int BLOCK_COUNT = 4;
    private List<Block> blocks;
    private Block pivot;

    /**
     * Creates a piece of a given formation. The starting coordinates are
     * determined by the input integers and the formation.
     *
     * @param x creation location on x-axis
     * @param y creation location on y-axis
     * @param formation that determines how the blocks of the piece are
     * positioned in relationship with each other.
     * @see tetris.domain.Block
     * @see tetris.domain.Formation
     */
    public Piece(int x, int y, Formation formation) {
        this.blocks = new ArrayList<>();
        for (int i = 0; i < BLOCK_COUNT; i++) {
            Block b = new Block(x + formation.xVal[i], y + formation.yVal[i], formation.color);
            blocks.add(b);
            if (i == formation.pivotIndex) {
                pivot = b;
            }
        }
        if (formation == Formation.O) {
            pivot = null;
        }
    }

    /**
     * Rotates the piece to according to given rotation
     *
     * @param rotation direction to be rotating.
     * @return true if the rotation was successful.
     */
    public boolean rotate(Rotation rotation) {
        if (pivot == null) {
            return true;
        }
        for (Block block : blocks) {
            rotateBlock(block, rotation);
        }
        return true;
    }

    /**
     * Rotates a given block according to the given rotational direction around
     * the pivot.
     *
     * @param block to be rotated
     * @param rotation direction to be rotated
     */
    public void rotateBlock(Block block, Rotation rotation) {
        int xPivot = pivot.getX();
        int yPivot = pivot.getY();

        int xBlock = block.getX();
        int yBlock = block.getY();

        int relativeX = xBlock - xPivot;
        int relativeY = yBlock - yPivot;

        int newRelativeX = (rotation.xMultiplier[0] * relativeX + rotation.xMultiplier[1] * relativeY);
        int newRelativeY = (rotation.yMultiplier[0] * relativeX + rotation.yMultiplier[1] * relativeY);

        int newX = newRelativeX + xPivot;
        int newY = newRelativeY + yPivot;

        block.setCoordinates(newX, newY);
    }

    /**
     * Moves the piece to given direction.
     *
     * @param direction of movement
     */
    public void move(Direction direction) {
        for (Block block : blocks) {
            block.move(direction);
        }
    }

    /**
     * @return a list of blocks that the piece consists of.
     */
    public List<Block> getBlocks() {
        return this.blocks;
    }
}
