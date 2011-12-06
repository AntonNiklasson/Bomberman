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
}
