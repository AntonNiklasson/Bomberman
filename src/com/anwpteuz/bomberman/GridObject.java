package com.anwpteuz.bomberman;

import java.awt.Graphics;

/**
 * 
 * @author antonniklasson
 *
 */
public abstract class GridObject {
	
	private Game game;
	private Tile tile;

	
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
