/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.gui;

import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * A class that contains swing components that display backgroundImages.
 * @author isjani
 */
public class BackgroundImage {
    /**
     * ImageHolder that displays the background left of gamesituation panel.
     */
    private JLabel leftImageHolder;
    /**
     * ImageHolder that displays the background right of gamesituation panel.
     */
    private JLabel rightImageHolder;
    
    /**
     * Creates a new instance of BackgroundImage. Makes two JLabels that display
     * the image in the given URL.
     * @param PIC_FILEPATH given URL for the image.
     */
    public BackgroundImage(String PIC_FILEPATH) {
        ImageIcon leftImage = new ImageIcon(PIC_FILEPATH);
        leftImageHolder = new JLabel();
        leftImageHolder.setIcon(leftImage);
        leftImageHolder.setBounds(0, 0, 800, 1200);
        
        ImageIcon rightImage = new ImageIcon(PIC_FILEPATH);
        rightImageHolder = new JLabel();
        rightImageHolder.setIcon(rightImage);
        rightImageHolder.setBounds(0, 0, 800, 1200);
        
    }

    public JLabel getLeftImageHolder() {
        return this.leftImageHolder;
    }

    public JLabel getRightImageHolder() {
        return this.rightImageHolder;
    }
}
