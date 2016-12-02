/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.domain;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Describes how the blocks of given formation should be in relationship to each
 * other.
 *
 * @author isjani
 */
public enum Formation {
    /**
     * Square. No pivot. color?
     */
    O(new int[]{0, 1, 1, 0}, new int[]{0, 0, 1, 1}, -1, Color.RED), //(0,0) is top left of the square
    /**
     * L-shape. Pivot is the second highest block of a standing L.
     */
    L(new int[]{0, 1, 0, 0}, new int[]{0, 0, -1, -2}, 2, Color.BLUE), //(0,0) is bottom left of standing L
    /**
     * T-shape. Pivot is top middle block of a standing T.
     */
    T(new int[]{0, 0, 1, -1}, new int[]{0, 1, 1, 1}, 1, Color.CYAN), //(0,0) is the top piece of T lying down on its long back
    /**
     * S-shape. Pivot is the top middle block of a S.
     */
    S(new int[]{0, -1, 1, 0}, new int[]{0, 0, -1, -1}, 3, Color.ORANGE), //(0,0) is the bottom right of S
    /**
     * I-shape. Pivot is the second block from the left of I lying down (in the
     * moment of creation).
     */
    I(new int[]{0, -1, 1, 2}, new int[]{0, 0, 0, 0}, 0, Color.WHITE), // (0,0)  is the second left piece of I lying down
    /**
     * Z-shape. Pivot is the top middle block of a Z
     */
    Z(new int[]{0, 0, -1, 1}, new int[]{0, -1, -1, 0}, 1, Color.YELLOW), // (0,0) is the bottom left block
    /**
     * J-shape. Pivot is the second highest block of a standing J.
     */
    J(new int[]{0, -1, 0, 0}, new int[]{0, 0, -1, -2}, 2, Color.MAGENTA); // (0,0) is  is bottom right of standing J// (0,0) is  is bottom right of standing J

    /**
     * x values of block coordinates. x[i] corresponds to y[i]
     */
    public int[] xVal;
    /**
     * y values of block coordinates. y[i] corresponds to x[i]
     */
    public int[] yVal;
    /**
     * Starting coordinates of the pivot can be accessed by x[pivotIndex],
     * y[pivotIndex].
     */
    public int pivotIndex;
    /**
     * The color unique to the blocks of this formation.
     */
    public Color color;

    private Formation(int[] x, int[] y, int p, Color color) {
        this.xVal = x;
        this.yVal = y;
        this.pivotIndex = p;
        this.color = color;
    }

    /**
     * Generates and returns a random object from the pool of all possible formations.
     * @return A random Formation
     */
    public static Formation getRandom() {
        List<Formation> forms = new ArrayList<>();
        Collections.addAll(forms, Formation.values());
        Collections.shuffle(forms);
        return forms.get(0);
    }
}
