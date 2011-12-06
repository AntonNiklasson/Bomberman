package com.anwpteuz.bomberman;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Grid extends JPanel {
	/**
	 * Generated uid
	 */
	private static final long serialVersionUID = 4158705485206730087L;
	
	public static final int COLUMNS = 20;
	public static final int ROWS = 15;
	public static final int CELL_SIZE = 50;
	
	private Tile[][] tileList = new Tile[COLUMNS][ROWS];

	public Grid() {
		// Initialize the tile list
		for(int x = 0; x < COLUMNS; x++) {
			for(int y = 0; y < ROWS; y++) {
				tileList[x][y] = new Tile(x, y);
			}
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		Log.get().info("Testing Grid.paint()");
		
		// Fill with white color
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getSize().width, getSize().height);
		
		for(int x = 0; x < COLUMNS; x++) {
			for(int y = 0; y < ROWS; y++) {
				// Call paint on every gridobject on this tile
				for(GridObject o : tileList[x][y]) {
					o.paint(g);
				}
			}
		}	
	}
	
	public Tile getTile(int x, int y) {
		return tileList[x][y];
	}
	
}
