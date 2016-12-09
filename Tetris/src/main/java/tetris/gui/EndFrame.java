/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import tetris.communication.Communicator;

/**
 * This frame is to be displayed as the game ends.
 * @author isjani
 */
public class EndFrame implements Runnable {

    private JFrame frame;
    private Communicator communicator;
    private boolean gameWon;

    /**
     * Creates a new instance of EndFrame. The end result varies according
     * to gameWon status.
     * @param gameWon indicates if the game was won.
     * @param communicator is told to create a new game if the player wants to.
     */
    public EndFrame(boolean gameWon, Communicator communicator) {
        this.communicator = communicator;
        this.gameWon = gameWon;
    }

    /**
     * Creates a ContentPanel and adds components to it.Then sets the frame
     * visible.
     */
    @Override
    public void run() {
        frame = new JFrame("Tetris by Grawave");
        frame.setPreferredSize(new Dimension(500, 500));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        createComponents();
        frame.pack();
        frame.setVisible(true);
    }

    private void createComponents() {
        String condition;
        if (gameWon) {
            condition = "You won the game! Amazing! ";
        } else {
            condition = "You lost the game.. Maybe next time!";
        }
        JButton button = new JButton(condition);
        button.addActionListener(new NewGameListener(communicator, frame));

        frame.getContentPane()
                .add(button);
    }

    private class NewGameListener implements ActionListener {

        private Communicator communicator;
        private JFrame frame;
        
        /**
         * Creates a listener that upon activation will ask the communicator
         * to create a new game.
         * @param communicator
         * @param frame 
         */
        public NewGameListener(Communicator communicator, JFrame frame) {
            this.communicator = communicator;
            this.frame = frame;
        }
        
        /**
         * Asks the communicator to create a  new game upon activation.
         * @param e actionEvent.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            communicator.newGame();
        }

    }
}
