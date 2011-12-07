package com.anwpteuz.bomberman;

import java.awt.Color;
import java.awt.Graphics;


/**
 * 
 * @author antonniklasson
 *
 */
public class Powerup extends StaticGridObject {

	public Powerup(Game g) { 
		super(g);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		//g.drawImage(img, 10, 10, this);
	}
}
