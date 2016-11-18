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
public enum Rotation {
    LEFT(new int[]{0,1},new int[]{-1,0}),
    RIGHT(new int[]{0,-1},new int[]{1,0});
    
    public int[] xMultiplier;
    public int[] yMultiplier;
    
    private Rotation(int[] a, int[] b) {
        xMultiplier=a;
        yMultiplier=b;
    }
}
