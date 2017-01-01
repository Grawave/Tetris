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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 * This class contains the quotes to be displayed.
 *
 * @author isjani
 */
public class DistractionQuotes {
    
    private final String QUOTES_FILENAME = "quotes.txt";
    /**
     * Array containing all the quotes.
     */
    private final String[] quotes;

    /**
     * Creates a new instance of this class. Constructor loads in the quotes
     * from a file.
     */
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

    /**
     * Returns the quotes as a table.
     *
     * @return table of quotes.
     */
    public String[] getQuotes() {
        return this.quotes;
    }

    private void quoteSettings(JTextArea quoteArea) {
        quoteArea.setBounds(0, 0, 600, 1000);
        quoteArea.setFont(new Font("Serif", Font.PLAIN, 18));
        quoteArea.setOpaque(false);
        quoteArea.setForeground(Color.white);
        quoteArea.setFocusable(false);
    }
    /**
     * Creates a JTextArea containing a random quote.
     * @return JTextArea containing a random quote.
     */
    public JTextArea newRandomQuoteArea() {
        JTextArea quoteArea = new JTextArea(newRandomQuote());
        quoteSettings(quoteArea);
        return quoteArea;
    }

    private String newRandomQuote() {
        Random random = new Random();
        int index = random.nextInt(quotes.length - 1);
        String quote = quotes[index];
        return modifyToFitLines(quote);
    }

    private String modifyToFitLines(String string) {
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
}
