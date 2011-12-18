package com.anwpteuz.bomberman;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;


/**
 * This powerup will increase the players bombRange by 1
 * for each powerup taken.
 * 
 * @author antonniklasson
 *
 */
public class PowerupBombRange extends Powerup {

	private Image image;
	
	
	public PowerupBombRange(Game g) {
		super(g);
		image = AssetsManager.getInstance().loadImage("powerup_bombrange");
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
		
		g.drawImage(image, getTile().getX()*Grid.CELL_SIZE, getTile().getY()*Grid.CELL_SIZE, Grid.CELL_SIZE, Grid.CELL_SIZE, null);
	}
}