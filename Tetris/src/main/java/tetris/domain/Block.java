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
     * @param x
     * @param y
     * @param color 
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
     * Informs the blocks location among the x-axis
     *  @return x-coordinate
     */
    public int getX() {
        return x;
    }
    
    /**
     * Informs the blocks location among the y-axis
     * @return 
     */
    public int getY() {
        return y;
    }
    
    /**
     * Informs the color associated with this block.
     * @return 
     */
    public Color getColor() {
        return this.color;
    }
    
    /**
     * Resets the block's coordinates to given x and y values.
     * @param x
     * @param y 
     */
    public void setCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
