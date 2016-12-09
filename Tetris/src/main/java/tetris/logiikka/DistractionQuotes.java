/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.logiikka;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author isjani
 */
public class DistractionQuotes {

    private final String QUOTES_FILENAME = "quotes.txt";
    private final String[] quotes;

    public DistractionQuotes() {
        quotes = readQuotes();
    }

    /**
     * Reads quotes from the running location.
     *
     * @return quotes as a table.
     */
    private String[] readQuotes() {
        List<String> utilList = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(QUOTES_FILENAME));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.isEmpty()) {
                    utilList.add(line);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DistractionQuotes.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[] result = new String[utilList.size()];
        for (int i = 0; i < utilList.size(); i++) {
            result[i] = utilList.get(i);
        }
        return result;
    }

    public String[] getQuotes() {
        return this.quotes;
    }
}
