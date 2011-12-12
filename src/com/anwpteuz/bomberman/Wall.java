package com.anwpteuz.bomberman;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

/**
 * 
 * @author antonniklasson
 *
 */
public class Wall extends StaticGridObject {

	private Image image;
	
	public Wall(Game g) { 
		super(g);
		image = AssetsManager.getInstance().loadImage("wall");
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		g.setColor(Color.BLACK);
		//g.fillRect(getTile().getX()*Grid.CELL_SIZE, getTile().getY()*Grid.CELL_SIZE, Grid.CELL_SIZE, Grid.CELL_SIZE);
		g.drawImage(image, getTile().getX()*Grid.CELL_SIZE, getTile().getY()*Grid.CELL_SIZE, Grid.CELL_SIZE, Grid.CELL_SIZE, null);
	}
}
