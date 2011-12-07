package com.anwpteuz.bomberman;

import java.awt.Color;
import java.awt.Graphics;

/**
 * 
 * @author antonniklasson
 *
 */
public class ExplodableWall extends Wall {
	
	public ExplodableWall(Game g) {
		super(g);
	}
	
	@Override
	public void paint(Graphics g) {
		//super.paint(g);
		
		g.setColor(Color.GRAY);
		
		int padding = 2;
		g.fillRect(
				getTile().getX()*Grid.CELL_SIZE + padding,
				getTile().getY()*Grid.CELL_SIZE + padding,
				Grid.CELL_SIZE - (2 * padding),
				Grid.CELL_SIZE - (2 * padding)
		);
	}
}
