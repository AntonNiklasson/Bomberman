package com.anwpteuz.bomberman;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Grid extends JPanel {
	/**
	 * Generated uid
	 */
	private static final long serialVersionUID = 4158705485206730087L;
	
	public static final int COLUMNS = 20;
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
		return tileList[x][y];
	}
	
}
