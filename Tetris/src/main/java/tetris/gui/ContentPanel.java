/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import tetris.logiikka.Communicator;
import tetris.logiikka.Direction;
import tetris.logiikka.Rotation;

/**
 *
 * @author isjani
 */
public class ContentPanel extends JPanel {

    private final Dimension GS_MIN = new Dimension(300, 600);
    private final Dimension GS_PREF = new Dimension(400, 800);
    private final Dimension GS_MAX = new Dimension(500, 1100);
    private Communicator communicator;
    private JLabel scoreLabel;
    
    private final String PIC_URL ="/home/jani/Tetris/Tetris/freeBackground.jpg"; //"~/Tetris/Tetris/freeBackgound.jpg"; // /home/jani/Tetris/Tetris/freeBackground.jpg

    private GridLayout layout;

    public ContentPanel(Communicator communicator) {
        super();
        this.communicator = communicator;
        createLayout();
        createScoreBoard();
//        createEmptyLeftFiller();
        createKeyBindings();
    }

    private void createScoreBoard() {
        JLayeredPane lPane = new JLayeredPane();
        lPane.setLayout(null);
        lPane.setPreferredSize(GS_PREF);
        
        ImageIcon image = new ImageIcon(PIC_URL);
        JLabel imageHolder = new JLabel();
        imageHolder.setIcon(image);
        imageHolder.setBounds(0,0,500,1000);
        
        lPane.add(imageHolder,0,0);
        
        JLabel scoreLabel = new JLabel("<html>SCORE<br>"+ communicator.getScore()+ "</html>");
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        scoreLabel.setBounds(300,300,100,100);
        
        lPane.add(scoreLabel,1,1);
        
        add(lPane);
    }

    private void createKeyBindings() {
        InputMap iPM = this.getInputMap();
        ActionMap aCM = this.getActionMap();

        iPM.put(KeyStroke.getKeyStroke("LEFT"), "LEFT");
        aCM.put("LEFT", new MoveAction(Direction.LEFT, communicator));

        iPM.put(KeyStroke.getKeyStroke("RIGHT"), "RIGHT");
        aCM.put("RIGHT", new MoveAction(Direction.RIGHT, communicator));

        iPM.put(KeyStroke.getKeyStroke("DOWN"), "DOWN");
        aCM.put("DOWN", new MoveAction(Direction.DOWN, communicator));

        iPM.put(KeyStroke.getKeyStroke("Z"), "ROTATE_LEFT");
        aCM.put("ROTATE_LEFT", new RotateAction(Rotation.LEFT, communicator));

        iPM.put(KeyStroke.getKeyStroke("C"), "ROTATE_RIGHT");
        aCM.put("ROTATE_RIGHT", new RotateAction(Rotation.RIGHT, communicator));

        iPM.put(KeyStroke.getKeyStroke("P"), "PAUSE");
        aCM.put("PAUSE", new PauseAction(communicator));
    }

    /**
     * creates a 1 row 3 column GridLayout
     */
    private void createLayout() {
        layout = new GridLayout(0, 3);
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
     *
     * @param gs GameSituationPanel to be added.
     */
    public void addGS(GameSituationPanel gs) {
        add(gs);
        gs.setPreferredSize(GS_PREF);
        gs.setMinimumSize(GS_MIN);
        gs.setMaximumSize(GS_MAX);
    }

    private static class MoveAction extends AbstractAction {

        private Direction dir;
        private Communicator communicator;

        public MoveAction(Direction dir, Communicator c) {
            this.dir = dir;
            this.communicator = c;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            communicator.movePiece(dir);
        }

    }

    private static class RotateAction extends AbstractAction {

        private Rotation rot;
        private Communicator communicator;

        public RotateAction(Rotation rot, Communicator c) {
            this.rot = rot;
            this.communicator = c;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            communicator.rotatePiece(rot);
        }

    }

    private static class PauseAction extends AbstractAction {

        private Communicator communicator;

        public PauseAction(Communicator communicator) {
            this.communicator = communicator;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            communicator.pauseGame();
        }
    }
}
