package swen221.tetris.moves;

import swen221.tetris.logic.Board;
import swen221.tetris.logic.Rectangle;
import swen221.tetris.tetromino.ActiveTetromino;
import swen221.tetris.tetromino.Tetromino;

/**
 * Implements a "landing" move. That is, when the tetromino is placed on the
 * board properly.
 *
 * @author David J. Pearce
 * @author Marco Servetto
 *
 */
public class LandingMove implements Move {
	private int lines;

	public LandingMove() {
		this.lines = -1;
	}

	public LandingMove(int lines) {
		this.lines = lines;
	}

	@Override
	public boolean isValid(Board board) {
		boolean valid = false;
		int FullRows = 0;
		boolean full = true;
		ActiveTetromino t = board.getTetromino();
		// first, get the value of full rows
		for (int Y = 0; Y < board.getHeight(); Y++) {
			for (int X = 0; X < board.getWidth(); X++) {
				// check every row and make full be true when it is full
				if (board.getTetrominoAt(X, Y) == null) {
					full = false;
				}
			}
			if (full) {
				FullRows++;
			}
		}
		// then check if it is in the bottom row
		for (int w = 0; w < board.getWidth(); w++) {
			if (t.isWithin(w, 0)) {
				if (this.lines == FullRows || this.lines == -1) {
					valid = true;
				} else {
					valid = false;
				}
			}
		}
		// if not, check if there is a placed tetromino under it
		for (int y = 1; y < board.getHeight(); y++) {
			for (int x = 0; x < board.getWidth(); x++) {
				if (t.isWithin(x, y) && board.getPlacedTetrominoAt(x, y - 1) != null) {
					valid = true;
					// still, check the lines
					if (this.lines == FullRows || this.lines == -1) {
						valid = true;
						return valid;
					} else {
						valid = false;
						return valid;
					}

				}
			}
		}
		return valid;
	}

	@Override
	public Board apply(Board board) {
		// Create copy of the board to prevent modifying its previous state.
		board = new Board(board);
		// Place tetromino on board
		board.put(board.getTetromino());
		// Reset active tetromino
		board.updateTetromino(null);
		// Remove any full rows
		removeFullRows(board);
		// DOne!
		return board;
	}

	/**
	 * Remove any rows on the board which are now full.
	 *
	 * @param board
	 * @return
	 */
	private void removeFullRows(Board board) {
		boolean full = true; // To check if it is full
			for (int y = 0; y < board.getHeight(); y++) {
				for (int x = 0; x < board.getWidth(); x++) { // check every row and make full be true when it is full
					if (board.getTetrominoAt(x, y) == null) {
						full = false;
					}
				}
				if (full) {

					  for (int row = y; row < board.getHeight() - 1; row++){// shift down others of the board
					    for (int cell = 0; cell < board.getWidth(); cell++) {
		                    board.setPlacedTetromino(cell, row, board.getTetrominoAt(cell, row + 1));
		                }
		              }
					y--;//incase there are more than one line full
				}
				full = true;
			}



	}

	@Override
	public String toString() {
		return "landing " + lines;
	}
}
