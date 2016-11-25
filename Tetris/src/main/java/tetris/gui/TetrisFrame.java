/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import tetris.logiikka.Communicator;

/**
 *
 * @author isjani
 */
public class TetrisFrame implements Runnable {

    private JFrame frame;
    private Communicator communicator;
    private GameSituationPanel gameSituationPanel;
    private int gridWidth;
    private int gridHeight;

    public TetrisFrame(Communicator communicator, int width, int height) {
        this.communicator = communicator;
        this.gridWidth = width;
        this.gridHeight = height;
    }

    @Override
    public void run() {
        frame = new JFrame("Tetris by Grawave");
        frame.setPreferredSize(new Dimension(1400, 1000));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        createLayout();

        createComponents(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
    }

    public void createLayout() {
        BorderLayout layout = new BorderLayout();
        frame.getContentPane().setLayout(layout);
    }

    private void createComponents(Container container) {
        this.gameSituationPanel = new GameSituationPanel(gridWidth, gridHeight);
        this.gameSituationPanel.initialize();
        this.gameSituationPanel.setPreferredSize(new Dimension(50, 100));
        this.gameSituationPanel.setMinimumSize(new Dimension(50, 100));
        this.gameSituationPanel.setMaximumSize(new Dimension(50, 100));

        container.add(gameSituationPanel, BorderLayout.CENTER);
    }

    public void rePaintSituation(Color[][] colorTable) {
        gameSituationPanel.rePaintSituation(colorTable);
    }

}
