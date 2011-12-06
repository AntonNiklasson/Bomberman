package com.anwpteuz.bomberman;

import java.awt.Graphics;

/**
 * 
 * @author antonniklasson
 *
 */
public class Player extends MoveableGridObject {

	private int bombCapacity = 3;
	private int bombsActive;
	
	public Player() {
		
	}
	
	public void placeBomb() {
		if(bombCapacity > bombsActive) {
			//GridObjectFactory.addBomb();
		}
	}
	
	@Override
	public void paint(Graphics g) {
		g.fillRect(getX()*Grid.CELL_SIZE, getY()*Grid.CELL_SIZE, Grid.CELL_SIZE, Grid.CELL_SIZE);
	}
}
