package com.anwpteuz.bomberman;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

import javax.swing.JPanel;

public class Grid extends JPanel {
	/**
	 * Generated uid
	 */
	private static final long serialVersionUID = 4158705485206730087L;
	
	public static final int COLUMNS = 20;
	public static final int ROWS = 15;
	public static final int CELL_SIZE = 50;
	
	// Java doesn't allow array combined with generics
	class Tile extends LinkedList<GridObject> {}
	private Tile[][] tileList = new Tile[COLUMNS][ROWS];

	public Grid() {
		// Initialize the tile list
		for(int x = 0; x < COLUMNS; x++) {
			for(int y = 0; y < ROWS; y++) {
				tileList[x][y] = new Tile();
			}
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		Log.get().info("Testing Grid.paint()");
		
		for(int x = 0; x < COLUMNS; x++) {
			for(int y = 0; y < ROWS; y++) {
				
				// Fill tile with white color
				g.setColor(x%2-y%2==0?Color.WHITE:Color.BLACK);
				g.fillRect(x*CELL_SIZE, y*CELL_SIZE, CELL_SIZE, CELL_SIZE);
				
				// Call paint on every gridobject on this tile
				for(GridObject o : tileList[x][y]) {
					o.paint(g);
				}
			}
		}	
	}
	
	/**
	 * Adds a GridObject to specified coordinates.
	 * 
	 * @param o GridObject to move
	 * @param x Start x position
	 * @param y Start y position
	 */
	public void addGridObject(GridObject o) {
		tileList[o.getX()][o.getY()].add(o);
	}
	
	/**
	 * Moves a GridObject to a new coordinates.
	 * 
	 * @param o GridObject to move
	 * @param x X destination
	 * @param y Y destination
	 */
	public void moveGridObject(GridObject o, int x, int y) {
		removeGridObject(o);
		
		addGridObject(o);
	}
	
	public LinkedList<GridObject> getTile(int x, int y) {
		return tileList[x][y];
	}
	
	public void removeGridObject(GridObject o) {
		tileList[o.getX()][o.getY()].remove(o);
	}
}
