package com.anwpteuz.bomberman;

import java.awt.Graphics;

/**
 * GridObjects are generally visual objects in the game grid.
 * @author antonniklasson
 *
 */
public abstract class GridObject {
	
	private Game game;
	private Tile tile;
	private boolean alive;
	
	public GridObject(Game g) {
		init(g);
	}
	
	public void init(Game g) {
		game = g;
	}

	public Tile getTile() {
		return tile;
	}
	
	public void setTile(Tile tile) {
		this.tile = tile;
		alive = tile != null;
	}
	
	protected Game getGame() {
		return game;
	}
	
	public void paint(Graphics g) {
		
	}
	
	public void remove() {
		this.getTile().remove(this);
		this.setTile(null);
	}
	
	public boolean isAlive() {
		return alive;
	}
}
