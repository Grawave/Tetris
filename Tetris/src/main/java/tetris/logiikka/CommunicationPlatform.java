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
 * This class receives messages from threads and acts upon GameSituation (based
 * on messages). After every communication, asks to draw the gui again.
 *
 * @author isjani
 */
public class CommunicationPlatform implements Communicator {

    private GameSituation gs;
    private TetrisFrame frame;

    public CommunicationPlatform() {
    }

    /**
     * {@inheritDoc}
     */
    public synchronized void movePiece(Direction direction) {
        MoveResult moveResult = gs.movePiece(direction);
        actBasedOnResult(moveResult);
    }

    /**
     * {@inheritDoc}
     */
    public void setGameSituation(GameSituation gs) {
        this.gs = gs;
    }

    /**
     * {@inheritDoc}
     */
    public synchronized void rotatePiece(Rotation rotation) {
        gs.rotatePiece(rotation);
        Color[][] colorTable = blockTableToColorTable(gs.getFieldAndPieceBlocks());
        rePaintSituation(colorTable);
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
            rePaintSituation(blockTableToColorTable(gs.getFieldAndPieceBlocks()));
        } else if (moveResult.gameWon) {
            victory();
        } else if (moveResult.gameLost) {
            defeat();
        } else if (moveResult.pieceWasFrozen) {
            gs.createActivePiece();
            rePaintSituation(blockTableToColorTable(gs.getFieldAndPieceBlocks()));
        }
    }

    private void defeat() {
        gs.gameIsActive = false;
    }

    private void victory() {
        gs.gameIsActive = false;
    }

    /**
     * {@inheritDoc}
     */
    public synchronized boolean gameIsActive() {
        return gs.gameIsActive;
    }

    /**
     * {@inheritDoc}
     */
    public void setFrame(TetrisFrame frame) {
        this.frame = frame;
    }
    /**
     * {@inheritDoc}
     */
    public void rePaintSituation(Color[][] colorTable) {
        frame.rePaintSituation(colorTable);
    }

}
