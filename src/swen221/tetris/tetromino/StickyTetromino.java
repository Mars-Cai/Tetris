package swen221.tetris.tetromino;

import swen221.tetris.logic.Rectangle;

/**
 * Represents a tetromino which can only perform one rotation operation.
 *
 * @author David J. Pearce
 * @author Marco Servetto
 *
 */
public class StickyTetromino implements Tetromino {
	private final Tetromino tetromino;
	private final int count;

	public StickyTetromino(int count, Tetromino tetromino) {
		this.count = count;
		this.tetromino = tetromino;
		// TODO Auto-generated method stub
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		if (this.count > 3) {
			return Color.DARK_GRAY;
		} else {
			return this.tetromino.getColor();
		}
	}

	@Override
	public Orientation getOrientation() {
		// TODO Auto-generated method stub
		return this.tetromino.getOrientation();
	}

	@Override
	public boolean isWithin(int x, int y) {
		// TODO Auto-generated method stub
		return this.tetromino.isWithin(x, y);
	}

	@Override
	public Rectangle getBoundingBox() {
		// TODO Auto-generated method stub
		return this.tetromino.getBoundingBox();
	}

	@Override
	public Tetromino rotate(int steps) {
		// TODO Auto-generated method stub
		return  this.tetromino.rotate(steps);
	}
}
