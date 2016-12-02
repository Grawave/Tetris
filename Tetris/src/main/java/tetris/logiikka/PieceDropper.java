/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.logiikka;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * PieceDropper is a separate thread. It tells the Communicator to move the
 * piece down at a standard time interval for as long as the game is active.
 *
 * @author isjani
 */
public class PieceDropper extends Thread {

    private Communicator cP;

    public PieceDropper(Communicator cP) {
        super();
        this.cP = cP;
    }

    /**
     * Tells the communicator to move the piece at a standard time interval.
     */
    @Override
    public synchronized void run() {
        while (cP.gameIsActive()) {
            try {
                wait(500);
            } catch (InterruptedException ex) {
                System.out.println("pieceDropper was interrupted");
                break;
            }
            if (!cP.gameIsActive()) {
                break;
            }
            cP.movePiece(Direction.DOWN);
        }
    }
}
