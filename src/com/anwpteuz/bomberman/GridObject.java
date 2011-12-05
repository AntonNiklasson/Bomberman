package com.anwpteuz.bomberman;
/**
 * 
 * @author antonniklasson
 *
 */
public class GridObject {
	
	private Game game;
	protected int positionX, positionY;
	
	public void init(Game g) {
		game = g;
	}
	
	public int getX() {
		return this.positionX;
	}
	
	public void setX(int newX) {
		this.positionX = newX;
	}
	
	public int getY() {
		return this.positionY;
	}	
	
	public void setY(int newY) {
		this.positionY = newY;
	}
	
	protected Game getGame() {
		return game;
	}
	
	public GridObject() {
		
	}
}
