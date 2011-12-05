package com.anwpteuz.bomberman;

import java.awt.Graphics;

/**
 * 
 * @author antonniklasson
 *
 */
public class Player extends MoveableGridObject {

	
	
	public Player() {
		
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
	
	@Override
	public void paint(Graphics g) {
		g.fillRect(getX()*Grid.CELL_SIZE, getY()*Grid.CELL_SIZE, Grid.CELL_SIZE, Grid.CELL_SIZE);
	}
}
