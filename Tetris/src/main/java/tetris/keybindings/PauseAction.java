/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.keybindings;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import tetris.communication.Communicator;

/**
 * Asks the communicator to pause the game when triggered.
 * @author jani
 */
public class PauseAction extends AbstractAction {

    private Communicator communicator;
    /**
     * Creates a new instance of pause action that communicates with
     * the given communicator.
     * @param communicator to communicate with.
     */
    public PauseAction(Communicator communicator) {
        this.communicator = communicator;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        communicator.pauseGame();
    }
}
