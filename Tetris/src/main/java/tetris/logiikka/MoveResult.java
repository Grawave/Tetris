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
public class MoveResult {
    public boolean gameLost;
    public boolean gameWon;
    public boolean pieceWasFrozen;
    public boolean pieceWasMoved;
    
    public MoveResult() {
        this.gameLost=false;
        this.gameWon=false;
        this.pieceWasFrozen=false;
        this.pieceWasMoved=false;
    }
}
