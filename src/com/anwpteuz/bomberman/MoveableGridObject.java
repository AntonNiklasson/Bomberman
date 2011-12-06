package com.anwpteuz.bomberman;

/**
 * 
 * @author antonniklasson
 *
 */
public abstract class MoveableGridObject extends GridObject {
	
	public MoveableGridObject(Game g) {
		super(g);
	}
	
	/**
	 * @param toX Move Object to given x position
	 * @param toY Move Object to given y position
	 */
	public void move(int toX, int toY) {
		this.getTile().remove(this);
		Tile tile = getGame().getGrid().getTile(toX, toY);
		tile.add(this);
		this.setTile(tile);
	}
	
	public void canMove() {
		// TODO Should return boolean and check in next if moving there is valid
	}
}
