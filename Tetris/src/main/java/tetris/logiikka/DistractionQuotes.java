/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.logiikka;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JTextArea;

/**
 *
 * @author isjani
 */
public class DistractionQuotes {

    private final URL QUOTES_URL = getClass().getClassLoader().getResource("file/quotes.txt");
    
    private String[] quotes;

    public DistractionQuotes() {
        System.out.println(QUOTES_URL.getPath());
        quotes = readQuotes();
    }

    public String[] readQuotes() {
        Scanner reader;
        ArrayList<String> quoteList = new ArrayList<>();
        try {
            reader = new Scanner(new File(QUOTES_URL.getPath()));
        } catch (Exception e) {
            throw new IllegalArgumentException("no such filepath for quotes");
        }
        while (reader.hasNext()) {
            quoteList.add(reader.nextLine());
        }
        String[] quotes = new String[quoteList.size()];
        for (int i = 0; i < quotes.length; i++) {
            quotes[i] = quoteList.get(i);
        }
        return quotes;
    }

    public String[] getQuotes() {
        return this.quotes;
    }
}
