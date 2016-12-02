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
import tetris.logiikka.Communicator;

/**
 * The frame for all the content
 *
 * @author isjani
 */
public class TetrisFrame implements Runnable {

    private JFrame frame;
    private Communicator communicator;
    private GameSituationPanel gameSituationPanel;
    private int gridWidth;
    private int gridHeight;
    private ContentPanel contentPanel;

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
        createComponents();
        assemble();
        frame.pack();
        frame.setVisible(true);
    }

    private void createContentPane() {
        contentPanel = new ContentPanel(communicator);
        frame.getContentPane().add(contentPanel);
    }

    private void createComponents() {
        this.gameSituationPanel = new GameSituationPanel(gridWidth, gridHeight);
        this.gameSituationPanel.initialize();
    }

    private void assemble() {
        contentPanel.addGS(this.gameSituationPanel);
    }

    /**
     * Passes the given colorTable to gameSituationpanel.
     *
     * @param colorTable the given colorTable
     */
    public void rePaintSituation(Color[][] colorTable) {
        gameSituationPanel.rePaintSituation(colorTable);
    }

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
    public void setDistractionBoard(String[] quotes) {
        contentPanel.createDistractionBoard(quotes);
    }

    /**
     * disposes the frame
     */
    public void dispose() {
        frame.dispose();
    }

}
