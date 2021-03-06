/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.domain;

import java.util.List;

/**
 * Knows all the frozen blocks in the Tetris-field.
 *
 * @author isjani
 */
public class Field {

    /**
     * A 2x2 matrix that contains all the frozen blocks. If no block is present
     * in a element of the matrix, it has a value NULL.
     */
    private Block[][] frozenBlocks;
    /**
     * Width of the field.
     */
    private final int width;
    /**
     * Height of the field.
     */
    private final int height;

    /**
     * Constructs a field with given width and height. Coordinates start from 0.
     *
     * @param width of the field.
     * @param height of the field.
     */
    public Field(int width, int height) {
        this.frozenBlocks = new Block[height][width];
        this.width = width;
        this.height = height;
    }

    /**
     * Attempts to freeze the given piece to where it is.
     *
     * The piece can be frozen and the game can be lost or won.
     *
     * @param piece to be frozen.
     * @return MoveResult object is returned to indicate the result.
     * @see tetris.domain.MoveResult
     */
    public MoveResult freezePiece(Piece piece) {
        MoveResult moveResult = new MoveResult();
        List<Block> blocks = piece.getBlocks();

        for (Block block : blocks) {
            int x = block.getX();
            int y = block.getY();
            /* game ends with these conditions */
            if (y < 0 || frozenBlocks[y][x] != null) {
                moveResult.gameLost = true;
                return moveResult;
            }
            frozenBlocks[y][x] = block;
        }
        // checks the rows where insertions where made. Drops rows above
        // if any rows were filled
        moveResult.pointsGained = dropRows();

        if (this.isEmpty()) {
            moveResult.gameWon = true;
        }
        moveResult.pieceWasFrozen = true;
        moveResult.pointsGained += 2;
        return moveResult;
    }

    private int dropRows() {
        int points = 0;
        for (int y = 0; y < height; y++) {
            if (getNumberOfBlocksOnRow(y) == width) {
                deleteRow(y);
                dropRowsAbove(y);
                points += width;
            }
        }
        return points;
    }

    private void deleteRow(int y) {
        for (int x = 0; x < width; x++) {
            frozenBlocks[y][x] = null;
        }
    }

    private void dropRowsAbove(int y) {
        for (int h = y; h >= 0; h--) {
            if (h == 0) {
                for (int x = 0; x < width; x++) {
                    frozenBlocks[h][x] = null;
                }
                continue;
            }
            for (int x = 0; x < width; x++) {
                frozenBlocks[h][x] = frozenBlocks[h - 1][x];
            }
        }
    }

    /**
     * two-dimensional array that contains all the frozen blocks in the field.
     *
     * @return two dimensional array of blocks
     */
    public Block[][] getFrozenBlocks() {
        return this.frozenBlocks;
    }

    /**
     * Checks whether or not the spot of the input coordinates is vacant.
     *
     * @param x - coordinate or column number
     * @param y - coordinate or row number
     * @return True if coordinates are above the field in y-axis. True if
     * coordinates exist in the limits of the field and the spot is vacant.
     * False if coordinates exist and spot is not vacant. False if coordinates
     * are over the sides or under the bottom of the field.
     */
    public boolean spotIsVacant(int x, int y) {
        // if y<0, or coordinates point towards a vacant spot in the field, the spot is considered to be vacan. 
        if (y < 0) {
            return true;
        } else if (x < 0 || x >= width) {
            return false;
        } else if (y >= height) {
            return false;
        }
        return frozenBlocks[y][x] == null;
    }

    /**
     * Returns the number of blocks on the specified row.
     *
     * @param row number
     * @return number of blocks on the row
     */
    public int getNumberOfBlocksOnRow(int row) {
        int i = 0;
        for (int x = 0; x < width; x++) {
            if (frozenBlocks[row][x] != null) {
                i++;
            }
        }
        return i;
    }

    /**
     * Checks whether the field contains no pieces.
     *
     * @return True if there are no frozen pieces.
     */
    public boolean isEmpty() {
        for (int row = height - 1; row >= 0; row--) {
            for (int col = 0; col < width; col++) {
                if (frozenBlocks[row][col] != null) {
                    return false;
                }
            }
        }
        return true;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    /**
     * Resets the field, so that all elements in matrix point to NULL.
     */
    public void reset() {
        this.frozenBlocks = new Block[height][width];
    }

}
