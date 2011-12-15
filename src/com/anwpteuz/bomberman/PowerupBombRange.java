package com.anwpteuz.bomberman;

import java.awt.Color;
import java.awt.Graphics;


/**
 * This powerup will increase the players bombRange by 1
 * for each powerup taken.
 * 
 * @author antonniklasson
 *
 */
public class PowerupBombRange extends Powerup {

	public PowerupBombRange(Game g) {
		super(g);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Run this method to acctually apply the powerup on givn Player
	 */
	public void applyTo(Player player) {
		player.setBombRange(player.getBombRange() + 1);
	}
	
	/**
	 * Paint the powerup. This should be somewhat unique for each powerup.
	 */
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.RED);
		
		int padding = 13;
		g.fillOval(
				this.getTile().getX()*Grid.CELL_SIZE + padding,
				this.getTile().getY()*Grid.CELL_SIZE + padding,
				Grid.CELL_SIZE - (2 * padding),
				Grid.CELL_SIZE - (2 * padding)
		);
	}
}