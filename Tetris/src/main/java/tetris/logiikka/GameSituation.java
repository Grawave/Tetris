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

    private Field field;
    private Piece activePiece;
    private final int BLOCK_COUNT = 4;

    public GameSituation(Field field) {
        this.field = field;
    }

    /*
    Moves the piece to the given direction if the required spots are vacant in the field.
    If there is no vacancy and the direction is down, the piece will be frozen to the field.
    If there is no vacancy and the direction is not down, the piece will not move.
    
    Returns true,true if the piece was moved and game continues, or piece wasn't allowed to move and game continues.
    Returns false, true if piece was frozen and game continues  --- requires new piece
    Returns false false if piece wasn't moved and game doesn't continue --- requires game to stop
     */
    public MoveResult movePiece(Direction dir) {
        /* moveResult is initialised with values: moved=false, frozen=false, gameWon =false, gameLost=false*/
        MoveResult moveResult = new MoveResult();
        /* Checking if the piece can be moved to the direction */
        List<Block> blocks = activePiece.getBlocks();
        for (int i = 0; i < BLOCK_COUNT; i++) {
            Block b = blocks.get(i);
            if (!field.spotIsVacant(b.getX() + dir.x, b.getY() + dir.y)) {
                if (dir == Direction.DOWN) {
                    return field.freezePiece(activePiece);
                }
                return moveResult; //wasn't moved or frozen.
            }
        }
        /* Checking done, the piece can move */
        activePiece.move(dir);
        moveResult.pieceWasMoved = true;
        return moveResult;
    }

    public void setActivePiece(Piece p) {
        this.activePiece = p;
    }

    public boolean rotatePiece(Rotation rot) {
        this.activePiece.rotate(rot);
        return true;
    }

    public void createActivePiece() {
        Piece randomPiece = new Piece(field.getWidth() / 2, 0, Formation.getRandom());
        setActivePiece(randomPiece);
    }

    public boolean fieldIsEmpty() {
        return this.field.isEmpty();
    }

    public Block[][] getFieldAndPieceBlocks() {
        Block[][] blocks = field.getFrozenBlocks().clone();
        List<Block> pieceBlocks = activePiece.getBlocks();
        for (int i = 0; i < BLOCK_COUNT; i++) {
            Block b = pieceBlocks.get(i);
            blocks[b.getY()][b.getX()] = b;
        }
        return blocks;
    }
}
