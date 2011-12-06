package com.anwpteuz.bomberman;

import java.awt.Color;
import java.awt.Graphics;

/**
 * 
 * @author antonniklasson
 *
 */
public class Player extends MoveableGridObject {

	private int bombCapacity = 3;
	private int bombsActive;
	
	public Player(Game g) {
		super(g);
	}
	
	public void placeBomb() {
		if(bombCapacity > bombsActive) {
			//GridObjectFactory.addBomb();
		}
	}
	
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval(getTile().getX()*Grid.CELL_SIZE, getTile().getY()*Grid.CELL_SIZE, Grid.CELL_SIZE, Grid.CELL_SIZE);
	}
}
