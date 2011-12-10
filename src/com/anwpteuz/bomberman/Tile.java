package com.anwpteuz.bomberman;

import java.util.LinkedList;

public class Tile extends LinkedList<GridObject> {
	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = 8447668080756774200L;
	private int positionX, positionY;

	public Tile(int posX, int posY) {
		this.positionX = posX;
		this.positionY = posY;
	}

	public int getX() {
		return positionX;
	}

	public int getY() {
		return positionY;
	}
	
	public boolean hasWall() {
		for(GridObject go : this) {
			if(go instanceof Wall)
				return true;
		}
		
		return false;
	}
	
	public boolean hasPlayer() {
		for(GridObject go : this) {
			if(go instanceof Player)
				return true;
		}
		
		return false;
	}
	
	/**
	 * Compares the specified object with this tile for equality.
	 * Returns true if the tiles position matches and the GridObject collections are equal.
	 * 
	 * @see LinkedList#equals(Object)
	 */
	@Override
	public boolean equals(Object o) {
		if(super.equals(o)) {
			Tile tile = (Tile)o;
			return tile.getX() == getX() && tile.getY() == getY();
		}
		return false;
	}
}
