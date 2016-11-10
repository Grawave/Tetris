/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author isjani
 */
public class Piece {
    private List<Block> blocks;
    public Piece(int x, int y, Formation f){
        this.blocks=new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            blocks.add(new Block(x+f.xVal[i],y+f.yVal[i]));
        }
    }
    public void move(Direction dir){
        for (Block b : blocks) {
            b.move(dir);
        }
    }
}
