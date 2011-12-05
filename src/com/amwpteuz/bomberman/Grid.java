package com.amwpteuz.bomberman;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Grid extends JPanel {
	public static final int COLUMNS = 20;
	public static final int ROWS = 15;
	public static final int CELL_SIZE = 64;

	public Grid() {
		
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		for(int x = 0; x < COLUMNS; x++) {
			for(int y = 0; y < ROWS; y++) {
				g.setColor(x%2-y%2==0?Color.BLACK:Color.WHITE);
				g.fillRect(x*CELL_SIZE, y*CELL_SIZE, CELL_SIZE, CELL_SIZE);
			}
		}
		
	}
	
}
