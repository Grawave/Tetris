/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.logiikka;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author isjani
 */
public class Field {

    private Block[][] frozenBlocks;
    private final int width;
    private final int height;

    public Field(int width, int height) {
        this.frozenBlocks = new Block[height][width];
        this.width = width;
        this.height = height;
    }

    public MoveResult freezePiece(Piece p) {
        MoveResult moveResult = new MoveResult();
        List<Block> blocks = p.getBlocks();

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
        dropRows();

        if (this.isEmpty()) {
            moveResult.gameWon = true;
        }
        moveResult.pieceWasFrozen = true;
        return moveResult;
    }

    private void dropRows() {
        for (int y = 0; y < height; y++) {
            if (getNumberOfBlocksOnRow(y) == width) {
                deleteRow(y);
                dropRowsAbove(y);
            }
        }
    }

    private void deleteRow(int y) {
        for (int x = 0; x < width; x++) {
            frozenBlocks[y][x] = null;
        }
    }

    private void dropRowsAbove(int y) {
        for (int h = y; h > 0; h--) {
            for (int x = 0; x < width; x++) {
                frozenBlocks[y][x] = frozenBlocks[y - 1][x];
            }
        }
    }

    public Block[][] getFrozenBlocks() {
        return this.frozenBlocks;
    }

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

    public int getNumberOfBlocksOnRow(int row) {
        int i = 0;
        for (int x = 0; x < width; x++) {
            if (frozenBlocks[row][x] != null) {
                i++;
            }
        }
        return i;
    }

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

}
