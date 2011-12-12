package com.anwpteuz.bomberman;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Grid extends JPanel {
	/**
	 * Generated uid
	 */
	private static final long serialVersionUID = 4158705485206730087L;
	
	/*
	 * Columns and rows must be odd in order to let the
	 * grid pattern of walls to line up nicely.
	 */
	public static final int COLUMNS = 19;
	public static final int ROWS = 15;
	public static final int CELL_SIZE = 50;
	
	private Tile[][] tileList = new Tile[COLUMNS][ROWS];
	private Timer timer;
	
	public Grid() {
		// Initialize the tile list
		for(int x = 0; x < COLUMNS; x++) {
			for(int y = 0; y < ROWS; y++) {
				tileList[x][y] = new Tile(x, y);
			}
		}
		
		timer = new Timer(16, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				repaint();
				invalidate();
			}
		});
		timer.start();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
	
		
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
		if(x < 0 || x > COLUMNS-1 || y < 0 || y > ROWS-1) return null;
		
		return tileList[x][y];
	}
	
	public ArrayList<Updateable> getAllUpdateables() {
		ArrayList<Updateable> updateables = new ArrayList<Updateable>();
		for(int x = 0; x < COLUMNS; x++) {
			for(int y = 0; y < ROWS; y++) {
				for(GridObject o : tileList[x][y]) {
					if(o instanceof Updateable) updateables.add(((Updateable)o));
				}
			}
		}
		
		return updateables;
	}
	
	public ArrayList<Tile> getTileNeighbours(Tile tile) {
		ArrayList<Tile> neighbours = new ArrayList<Tile>();
		int fromX = tile.getX();
		int fromY = tile.getY();
		
		neighbours.add(getTile(fromX-1, fromY)); // Left
		neighbours.add(getTile(fromX+1, fromY)); // Right
		neighbours.add(getTile(fromX, fromY-1)); // Up
		neighbours.add(getTile(fromX, fromY+1)); // Down
		
		
		return neighbours;
	}
	
	public Tile nextTile(Tile startTile, Direction dir) {
		int x = startTile.getX() + dir.getX();
		int y = startTile.getY() + dir.getY();
		
		return this.getTile(x, y);
	}

	
	/**
	 * This methods tests if a bomb would be dropped at a tile, would the tile be connected to any other tile that is bomb-safe.
	 * @param tile The tile to test.
	 * @param bombRange The range of the bomb.
	 * @return Returns true if a bomb-safe tile is connected the specified tile.
	 */
	public boolean tileHasPathToSafeTile(Tile tile, int bombRange) {
		for(Direction dir : Direction.directions)
		{
			Tile currentTile = nextTile(tile, dir);
			if(currentTile != null && !currentTile.has(Wall.class)) {
				if(recursiveSafeTileCheck(currentTile, dir, bombRange)) return true;
			}
		}
		
		
		return false;
	}
	
	/**
	 * Recursively checks if the tile with the specified direction has a bomb-safe tile.
	 * This is a helper method used by {@link Grid#tileHasPathToSafeTile(Tile, int)}.
	 * @param tile The first tile to check.
	 * @param dir Direction to test. All directions will be tested except the inverse the specified direction. 
	 * @param range Bomb range.
	 * @return
	 */
	private boolean recursiveSafeTileCheck(Tile tile, Direction dir, int range) {
		Direction inv = dir.getInverse();
		for(Direction testDir : Direction.directions) {
			if(testDir == inv) continue;
			Tile nextTile = nextTile(tile, testDir);
			if(nextTile.has(Wall.class) == false) {
				if(testDir != dir || range <= 1) return true;
				if(recursiveSafeTileCheck(nextTile, testDir, range-1)) return true;
			}
		}
		
		return false;
	}
}
