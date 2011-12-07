package com.anwpteuz.bomberman;

import java.awt.Color;
import java.awt.Graphics;

public class Enemy extends MoveableGridObject {

	public Enemy(Game g) {
		super(g);
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.YELLOW);
		
		int padding = 3;
		g.fillRect(
				getTile().getX()*Grid.CELL_SIZE + padding,
				getTile().getY()*Grid.CELL_SIZE + padding,
			 	Grid.CELL_SIZE - 2*padding,
				Grid.CELL_SIZE - 2*padding
		);
	}
}
