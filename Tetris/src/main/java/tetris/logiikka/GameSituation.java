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
public class GameSituation {

    private int width;
    private int height;

    private Field field;
    private Piece activePiece;

    public GameSituation(int width, int height) {
        this.width = width;
        this.height = height;
        field = new Field(width, height);
    }

    /*
    Moves the piece to the given direction if the required spots are vacant in the field.
    If there is no vacancy and the direction is down, the piece will be frozen to the field.
    If there is no vacancy and the direction is not down, the piece will not move.
    
    Returns true,true if the piece was moved and game continues, or piece wasn't allowed to move and game continues.
    Returns false, true if piece was frozen and game continues  --- requires new piece
    Returns false false if piece wasn't moved and game doesn't continue --- requires game to stop
     */
    public boolean[] movePiece(Direction dir) {
        /* Checking if the piece can be moved to the direction */
        List<Block> blocks = activePiece.getBlocks();
        for (int i = 0; i < 4; i++) {
            Block b = blocks.get(i);
            if (!field.spotIsVacant(b.getX() + dir.x, b.getY() + dir.y)) {
                if (dir == Direction.DOWN) {
                    if (!field.freezePiece(activePiece)) {
                        return new boolean[]{false, false};
                    } else {
                        return new boolean[]{false, true};
                    }
                }
                return new boolean[]{true, true};
            }
        }
        /* Checking done, the piece can move */
        activePiece.move(dir);
        return new boolean[]{true, true};
    }

    public void setActivePiece(Piece p) {
        this.activePiece = p;
    }
//
//    public void createActivePiece() {
//        List<Formation> forms = new ArrayList<>();
//        Collections.addAll(forms, Formation.values());
//        Collections.shuffle(forms);
//        Piece randomPiece= new Piece();
//        this.activePiece =
//    }

    public boolean rotatePiece(Rotation rot) {
        this.activePiece.rotate(rot);
        return true;
    }

    public boolean fieldIsEmpty() {
        return this.field.isEmpty();
    }
}
