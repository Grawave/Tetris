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
        
        
//        frame.addKeyListener(new );
//        this.gameSituationPanel.setPreferredSize(new Dimension(500, 500));
//        this.gameSituationPanel.setMinimumSize(new Dimension(500, 500));
//        this.gameSituationPanel.setMaximumSize(new Dimension(500, 500));
    }

    private void assemble() {
        contentPanel.addGS(this.gameSituationPanel);
    }

    public void rePaintSituation(Color[][] colorTable) {
        gameSituationPanel.rePaintSituation(colorTable);
    }

    public void close() {
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }

}
