package com.anwpteuz.bomberman;

/**
 * 
 * @author antonniklasson
 *
 */
public class Player extends MoveableGridObject {

	private int bombCapacity = 3;
	private int bombsActive;
	
	public Player(int posX, int posY) {
		this.positionX = posX;
		this.positionY = posY;
	}
	
	public void placeBomb() {
		if(bombCapacity > bombsActive) {
			GridObjectFactory.addBomb(getX(), getY());
		}
	}
	
	@Override
	public void move(int toX, int toY) {
		// TODO
	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return 0;
	}
}
