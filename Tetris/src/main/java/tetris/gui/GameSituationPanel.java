/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.gui;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author isjani
 */
public class GameSituationPanel extends JPanel {

    private JLabel[][] labels;
    private int width;
    private int height;

    public GameSituationPanel(int width, int height) {
        labels = new JLabel[height][width];
        createLayout();
        initialize();
    }

    public void rePaintSituation(Color[][] colorTable) {
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                JLabel label = labels[row][column];
                label.setBackground(colorTable[row][column]);
            }
        }
    }

    public void createLayout() {
        GridLayout layout = new GridLayout(height, width);
        setLayout(layout);
    }

    public void initialize() {
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                JLabel label = new JLabel();
                label.setBackground(Color.BLACK);
                labels[row][column] = label;
            }
        }
    }
}
