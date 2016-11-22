/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.logiikka;

import tetris.gui.TetrisFrame;

/**
 *
 * @author isjani
 */
public interface Communicator{
    
    public void movePiece(Direction direction);
    public void rotatePiece(Rotation rotation);
    public boolean gameIsActive();
    public void setFrame(TetrisFrame frame);
}
