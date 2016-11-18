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
    private int width;
    private int height;

    public Field(int width, int height) {
        this.frozenBlocks = new Block[width][height];
        this.width = width;
        this.height = height;
    }

    public void freezePiece(Piece p) {
        List<Block> blocks = p.getBlocks();
        List<Integer> yValues = new ArrayList<>();
        for (Block block : blocks) {
            int x = block.getX();
            int y = block.getY();
            yValues.add(y);
            if (frozenBlocks[x][y] != null) {
                throw new IllegalArgumentException("Field spot already reserved, can't freeze here " + x + " " + y);
            }
            frozenBlocks[x][y] = block;
        }
        checkRows(yValues);
    }

    public void checkRows(List<Integer> list) {
        Collections.sort(list);
        List<Integer> deleted = new ArrayList<>();
        for (Integer y : list) {
            boolean full = true;
            for (int x = 0; x < width; x++) {
                if (frozenBlocks[x][y] == null) {
                    full = false;
                    break;
                }
                if (full) {
                    deleteRow(y);
                    deleted.add(y);
                }
            }
        }
        Collections.sort(deleted);
        for (Integer y : deleted) {
            dropRows(y);
        }
    }

    public void deleteRow(int y) {
        for (int x = 0; x < width; x++) {
            frozenBlocks[x][y] = null;
        }
    }

    public void dropRows(int y) {
        for (int h = y; h > 0; h--) {
            for (int x = 0; x < width; x++) {
                frozenBlocks[x][y] = frozenBlocks[x][y - 1];
            }
        }
    }

    public Block[][] getFrozenBlocks() {
        return this.frozenBlocks;
    }
    public boolean spotIsVacant(int x, int y){
        return frozenBlocks[x][y]==null;
    }

    //input row is given as bottom =0
    public int getNumberOfBlocksOnRow(int row) {
        int i = 0;
        row=height-row-1;
        for (int x = 0; x < width; x++) {
            if (frozenBlocks[x][row] != null) {
                i++;
            }
        }
        return i;
    }
}
