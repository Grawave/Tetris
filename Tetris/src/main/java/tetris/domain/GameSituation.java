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

    /**
     * The field in which all the pieces will be frozen.
     */
    private Field field;
    /**
     * The piece that is currently active and dropping towards the bottom.
     */
    private Piece activePiece;
    /**
     * The current score.
     */
    private int score;
    /**
     * True indicates that game is active and has not ended.
     */
    public boolean gameIsActive;
    /**
     * Number of blocks per piece.
     */
    private final int BLOCK_COUNT = 4;

    /**
     * Creates a new instance of game situation.
     *
     * @param field related to the game situation.
     */
    public GameSituation(Field field) {
        this.field = field;
        gameIsActive = true;
        score = 0;
    }

    /**
     * Attempts to move the piece to the given direction. Piece can be moved, it
     * can stay still or be frozen. Being frozen can result in increase of score
     * and the end of the game.
     *
     * @param direction of movement.
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
            if (!field.spotIsVacant(b.getX() + direction.moveX, b.getY() + direction.moveY)) {
                if (direction == Direction.DOWN) {
                    moveResult = field.freezePiece(activePiece);
                    score += moveResult.pointsGained;
                    return moveResult;
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
     * Creates a new active piece of a given form.
     *
     * @param form shape of the new active piece.
     */
    public void createActivePiece(Formation form) {
        this.activePiece = new Piece(field.getWidth() / 2, 0, form);
    }

    /**
     * Checks whether or not the field is empty.
     *
     * @return True if the field is empty.
     */
    public boolean fieldIsEmpty() {
        return this.field.isEmpty();
    }

    /**
     * Asks the field for it's array of blocks and adds active pieces blocks to
     * the array. Returns this array.
     *
     * @return A two-dimensional array of blocks.
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

    /**
     * Copies the content of the first block matrix to a new matrix.
     *
     * @param original matrix to be copied
     * @param width of matrix
     * @param height of matrix
     * @return copy of original matrix.
     */
    public Block[][] copyBlocks(Block[][] original, int width, int height) {
        Block[][] copy = new Block[height][width];
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                copy[row][column] = original[row][column];
            }
        }
        return copy;
    }

    public int getScore() {
        return this.score;
    }

    /**
     * Resets the score to zero. Resets the field and creates a new active
     * piece.
     */
    public void reset() {
        this.score = 0;
        field.reset();
        createActivePiece();
        this.gameIsActive = true;
    }
}
