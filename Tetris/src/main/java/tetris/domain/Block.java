/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.domain;

/**
 *
 * @author isjani
 */
public class Block {
    private int x;
    private int y;
    
    public Block(int x, int y){
        this.x=x;
        this.y=y;
    }
    
    public void move(Direction dir){
        this.x += dir.x;
        this.y += dir.y;
    }
    
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
}
