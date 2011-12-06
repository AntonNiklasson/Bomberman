package com.anwpteuz.bomberman;

import java.awt.Color;
import java.awt.Graphics;

/**
 * 
 * @author antonniklasson
 *
 */
public class Wall extends StaticGridObject {
	
	public Wall() {}
	public Wall(int x, int y) { 
		this.positionX = x;
		this.positionY = y;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		g.setColor(Color.BLACK);
		g.fillRect(getX()*Grid.CELL_SIZE, getY()*Grid.CELL_SIZE, Grid.CELL_SIZE, Grid.CELL_SIZE);
	}
}
