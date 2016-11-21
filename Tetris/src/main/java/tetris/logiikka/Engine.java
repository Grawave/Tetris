/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.logiikka;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author isjani
 */
public class Engine {

    private GameSituation gs;
    private final int width = 10;
    private final int height = 10;

    public Engine() {

    }

    public void start() {
        initialize();
        while (true) {
            MoveResult moveResult = gs.movePiece(Direction.DOWN);
            if(moveResult.pieceWasMoved) {
                continue;
            } else if (moveResult.gameWon) {
                victory();
            } else if (moveResult.gameLost) {
                defeat();
            } else if (moveResult.pieceWasFrozen) {
                gs.setActivePiece(createActivePiece());
            }
        }
    }

    public void defeat() {

    }

    public void victory() {

    }

    public void initialize() {
        gs = new GameSituation(new Field(width,height));
        gs.setActivePiece(createActivePiece());
    }

    public Piece createActivePiece() {
        Piece randomPiece = new Piece(width / 2, 0, Formation.getRandom());
        return randomPiece;
    }

}
