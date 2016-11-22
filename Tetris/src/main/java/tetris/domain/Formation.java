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
 *
 * @author isjani
 */
public enum Formation {
    O(new int[]{0, 1, 1, 0}, new int[]{0, 0, 1, 1}, -1, Color.RED), //(0,0) is top left of the square
    L(new int[]{0, 1, 0, 0}, new int[]{0, 0, -1, -2}, 2, Color.BLUE), //(0,0) is bottom left of standing L
    T(new int[]{0, 0, 1, -1}, new int[]{0, 1, 1, 1}, 1, Color.CYAN), //(0,0) is the top piece of T lying down on its long back
    S(new int[]{0, -1, 1, 0}, new int[]{0, 0, -1, -1}, 3, Color.ORANGE), //(0,0) is the bottom right of S
    I(new int[]{0, -1, 1, 2}, new int[]{0, 0, 0, 0}, 0, Color.WHITE), // (0,0)  is the second left piece of I lying down
    Z(new int[]{0, 0, -1, 1}, new int[]{0, -1, -1, 0}, 1, Color.yellow), // (0,0) is the bottom left block
    J(new int[]{0, -1, 0, 0}, new int[]{0, 0, -1, -2}, 2, Color.DARK_GRAY); // (0,0) is  is bottom right of standing J// (0,0) is  is bottom right of standing J

    public int[] xVal;
    public int[] yVal;
    public int pivotIndex;
    public Color color;

    private Formation(int[] x, int[] y, int p, Color color) {
        this.xVal = x;
        this.yVal = y;
        this.pivotIndex = p;
        this.color = color;
    }

    public static Formation getRandom() {
        List<Formation> forms = new ArrayList<>();
        Collections.addAll(forms, Formation.values());
        Collections.shuffle(forms);
        return forms.get(0);
    }
}
