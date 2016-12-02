/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.logiikka;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
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
    private PieceDropper pieceDropper;
    private boolean paused;
    private final String HIGH_SCORE_FILEPATH = "highscore.txt";
    private final String QUOTES_FILEPATH = "quotes.txt";
    private int highScore;
    // "/home/jani/Tetris/Tetris/freeBackground.jpg"

    public CommunicationPlatform() {
        paused = false;
    }

    /**
     * {@inheritDoc}
     */
    public synchronized void movePiece(Direction direction) {
        if (paused) {
            return;
        }
        MoveResult moveResult = gs.movePiece(direction);
        actBasedOnResult(moveResult);
    }

    /**
     * {@inheritDoc}
     */
    public void setHighScore() {
        highScore = 0;
        Scanner reader;
        try {
            reader = new Scanner(new File(HIGH_SCORE_FILEPATH));
        } catch (Exception e) {
            throw new IllegalArgumentException("no such filepath for High scores");
        }
        if (reader.hasNext()) {
            try {
                highScore = Integer.parseInt(reader.next());
            } catch (Exception e) {
                throw new IllegalArgumentException("highscore unreadable");
            }
        }
        frame.setHighScore(highScore);
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
        int score = gs.getScore();
        boolean newHighScore = score > highScore;
        gs.gameIsActive = false;
        if (newHighScore) {
            try {
                PrintWriter writer = new PrintWriter("highscore.txt", "UTF-8");
                writer.println(score);
                writer.close();
            } catch (IOException e) {
                throw new IllegalStateException("unable to write highscore.txt");
            }
        }
        frame.close();
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

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void pauseGame() {
        paused = !paused;

    }

    @Override
    public int getScore() {
        return gs.getScore();
    }

    @Override
    public void setDistractionBoard() {
        Scanner reader;
        ArrayList<String> quoteList = new ArrayList<>();
        try {
            reader = new Scanner(new File(QUOTES_FILEPATH));
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
        frame.setDistractionBoard(quotes);
    }

}
