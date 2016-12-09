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
import java.net.URL;
import java.util.Random;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import tetris.logiikka.Communicator;
import tetris.logiikka.Direction;
import tetris.logiikka.Rotation;

/**
 * ContentPanel contains everything to be displayed in the TetrisFrame.
 *
 * @author isjani
 */
public class ContentPanel extends JPanel {

    private final Dimension GS_MIN = new Dimension(300, 600);
    private final Dimension GS_PREF = new Dimension(400, 800);
    private final Dimension GS_MAX = new Dimension(500, 1100);
    private Communicator communicator;
    private JLabel scoreLabel;
    private JTextArea quoteArea;
    private JLayeredPane leftLayeredPane;
    private JLayeredPane rightLayeredPane;
    private String[] quotes;
    private JLabel highScoreLabel;

//    private final String PIC_URL = "freeBackground.jpg";
    private final URL PIC_URL =getClass().getClassLoader().getResource("freeBackground.jpg");
    private GridLayout layout;

    /**
     * Initializes the panel with a ScoreBoard and sets up keybindings.
     *
     * @param communicator to communicate with.
     */
    public ContentPanel(Communicator communicator) {
        super();
        this.communicator = communicator;
        createLayout();
        createScoreBoard();
        createKeyBindings();
        rightLayeredPane = new JLayeredPane();
        add(rightLayeredPane);
        
    }

    /**
     * Creates a panel that contains a background image and distractive quotes.
     * Because why not?
     *
     * @param quotes quotes to be displayed.
     */
    public void createDistractionBoard(String[] quotes) {
        rightLayeredPane.setLayout(null);
        rightLayeredPane.setPreferredSize(GS_PREF);

        this.quotes = quotes;
        quoteSettings();

        ImageIcon image = new ImageIcon(PIC_URL);
        JLabel imageHolder = new JLabel();
        imageHolder.setIcon(image);
        imageHolder.setBounds(0, 0, 800, 1200);

        rightLayeredPane.add(imageHolder, 0, 0);
        rightLayeredPane.add(quoteArea, 4, 4);

    }

    private String newRandomQuote() {
        Random random = new Random();
        int index = random.nextInt(quotes.length - 1);
        String quote = quotes[index];
        return modifyToLines(quote);
    }

    private String modifyToLines(String string) {
        String modified = "Distractions for your mind.. \n  \n";
        boolean lineChange = false;
        int i = 0;
        while (i < string.length()) {
            char ch = string.charAt(i);
            if (i % 30 == 0 && i != 0) {
                lineChange = true;
            }
            if (lineChange && ch == ' ') {
                lineChange = false;
                modified += "\n";
            }
            modified += ch;
            i++;
        }
        return modified;
    }

    private void updateQuote() {
        rightLayeredPane.remove(quoteArea);
        quoteSettings();

        rightLayeredPane.add(quoteArea, 1, 1);
        rightLayeredPane.repaint();
    }

    private void quoteSettings() {
        quoteArea = new JTextArea(newRandomQuote());
        quoteArea.setBounds(0, 0, 600, 1000);
        quoteArea.setFont(new Font("Serif", Font.PLAIN, 18));
        quoteArea.setOpaque(false);
        quoteArea.setForeground(Color.white);
    }

    private void createScoreBoard() {
        leftLayeredPane = new JLayeredPane();
        leftLayeredPane.setLayout(null);
        leftLayeredPane.setPreferredSize(GS_PREF);

        ImageIcon image = new ImageIcon(PIC_URL);
        JLabel imageHolder = new JLabel();
        imageHolder.setIcon(image);
        imageHolder.setBounds(0, 0, 800, 1200);

        leftLayeredPane.add(imageHolder, 0, 0);

        JLabel manual = new JLabel("<html>Move Right - Right Arrow <br>"
                + "Move Left   - Left Arrow <br>"
                + "Rotate Right - C <br> "
                + "Rotate Left  - Z <br> "
                + "Pause        - P</html>");
        manual.setForeground(Color.WHITE);
        manual.setFont(new Font("Serif", Font.PLAIN, 20));
        manual.setBounds(50, 300, 300, 300);

        leftLayeredPane.add(manual, 4, 4);

        scoreLabel = new JLabel("<html>SCORE<br>" + communicator.getScore() + "</html>");
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(new Font("Serif", Font.PLAIN, 30));
        scoreLabel.setBounds(100, 10, 300, 300);

        leftLayeredPane.add(scoreLabel, 1, 1);

        add(leftLayeredPane);
    }

    /**
     * Updates the score status and puts a new random quote on the distraction
     * board.
     */
    public void updateBoards() {
        int score = communicator.getScore();

        leftLayeredPane.remove(scoreLabel);
        leftLayeredPane.repaint();

        scoreLabel = new JLabel("<html>SCORE<br>" + score + "</html>");
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(new Font("Serif", Font.PLAIN, 30));
        scoreLabel.setBounds(100, 10, 300, 300);

        leftLayeredPane.add(scoreLabel, 1, 1);
        leftLayeredPane.repaint();
        updateQuote();
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

    private void createLayout() {
        layout = new GridLayout(0, 3);
        setLayout(layout);
    }

    /**
     * Adds the GameSituationPanel to this ContentPanel. Sets it's dimensions
     *
     * @param gs GameSituationPanel to be added.
     */
    public void addGS(GameSituationPanel gs) {
        add(gs, 1);
        gs.setPreferredSize(GS_PREF);
        gs.setMinimumSize(GS_MIN);
        gs.setMaximumSize(GS_MAX);
    }

    /**
     * Sets the given highscore to be displayed on the scoreboard.
     *
     * @param highScore to be displayed.
     */
    public void setHighScore(int highScore) {
        highScoreLabel = new JLabel("<html>HIGHSCORE<br>" + highScore + "</html>");
        highScoreLabel.setFont(new Font("Serif", Font.PLAIN, 30));
        highScoreLabel.setForeground(Color.WHITE);
        highScoreLabel.setBounds(100, 100, 300, 300);
        leftLayeredPane.add(highScoreLabel, 3, 3);
    }
    /**
     * Updates the highscore to given value.
     * @param highScore  new highscore.
     */
    public void updateHighScore(int highScore) {
        leftLayeredPane.remove(highScoreLabel);
        
        highScoreLabel = new JLabel("<html>HIGHSCORE<br>" + highScore + "</html>");
        highScoreLabel.setFont(new Font("Serif", Font.PLAIN, 30));
        highScoreLabel.setForeground(Color.WHITE);
        highScoreLabel.setBounds(100, 100, 300, 300);
        
        leftLayeredPane.add(highScoreLabel, 3, 3);
        leftLayeredPane.repaint();
    }

}
