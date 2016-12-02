/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.logiikka;

/**
 * Rotation has constants needed to rotate a piece.
 *
 * @author isjani
 */
public enum Rotation {
    LEFT(new int[]{0, 1}, new int[]{-1, 0}),
    RIGHT(new int[]{0, -1}, new int[]{1, 0});

    public int[] xMultiplier;
    public int[] yMultiplier;

    private Rotation(int[] a, int[] b) {
        xMultiplier = a;
        yMultiplier = b;
    }

    /**
     * Gives the direction opposite to the given rotation.
     * @param rot given rotation.
     * @return Rotation instance, reverse to input.
     */
    public static Rotation reverseRotation(Rotation rot) {
        if (rot == Rotation.LEFT) {
            return Rotation.RIGHT;
        }
        return Rotation.LEFT;
    }
}
