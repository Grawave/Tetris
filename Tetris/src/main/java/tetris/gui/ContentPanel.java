/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.gui;

import tetris.keybindings.RotateAction;
import tetris.keybindings.PauseAction;
import tetris.keybindings.MoveAction;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import tetris.communication.Communicator;
import tetris.logiikka.Direction;
import tetris.logiikka.DistractionQuotes;
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
    /**
     * This communicator is used to communicate when user presses keybindings.
     */
    private Communicator communicator;
    /**
     * Label that contains the current score.
     */
    private JLabel scoreLabel;
    /**
     * quoteArea contains the currently displayed quote.
     */
    private JTextArea quoteArea;
    /**
     * The panel left of gamesituation panel.Contains scores, manual,background.
     */
    private JLayeredPane leftLayeredPane;
    /**
     * The panel right of gamesituation panel. Contains quotes and background.
     */
    private JLayeredPane rightLayeredPane;
    /**
     * Contains all the distraction quotes.
     */
    private DistractionQuotes quotes;
    /**
     * Label that contains the current highscore.
     */
    private JLabel highScoreLabel;
    private final BackgroundImage bImage;
    /**
     * Layout used to display GameSituationPanel and the two layered panels.
     */
    private GridLayout layout;
    /**
     * GameSituationPanel that displays the game situation to the user.
     */
    private GameSituationPanel gameSituationpanel;

    /**
     * Initializes the panel with a ScoreBoard and sets up keybindings.
     *
     * @param communicator to communicate with.
     */
    public ContentPanel(Communicator communicator) {
        super();
//        bImage = new BackgroundImage(getClass().getClassLoader().getResource("freeBackground.png"));
        bImage = new BackgroundImage("freeBackground.jpg");
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
    public void createDistractionBoard(DistractionQuotes quotes) {
        rightLayeredPane.setLayout(null);
        rightLayeredPane.setPreferredSize(GS_PREF);
        this.quotes = quotes;
        quoteArea = quotes.newRandomQuoteArea();
        rightLayeredPane.add(bImage.getRightImageHolder(), 0, 0);
        rightLayeredPane.add(quoteArea, 4, 4);

    }

    private void updateQuote() {
        rightLayeredPane.remove(quoteArea);
        quoteArea = quotes.newRandomQuoteArea();
        rightLayeredPane.add(quoteArea, 1, 1);
        rightLayeredPane.repaint();
    }

    private void createScoreBoard() {
        leftLayeredPane = new JLayeredPane();
        leftLayeredPane.setLayout(null);
        leftLayeredPane.setPreferredSize(GS_PREF);

        leftLayeredPane.add(bImage.getLeftImageHolder(), 0, 0);

        Manual manual = new Manual();
        leftLayeredPane.add(manual.getManual(), 4, 4);
        updateScore();
        add(leftLayeredPane);
    }

    private void updateScore() {
        scoreLabel = new JLabel("<html>SCORE<br>" + communicator.getScore() + "</html>");
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(new Font("Serif", Font.PLAIN, 30));
        scoreLabel.setBounds(100, 10, 300, 300);

        leftLayeredPane.add(scoreLabel, 1, 1);
        leftLayeredPane.repaint();
    }

    /**
     * Updates the score status and puts a new random quote on the distraction
     * board.
     */
    public void updateBoards() {
        leftLayeredPane.remove(scoreLabel);
        updateScore();
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
     * and location in the layout.
     *
     * @param gs GameSituationPanel to be added.
     */
    public void addGS(GameSituationPanel gs) {
        this.gameSituationpanel = gs;
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
        leftLayeredPane.repaint();
    }

    /**
     * Updates the highscore to given value.
     *
     * @param highScore new highscore.
     */
    public void updateHighScore(int highScore) {
        leftLayeredPane.remove(highScoreLabel);
        setHighScore(highScore);
    }

    /**
     * Updates the GameSituationPanel.
     *
     * @param colortable 2x2 matrix of colors.
     */
    public void repaintSituation(Color[][] colortable) {
        this.gameSituationpanel.rePaintSituation(colortable);
    }
}
