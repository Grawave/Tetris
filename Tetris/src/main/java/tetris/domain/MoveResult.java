/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.domain;

/**
 * Objects of this class contain the results of movePiece method call.
 *
 * @author isjani
 */
public class MoveResult {

    /**
     * false on creation.
     */
    public boolean gameLost;
    /**
     * false on creation.
     */
    public boolean gameWon;
    /**
     * false on creation.
     */
    public boolean pieceWasFrozen;
    /**
     * false on creation.
     */
    public boolean pieceWasMoved;

    /**
     * 0 on creation.
     */
    public int pointsGained;

    /**
     * Creates a moveResult object with all conditions equalling false.
     */
    public MoveResult() {
        this.gameLost = false;
        this.gameWon = false;
        this.pieceWasFrozen = false;
        this.pieceWasMoved = false;
        pointsGained = 0;
    }
}
