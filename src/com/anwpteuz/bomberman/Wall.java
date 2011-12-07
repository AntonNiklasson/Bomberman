package com.anwpteuz.bomberman;

import java.awt.Color;
import java.awt.Graphics;

/**
 * 
 * @author antonniklasson
 *
 */
public class Wall extends StaticGridObject {

	public Wall(Game g) { 
		super(g);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		g.setColor(Color.BLACK);
		g.fillRect(getTile().getX()*Grid.CELL_SIZE, getTile().getY()*Grid.CELL_SIZE, Grid.CELL_SIZE, Grid.CELL_SIZE);
	}
}
