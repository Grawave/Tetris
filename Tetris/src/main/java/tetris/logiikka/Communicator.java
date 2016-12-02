/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.logiikka;

import java.awt.Color;
import tetris.domain.GameSituation;
import tetris.gui.TetrisFrame;

/**
 * Implementations of Communicator are capable of communication between gui and
 * GameSituation.
 *
 * @author isjani
 */
public interface Communicator {
    public void newGame();
    /**
     * gives access to the engine that begins a game
     */
    public void setEngine(Engine engine);
    public void setDistractionBoard();
    /**
     * Retrieves highest score from text file, and gives it to the frame
     */
    public void setHighScore();
    /**
     * 
     * @return the current score
     */
    public int getScore();
    /**
     * Attempts to move the piece to given direction.
     *
     * @param direction to move
     */
    public void movePiece(Direction direction);

    /**
     * Attempts to rotate the piece according to given rotation.
     *
     * @param rotation direction of rotation
     */
    public void rotatePiece(Rotation rotation);

    /**
     * @return true if game is active
     */
    public boolean gameIsActive();

    /**
     * Sets the frame to which we want to communicate.
     *
     * @param frame to communicate with
     */
    public void setFrame(TetrisFrame frame);

    /**
     * Sets the GameSituation with which the communication is to be done.
     *
     * @param gameSituation to communicate with
     */
    public void setGameSituation(GameSituation gameSituation);

    /**
     * Sets the PieceDropper with which the communication is to be done.
     *
     * @param gameSituation to communicate with
     */
    public void setPieceDropper(PieceDropper pieceDropper);

    /**
     * Tells the frame to repaint itself.
     */
    public void rePaintSituation(Color[][] colorTable);

    /**
     * Tells the PieceDropper to stop when it is running, or continue if it's
     * stopped
     */
    public void pauseGame();
}
