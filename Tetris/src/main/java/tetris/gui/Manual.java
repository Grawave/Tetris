/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.gui;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

/**
 * Contains the information about keybindings.
 * @author isjani
 */
public class Manual {
    
    /**
     * Displays the information about keybindings.
     */
    private JLabel label;

    /**
     * Creates a new instance of Manual that contains the informatio nabout
     * keybindings.
     */
    public Manual() {
        label = new JLabel("<html>Move Right - Right Arrow <br>"
                + "Move Left   - Left Arrow <br>"
                + "Move Down   - Down Arrow <br>"
                + "Rotate Right - C <br> "
                + "Rotate Left  - Z <br> "
                + "Pause        - P</html>");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Serif", Font.PLAIN, 20));
        label.setBounds(50, 300, 300, 300);
    }

    public JLabel getManual() {
        return this.label;
    }
}
