package main;


import tetris.logiikka.Engine;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *  Here it all starts. Press the button.
 * @author isjani
 */
public class Main {

    /**
     * Unleash the hounds.
     * @param args no arguments taken from command line.
     */
    public static void main(String[] args) {
        Engine engine = new Engine();
        engine.initialize();
    }
}
