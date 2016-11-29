/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author isjani
 */
public class GameSituationPanel extends JPanel {

    private Component[][] components;
    private int width;
    private int height;
    private final Color BACKGROUND_COLOR = Color.BLACK;
    private final Color BORDER_COLOR = Color.GRAY;
    
    private GridLayout layout;

    public GameSituationPanel(int width, int height) {
        components = new JLabel[height][width];
        this.width = width;
        this.height = height;
    }

    public void rePaintSituation(Color[][] colorTable) {
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                Component label = components[row][column];
                label.setBackground(colorTable[row][column]);
            }
        }
    }

    public void createLayout() {
        layout = new GridLayout(height, width);
        setLayout(layout);
    }

    public void initialize() {
        createLayout();
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
//                JLabel label = new JLabel("row " + Integer.toString(row) + " column " + Integer.toString(column));
                JLabel label = new JLabel();
                label.setBackground(BACKGROUND_COLOR);
                label.setOpaque(true);
                label.setBorder(BorderFactory.createLineBorder(BORDER_COLOR));
                components[row][column] = label;
                add(label);
            }
        }
    }
}
