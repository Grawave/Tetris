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
/*
This class receives messages from threads, acts upon GameSituation (based on 
messages). After every communication, asks to draw the gui again.
 */
public class CommunicationPlatform {

    private GameSituation gs;

    public CommunicationPlatform(GameSituation gs) {
        this.gs = gs;
    }

    /* this method can be called by timer thread (drops piece every 1 sec) and the keyListener thread */
    public void movePiece(Direction direction) {
        MoveResult moveResult = gs.movePiece(direction);
        actBasedOnResult(moveResult);
    }

    /* this method can be called by the keyListener thread */
    public void rotatePiece(Rotation rotation) {
        gs.rotatePiece(rotation);
    }

    public void actBasedOnResult(MoveResult moveResult) {
        if (moveResult.pieceWasMoved) {
            // do nothing
        } else if (moveResult.gameWon) {
            victory();
        } else if (moveResult.gameLost) {
            defeat();
        } else if (moveResult.pieceWasFrozen) {
            gs.createActivePiece();
        }
    }

    public void defeat() {

    }

    public void victory() {

    }

}
