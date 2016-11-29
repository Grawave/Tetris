/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author isjani
 */
public class ContentPanel extends JPanel{
    private final int[] GS_DIMENSIONS_X=new int[]{300,400,500};
    private final int[] GS_DIMENSIONS_Y= new int[]{600,800,1000};
    private final Dimension GS_MIN = new Dimension(300,600);
    private final Dimension GS_PREF = new Dimension(400,800);
    private final Dimension GS_MAX = new Dimension(500,1000);
    
    private GridLayout layout;
    
    public ContentPanel() {
        super();
        createLayout();
        createEmptyLeftFiller();
    }
    
    /**
     * creates a 1 row 3 column GridLayout
     */
    private void createLayout() {
        layout = new GridLayout(0,3);
        setLayout(layout);
    }
    
    /**
     * Fills the first column of the GridLayout with a filler, invisible button.
     */
    private void createEmptyLeftFiller() {
        JButton button = new JButton();
        button.setVisible(false);
        add(button);
    }
    
    /**
     * Adds the GameSituationPanel to this ContentPanel. Sets it's dimensions
     * @param gs GameSituationPanel to be added.
     */
    public void addGS(GameSituationPanel gs) {
        add(gs);
        gs.setPreferredSize(GS_PREF);
        gs.setMinimumSize(GS_MIN);
        gs.setMaximumSize(GS_MAX);
        
    }
}
