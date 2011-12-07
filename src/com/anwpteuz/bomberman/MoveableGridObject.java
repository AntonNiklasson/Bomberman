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
	
	public void move(int toX, int toY) {
		if(canMoveTo(toX, toY)) {
			this.getTile().remove(this);
			Tile tile = getGame().getGrid().getTile(toX, toY);
			tile.add(this);
			this.setTile(tile);
		}
	}
	
	public boolean canMoveTo(int toX, int toY) {
		return !getGame().getGrid().getTile(toX, toY).hasWall();
	}
}
