/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.domain;

import java.awt.Color;
import tetris.logiikka.Direction;

/**
 *
 * @author isjani
 */
/**
 * Blocks are building blocks for pieces.
 */
public class Block {

    private int x;
    private int y;
    private Color color;

    /**
     * Constructs a block with color and coordinates of given x,y and color values.
     * @param x coordinate
     * @param y coordinate
     * @param color of block
     */
    public Block(int x, int y, Color color) {
        this.x = x;
        this.y = y;
    }

    /**
     * Moves the block to the given direction
     * @param direction of movement 
     */
    public void move(Direction direction) {
        this.x += direction.x;
        this.y += direction.y;
    }

    /**
     *  @return the blocks location among the x-axis
     */
    public int getX() {
        return x;
    }
    
    /**
     * @return the blocks location among the y-axis
     */
    public int getY() {
        return y;
    }
    
    /**
     * @return the color associated with this block.
     */
    public Color getColor() {
        return this.color;
    }
    
    /**
     * Resets the block's coordinates to given x and y values.
     * @param x new x-coordinate for the block.
     * @param y new y-coordinate for the block.
     */
    public void setCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
