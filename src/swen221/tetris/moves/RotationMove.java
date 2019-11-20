package swen221.tetris.moves;

import swen221.tetris.logic.Board;
import swen221.tetris.tetromino.ActiveTetromino;
import swen221.tetris.tetromino.Tetromino;

/**
 * Implements a rotation move which is either clockwise or anti-clockwise.
 *
 * @author David J. Pearce
 * @author Marco Servetto
 *
 */
public class RotationMove implements Move {
	/**
	 * Rotate a given number of steps in a clockwise direction (if positive), or an
	 * anti-clockwise direction (if negative).
	 */
	private final int rotation;

	public RotationMove(int steps) {
		this.rotation = steps;
	}

	@Override
	public boolean isValid(Board board) {
		boolean valid = true;
		//create a new tetromino t to try to rotate
		ActiveTetromino t = board.getTetromino().rotate(rotation);
		//first check if the tetromino is inside the board
		if (t.getBoundingBox().getMaxX() >= board.getWidth() || t.getBoundingBox().getMinX() < 0
				|| t.getBoundingBox().getMinY() < 0) {
			valid = false;
			return valid;
		} else {
			for (int y = 0; y < board.getHeight(); y++) {
				for (int x = 0; x < board.getWidth(); x++) {
					if (t.isWithin(x, y) && board.getPlacedTetrominoAt(x, y) != null) {
						valid = false;
						return valid;
					} else {
						valid=true;
					}
				}
			}
		}
		return valid;
	}

	/**
	 * Get the number of rotation steps in this move.
	 *
	 * @return
	 */
	public int getRotation() {
		return rotation;
	}

	@Override
	public Board apply(Board board) {
		// Create copy of the board to prevent modifying its previous state.
		board = new Board(board);
		// Create a copy of this board which will be updated.
		ActiveTetromino tetromino = board.getTetromino().rotate(rotation);
		// Apply the move to the new board, rather than to this board.
		board.updateTetromino(tetromino);
		// Return updated version of this board.
		return board;
	}

	@Override
	public String toString() {
		return "rotate " + rotation;
	}

}
