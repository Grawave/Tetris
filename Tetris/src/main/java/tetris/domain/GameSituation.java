/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.domain;

import java.util.List;
import tetris.logiikka.Direction;
import tetris.logiikka.Rotation;
import static tetris.logiikka.Rotation.reverseRotation;

/**
 * GameSituation knows the active piece and the field that it's in.
 *
 * @author isjani
 */
public class GameSituation {

    private Field field;
    private Piece activePiece;
    /**
     * True indicates that game is active and has not ended.
     */
    public boolean gameIsActive;
    private final int BLOCK_COUNT = 4;

    public GameSituation(Field field) {
        this.field = field;
        gameIsActive = true;
    }

    /**
     * Attempts to move the piece to the given direction. Piece can be moved, it
     * can stay still or be frozen. Being frozen can result in the end of the
     * game.
     *
     * @param direction of movement
     * @return A MoveResult object that indicates the outcome.
     * @see tetris.domain.MoveResult
     */
    public MoveResult movePiece(Direction direction) {
        /* moveResult is initialised with values: moved=false, frozen=false, gameWon =false, gameLost=false*/
        MoveResult moveResult = new MoveResult();
        /* Checking if the piece can be moved to the direction */
        List<Block> blocks = activePiece.getBlocks();
        for (int i = 0; i < BLOCK_COUNT; i++) {
            Block b = blocks.get(i);
            if (!field.spotIsVacant(b.getX() + direction.x, b.getY() + direction.y)) {
                if (direction == Direction.DOWN) {
                    return field.freezePiece(activePiece);
                }
                return moveResult; //wasn't moved or frozen.
            }
        }
        /* Checking done, the piece can move */
        activePiece.move(direction);
        moveResult.pieceWasMoved = true;
        return moveResult;
    }

    /**
     * Attempts to rotate the active piece according to given rotation.
     *
     * @param rotation direction of rotation.
     * @return True if the active piece was rotated. False if piece wasn't
     * rotated.
     */
    public boolean rotatePiece(Rotation rotation) {
        this.activePiece.rotate(rotation);
        List<Block> blocks = activePiece.getBlocks();
        for (int i = 0; i < BLOCK_COUNT; i++) {
            Block b = blocks.get(i);
            if (!field.spotIsVacant(b.getX(), b.getY())) {
                activePiece.rotate(reverseRotation(rotation));
                return false;
            }
        }
        return true;
    }

    /**
     * Sets the active piece to a new piece of a random formation. Location of
     * the new piece is in the middle of x-axis, on top of y-axis.
     */
    public void createActivePiece() {
        Piece randomPiece = new Piece(field.getWidth() / 2, 0, Formation.getRandom());
        this.activePiece = randomPiece;
    }

    /**
     * @return True if the field is empty.
     */
    public boolean fieldIsEmpty() {
        return this.field.isEmpty();
    }

    /**
     * @return A two-dimensional array that contains all the blocks in the game.
     * Active and frozen.
     */
    public Block[][] getFieldAndPieceBlocks() {
        Block[][] blocks = copyBlocks(field.getFrozenBlocks(), field.getWidth(), field.getHeight());
        List<Block> pieceBlocks = activePiece.getBlocks();
        for (int i = 0; i < BLOCK_COUNT; i++) {
            Block b = pieceBlocks.get(i);
            if (b.getY() < 0) {
                continue;
            }
            blocks[b.getY()][b.getX()] = b;
        }
        return blocks;
    }
    
    private Block[][] copyBlocks(Block[][] original, int width, int height) {
        Block[][] copy = new Block[height][width];
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                copy[row][column]= original[row][column];
            }
        }
        return copy;
    }
}
