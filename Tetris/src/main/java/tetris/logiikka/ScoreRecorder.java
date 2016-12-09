/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.logiikka;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author isjani
 */
public class ScoreRecorder {

    private int highScore;
    private final String HIGH_SCORE_FILENAME = "highscore.txt";

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

    public int getHighScore() {
        return this.highScore;
    }

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
