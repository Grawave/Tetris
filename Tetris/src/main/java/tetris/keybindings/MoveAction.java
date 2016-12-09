/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.keybindings;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import tetris.communication.Communicator;
import tetris.logiikka.Direction;

/**
 * When triggered, asks the communicator to move the piece to given direction.
 * @author jani
 */
public class MoveAction extends AbstractAction {

    private Direction dir;
    private Communicator communicator;
    /**
     * Creates a new instance of move action that communicates with
     * the given communicator.
     * @param communicator to communicate with.
     * @param dir direction to move.
     */
    public MoveAction(Direction dir, Communicator communicator) {
        this.dir = dir;
        this.communicator = communicator;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        communicator.movePiece(dir);
    }

}
