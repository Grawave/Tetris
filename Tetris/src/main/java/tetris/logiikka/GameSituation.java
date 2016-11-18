/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.logiikka;

import java.util.List;

/**
 *
 * @author isjani
 */
public class GameSituation {

    private final int width = 10;
    private final int height = 20;

    private Field field;
    private Piece activePiece;

    public GameSituation() {
        field = new Field(width, height);
    }

    /*
    Moves the piece to the given direction if the required spots are vacant in the field.
    If there is no vacancy and the direction is down, the piece will be frozen to the field.
    If there is no vacancy and the direction is not down, the piece will not move.
    
    Returns true if the piece was frozen.
    */
    public boolean movePiece(Direction dir) {
        /* Checking if the piece can be moved to the direction */
        List<Block> blocks = activePiece.getBlocks();
        for (int i = 0; i < 4; i++) {
            Block b = blocks.get(i);
            if (!field.spotIsVacant(b.getX() + dir.x, b.getY() + dir.x)) {
                if(dir==Direction.DOWN){
                    field.freezePiece(activePiece);
                    return true;
                }
                return false;
            }
        }
        /* Checking done, the piece can move */
        activePiece.move(dir);
        return false;
    }
    
    
    public boolean rotatePiece(Rotation rot){
        
        
        return false;
    }
}
