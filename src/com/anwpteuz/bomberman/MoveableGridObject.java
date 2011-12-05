package com.anwpteuz.bomberman;

/**
 * 
 * @author antonniklasson
 *
 */
public abstract class MoveableGridObject extends GridObject {
	
	protected int positionX, positionY;
	
	/**
	 * @param toX Move Object to given x position
	 * @param toY Move Object to given y position
	 */
	public abstract void move(int toX, int toY);

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
	
	public void canMove() {
		// TODO Should return boolean and check in next if moving there is valid
	}
}
