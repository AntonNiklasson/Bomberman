package com.anwpteuz.bomberman;

import java.awt.Color;
import java.awt.Graphics;

/**
 * The Powerup class should be the super class for every different type of powerup on the 
 * @author Matteus
 *
 */
public abstract class Powerup extends StaticGridObject {
	
	public Powerup(Game g) {
		super(g);
	}
	
	/**
	 * Applies this power up to the player
	 * 
	 * @param player The player to apply the powerup effect to.
	 */
	public abstract void applyTo(Player player);
	
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.ORANGE);
		
		int padding = 5;
		g.fillOval(
				getTile().getX()*Grid.CELL_SIZE + padding,
				getTile().getY()*Grid.CELL_SIZE + padding,
				Grid.CELL_SIZE + 2*padding,
				Grid.CELL_SIZE + 2*padding
		);
	}
}
