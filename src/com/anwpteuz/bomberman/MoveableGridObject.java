package com.anwpteuz.bomberman;

/**
 * 
 * @author antonniklasson
 *
 */
public abstract class MoveableGridObject extends GridObject {
	
	/**
	 * @param toX Move Object to given x position
	 * @param toY Move Object to given y position
	 */
	public void move(int toX, int toY) {
		
	}
	
	public void canMove() {
		// TODO Should return boolean and check in next if moving there is valid
	}
}
