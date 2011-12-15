package com.anwpteuz.bomberman;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;


/**
 * This powerup will increase the players bombCapacity by 1
 * for each powerup taken.
 * 
 * @author antonniklasson
 *
 */
public class PowerupBombCapacity extends Powerup {

	private Image image;
	
	
	public PowerupBombCapacity(Game g) {
		super(g);
		image = AssetsManager.getInstance().loadImage("powerup_bombcapacity");
	}

	/**
	 * Run this method to acctually apply the powerup on given Player
	 */
	public void applyTo(Player player) {
		player.setBombCapacity(player.getBombCapacity() + 1);
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