/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.logiikka;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Scanner;

/**
 *
 * @author isjani
 */
public class ScoreRecorder {

    private int highScore;
    private final URL HIGH_SCORE_URL = getClass().getClassLoader().getResource("file/highscore.txt");
    private final File HIGH_SCORE_FILE = new File(getClass().getClassLoader().getResource("file/highscore.txt").getFile());

    public ScoreRecorder() {
        this.highScore = readHighScore();
    }

    private int readHighScore() {
        int highS = 0;
        try (Scanner reader = new Scanner(HIGH_SCORE_FILE.getAbsoluteFile())) {
            if (reader.hasNext()) {
                try {
                    highS = Integer.parseInt(reader.next());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    throw new IllegalArgumentException("highscore unreadable");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        return highS;
    }

    public int getHighScore() {
        return this.highScore;
    }

    public void update(int newScore) {
        if (newScore > highScore) {
            try {
                PrintWriter writer = new PrintWriter(HIGH_SCORE_URL.getPath(), "UTF-8");
                writer.println(newScore);
                writer.close();
                this.highScore = newScore;
            } catch (IOException e) {
                throw new IllegalStateException("unable to write highscore.txt");
            }
        }
    }
}
