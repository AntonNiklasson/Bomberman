package com.anwpteuz.bomberman;

import java.awt.Color;
import java.awt.Graphics;

/**
 * 
 * @author antonniklasson
 *
 */
public class GridObject {
	
	private Game game;
	protected Tile tile;

	
	public GridObject(Game g) {
		
		init(g);
	}
	
	public void init(Game g) {
		game = g;
	}

	public Tile getTile() {
		return tile;
	}
	
	protected Game getGame() {
		return game;
	}
	
	public void paint(Graphics g) {
		
	}
}
