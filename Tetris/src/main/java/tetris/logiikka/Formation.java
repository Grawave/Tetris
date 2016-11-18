/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.logiikka;

/**
 *
 * @author isjani
 */
public enum Formation {
    O(new int[]{0, 1, 1, 0}, new int[]{0, 0, 1, 1}, -1), //(0,0) is top left of the square
    L(new int[]{0, 1, 0, 0}, new int[]{0, 0, -1, -2}, 2), //(0,0) is bottom left of standing L
    T(new int[]{0, 0, 1, -1}, new int[]{0, 1, 1, 1}, 1), //(0,0) is the top piece of T lying down on its long back
    S(new int[]{0, -1, 1, 0}, new int[]{0, 0, -1, -1}, 3), //(0,0) is the bottom right of S
    I(new int[]{0, -1, 1, 2}, new int[]{0, 0, 0, 0}, 0), // (0,0)  is the second left piece of I lying down
    Z(new int[]{0, 0, -1, 1}, new int[]{0, -1, -1, 0}, 1), // (0,0) is the bottom left block
    J(new int[]{0, -1, 0, 0}, new int[]{0, 0, -1, -2}, 2); // (0,0) is  is bottom right of standing J// (0,0) is  is bottom right of standing J

    public int[] xVal;
    public int[] yVal;
    public int pivotIndex;

    private Formation(int[] x, int[] y, int p) {
        this.xVal = x;
        this.yVal = y;
        this.pivotIndex = p;
    }
}
