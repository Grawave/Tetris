/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.communication;

import java.awt.Color;
import tetris.domain.Block;
import tetris.domain.MoveResult;
import tetris.domain.GameSituation;
import tetris.gui.EndFrame;
import tetris.gui.TetrisFrame;
import tetris.logiikka.Direction;
import tetris.logiikka.DistractionQuotes;
import tetris.timer.PieceDropper;
import tetris.logiikka.Rotation;
import tetris.logiikka.ScoreRecorder;

/**
 * This class receives messages from threads and acts upon GameSituation (based
 * on messages). After every communication, asks to draw the gui again.
 *
 * @author isjani
 */
public class CommunicationPlatform implements Communicator {

    /**
     * GameSituation that receives orders from pieceDropper and keyboard.
     */
    private GameSituation gs;
    /**
     * Frame for the gui. All the communication to gui goes by the Frame.
     */
    private TetrisFrame frame;
    /**
     * The timer-thread that tells this communicator to move pieces. This class
     * has to know the pieceDropper. When a game ends and a new one is started,
     * the pieceDropper needs to be interrupted.
     */
    private PieceDropper pieceDropper;
    /**
     * Indicates whether the game is paused or not.
     */
    private boolean paused;
    private ScoreRecorder scoreRecorder;

    /**
     * Creates a new communication platform.
     */
    public CommunicationPlatform() {
        paused = false;
    }

    /**
     * {@inheritDoc}
     */
    public synchronized void movePiece(Direction direction) {
        if (paused || !gs.gameIsActive) {
            return;
        }
        MoveResult moveResult = gs.movePiece(direction);
        actBasedOnResult(moveResult);
    }

    /**
     * {@inheritDoc}
     */
    public void setHighScore() {
        scoreRecorder = new ScoreRecorder();
        frame.setHighScore(scoreRecorder.getHighScore());
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
    public void setPieceDropper(PieceDropper pieceDropper) {
        this.pieceDropper = pieceDropper;
    }

    /**
     * {@inheritDoc}
     */
    public synchronized void rotatePiece(Rotation rotation) {
        if (paused || !gs.gameIsActive) {
            return;
        }
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
            frame.updateBoards();
            rePaintSituation(blockTableToColorTable(gs.getFieldAndPieceBlocks()));
        }
    }

    private void defeat() {
        gs.gameIsActive = false;
        scoreRecording();
        EndFrame endFrame = new EndFrame(false, this);
        endFrame.run();
    }

    private void victory() {
        gs.gameIsActive = false;
        scoreRecorder.update(gs.getScore() + 1000);
        scoreRecording();
        EndFrame endFrame = new EndFrame(true, this);
        endFrame.run();
    }

    private void scoreRecording() {
        int score = gs.getScore();
        scoreRecorder.update(score);
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

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void pauseGame() {
        paused = !paused;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getScore() {
        return gs.getScore();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDistractionBoard() {
        DistractionQuotes dQ = new DistractionQuotes();
        frame.setDistractionBoard(dQ);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void newGame() {
        pieceDropper.interrupt();
        gs.reset();
        pieceDropper = new PieceDropper(this);
        pieceDropper.start();
        frame.updateBoards();
        frame.updateHighScore(this.scoreRecorder.getHighScore());
    }
}
