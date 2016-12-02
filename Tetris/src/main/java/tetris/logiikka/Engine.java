/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.logiikka;

import tetris.domain.Piece;
import tetris.domain.Formation;
import tetris.domain.Field;
import tetris.domain.GameSituation;
import tetris.gui.TetrisFrame;

/**
 *
 * @author isjani
 */
public class Engine {

    private final int width = 10;
    private final int height = 20;
    
    private Communicator cP;
    private PieceDropper pieceDropper;

    public Engine() {
    }

    public void start() {
        initialize();

    }

    public void initialize() {
        Field field = new Field(width, height);
        GameSituation gs = new GameSituation(field);
        gs.createActivePiece();

        cP = new CommunicationPlatform();
        cP.setGameSituation(gs);

        TetrisFrame frame = new TetrisFrame(cP, width, height);
        cP.setFrame(frame);

        pieceDropper = new PieceDropper(cP);
        cP.setPieceDropper(pieceDropper);
//        

        frame.run();
        cP.setHighScore();
        cP.setDistractionBoard();
        cP.setEngine(this);
        pieceDropper.run();
    }
    public void reStart() {
        pieceDropper.interrupt();
        initialize();
    }
}
