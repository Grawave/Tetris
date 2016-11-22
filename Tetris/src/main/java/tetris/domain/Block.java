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
public class Block {

    private int x;
    private int y;
    private Color color;

    public Block(int x, int y, Color color) {
        this.x = x;
        this.y = y;
    }

    public void move(Direction dir) {
        this.x += dir.x;
        this.y += dir.y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public Color getColor() {
        return this.color;
    }
    public void setCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
