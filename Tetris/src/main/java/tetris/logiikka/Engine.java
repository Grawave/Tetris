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
            boolean[] situ = gs.movePiece(Direction.DOWN);
            boolean pieceFrozen = !situ[0];
            if (pieceFrozen) {
                boolean gameLost = !situ[1];
                boolean gameWon = gs.fieldIsEmpty();
                if (gameLost) {
                    defeat();
                    break;
                } else if (gameWon) {
                    victory();
                } else {
                    gs.setActivePiece(createActivePiece());
                }
            }
        }
    }

    public void defeat() {

    }

    public void victory() {

    }

    public void initialize() {
        gs = new GameSituation(width, height);
        gs.setActivePiece(createActivePiece());
    }

    public Piece createActivePiece() {
        List<Formation> forms = new ArrayList<>();
        Collections.addAll(forms, Formation.values());
        Collections.shuffle(forms);
        Piece randomPiece = new Piece(width / 2, 0, forms.get(0));
        return randomPiece;
    }

}
