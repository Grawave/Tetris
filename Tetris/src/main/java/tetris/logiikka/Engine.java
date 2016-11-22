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
    private final int height = 10;

    public Engine() {
    }

    public void start() {
        initialize();
    }


    public void defeat() {

    }

    public void victory() {

    }

    public void initialize() {
        Field field = new Field(width, height);
        GameSituation gs = new GameSituation(field);
        gs.createActivePiece();
        
        Communicator cP= new CommunicationPlatform(gs);
        
        TetrisFrame frame = new TetrisFrame(cP,width,height);
        cP.setFrame(frame);

        // cP.setGui(gui)
        // gui.setCommunicationPlatform(cP)
        
        Thread pieceDropper = new PieceDropper(cP);
        pieceDropper.run();
    }

    public Piece createActivePiece() {
        Piece randomPiece = new Piece(width / 2, 0, Formation.getRandom());
        return randomPiece;
    }

}
