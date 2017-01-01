/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.logiikka;

/**
 * Direction has constants needed to move blocks one step towards desired
 * direction.
 *
 * @author isjani
 */
public enum Direction {

    UP(0, -1),
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0);
    
    /**
     * Amount to be moved in X direction.
     */
    public int moveX;
    /**
     * Amount to be moved in Y direction.
     */
    public int moveY;

    private Direction(int x, int y) {
        this.moveX = x;
        this.moveY = y;
    }

}
