/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import tetris.communication.Communicator;
import tetris.logiikka.DistractionQuotes;

/**
 * The frame for all the content displayed during the game.
 *
 * @author isjani
 */
public class TetrisFrame implements Runnable {

    private JFrame frame;
    /**
     * Communication between user pressable keybindings and game logic goes
     * through this communicator.
     */
    private Communicator communicator;
    /**
     * width of the GameSituationPanel to be created.
     */
    private int gridWidth;
    /**
     * height of the GameSituationPanel to be created.
     */
    private int gridHeight;
    /**
     * Panel that has all the content.
     */
    private ContentPanel contentPanel;

    /**
     * Creates a new instance of main game frame that communicates with the
     * given communicator.
     *
     * @param communicator to communicate with.
     * @param width for the game grid.
     * @param height for the game grid.
     */
    public TetrisFrame(Communicator communicator, int width, int height) {
        this.communicator = communicator;
        this.gridWidth = width;
        this.gridHeight = height;
    }

    /**
     * Creates a ContentPanel and adds components to it.Then sets the frame
     * visible.
     */
    @Override
    public void run() {
        frame = new JFrame("Tetris by Grawave");
        frame.setPreferredSize(new Dimension(1400, 1000));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        createContentPane();
        frame.pack();
        frame.setVisible(true);
    }

    private void createContentPane() {
        contentPanel = new ContentPanel(communicator);
        GameSituationPanel gsp = new GameSituationPanel(gridWidth, gridHeight);
        gsp.initialize();
        contentPanel.addGS(gsp);
        frame.getContentPane().add(contentPanel);
    }

    /**
     * Passes the given colorTable to gameSituationpanel.
     *
     * @param colorTable the given colorTable
     */
    public void rePaintSituation(Color[][] colorTable) {
        contentPanel.repaintSituation(colorTable);
    }

    /**
     * Closes this particular frame without shutting down the program.
     */
    public void close() {
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }

    /**
     * Tells the contentPanel to update its scoreboard and distraction board.
     */
    public void updateBoards() {
        contentPanel.updateBoards();
    }

    /**
     * Passes the given highScore argument to contentPanel.
     *
     * @param highScore given highScore argument.
     */
    public void setHighScore(int highScore) {
        contentPanel.setHighScore(highScore);
    }

    /**
     * Passes the given quotes to contentPanel.
     *
     * @param quotes given quotes.
     */
    public void setDistractionBoard(DistractionQuotes quotes) {
        contentPanel.createDistractionBoard(quotes);
    }

    /**
     * disposes the frame.
     */
    public void dispose() {
        frame.dispose();
    }

    /**
     * updates the highScore board.
     *
     * @param newScore new highScore.
     */
    public void updateHighScore(int newScore) {
        contentPanel.updateHighScore(newScore);
    }

}
