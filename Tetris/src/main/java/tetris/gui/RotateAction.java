/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.gui;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import tetris.logiikka.Communicator;
import tetris.logiikka.Rotation;

/**
 * When triggered, asks the communicator to rotate the piece to given
 * direction.
 *
 * @author jani
 */
public class RotateAction extends AbstractAction {

    private Rotation rot;
    private Communicator communicator;

    /**
     * Creates a new instance of rotate action that communicates with the given
     * communicator.
     *
     * @param communicator to communicate with.
     * @param rot direction of rotation.
     */
    public RotateAction(Rotation rot, Communicator communicator) {
        this.rot = rot;
        this.communicator = communicator;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        communicator.rotatePiece(rot);
    }

}
