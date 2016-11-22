/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.logiikka;

import java.awt.Color;
import tetris.domain.Block;
import tetris.domain.MoveResult;
import tetris.domain.GameSituation;
import tetris.gui.TetrisFrame;

/**
 *
 * @author isjani
 */
/*
This class receives messages from threads, acts upon GameSituation (based on 
messages). After every communication, asks to draw the gui again.
 */
public class CommunicationPlatform implements Communicator {

    private GameSituation gs;
    private TetrisFrame frame;

    public CommunicationPlatform(GameSituation gs) {
        this.gs = gs;
    }

    /* this method can be called by pieceDropper thread (drops every 1 sec) 
    and the gui's keyListener */
    public synchronized void movePiece(Direction direction) {
        MoveResult moveResult = gs.movePiece(direction);
        actBasedOnResult(moveResult);
    }

    /* this method can be called by the keyListener */
    public synchronized void rotatePiece(Rotation rotation) {
        gs.rotatePiece(rotation);
        Color[][] colorTable = blockTableToColorTable(gs.getFieldAndPieceBlocks());
        frame.rePaintSituation(colorTable);
    }

    private Color[][] blockTableToColorTable(Block[][] blocks) {
        int rowCount = blocks.length;
        int columnCount = blocks[1].length;
        Color[][] colorTable = new Color[rowCount][columnCount];
        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                Block block = blocks[row][column];
                if (block == null) {
                    colorTable[row][column] = Color.BLACK;
                } else {
                    colorTable[row][column] = block.getColor();
                }
            }
        }
        return colorTable;
    }

    private void actBasedOnResult(MoveResult moveResult) {
        if (moveResult.pieceWasMoved) {
            frame.rePaintSituation(blockTableToColorTable(gs.getFieldAndPieceBlocks()));
        } else if (moveResult.gameWon) {
            victory();
        } else if (moveResult.gameLost) {
            defeat();
        } else if (moveResult.pieceWasFrozen) {
            gs.createActivePiece();
            frame.rePaintSituation(blockTableToColorTable(gs.getFieldAndPieceBlocks()));
        }
    }

    private void defeat() {
        gs.gameIsActive = false;
    }

    private void victory() {
        gs.gameIsActive = false;
    }

    public synchronized boolean gameIsActive() {
        return gs.gameIsActive;
    }
    
    public void setFrame(TetrisFrame frame){
        this.frame=frame;
    }

}
