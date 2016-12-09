/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.timer;

import tetris.communication.Communicator;
import java.util.logging.Level;
import java.util.logging.Logger;
import tetris.logiikka.Direction;

/**
 * PieceDropper is a separate thread. It tells the Communicator to move the
 * piece down at a standard time interval for as long as the game is active.
 *
 * @author isjani
 */
public class PieceDropper extends Thread {

    private Communicator cP;
    
    /**
     * Creates a new instance of piece dropper, that communicates with the
     * give communicator.
     * @param cP the given communicator.
     */
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
