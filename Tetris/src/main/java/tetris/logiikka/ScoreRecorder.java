/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.logiikka;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class holds connection to highscore file and communicates with it.
 *
 * @author isjani
 */
public class ScoreRecorder {

    /**
     * Current highScore.
     */
    private int highScore;
    private final String HIGH_SCORE_FILENAME = "highscore.txt";

    /**
     * Creates a new instance of this class. Loads the current highscore from
     * the file.
     */
    public ScoreRecorder() {
        this.highScore = readHighScore();
    }

    private int readHighScore() {
        int result = 0;
        try {
            Scanner scanner = new Scanner(new File(HIGH_SCORE_FILENAME));
            result = Integer.parseInt(scanner.nextLine());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ScoreRecorder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    /**
     * Returns the current highscore.
     *
     * @return current highscore.
     */
    public int getHighScore() {
        return this.highScore;
    }

    /**
     * If new Score is higher than current highscore, opens the highscore file
     * and rewrites it.
     *
     * @param newScore to be set as highscore.
     */
    public void update(int newScore) {
        if (newScore > highScore) {
            try {
                PrintWriter writer = new PrintWriter(HIGH_SCORE_FILENAME);
                writer.println(newScore);
                writer.close();
                this.highScore = newScore;
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ScoreRecorder.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
