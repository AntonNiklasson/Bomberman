package com.anwpteuz.bomberman;

import java.awt.Color;
import java.awt.Graphics;

/**
 * The Powerup class should be the super class for every different type of powerup in the game 
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
}
