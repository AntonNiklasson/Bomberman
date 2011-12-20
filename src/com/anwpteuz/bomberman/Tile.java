package com.anwpteuz.bomberman;

import java.util.LinkedList;
import java.util.WeakHashMap;

/**
 * Our grid is built of Tiles. Each tile can contain instances of GridObject. Tile extends LinkedList
 * @author antonniklasson
 *
 */
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
	
	public boolean has(Class<? extends GridObject> c) {
		for(GridObject go : this) {
			if(c.isAssignableFrom(go.getClass()))
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
	
	@Override
	public boolean add(GridObject e) {
		
		return super.add(e);
	}
}
