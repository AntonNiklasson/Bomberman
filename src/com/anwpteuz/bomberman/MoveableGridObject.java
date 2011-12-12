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
	
	public void moveTo(int toX, int toY) {
		if(canMoveTo(toX, toY)) {
			this.getTile().remove(this);
			Tile tile = getGame().getGrid().getTile(toX, toY);
			tile.add(this);
			this.setTile(tile);
		}
	}
	
	public void moveTo(Tile tile) {
		moveTo(tile.getX(), tile.getY());
	}
	
	public void move(int x, int y) {
		this.moveTo(getTile().getX()+x, getTile().getY()+y);
	}
	
	public boolean canMoveTo(int toX, int toY) {
		return !getGame().getGrid().getTile(toX, toY).has(Wall.class);
	}
}
