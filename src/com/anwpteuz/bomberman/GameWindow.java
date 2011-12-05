package com.anwpteuz.bomberman;

import javax.swing.JFrame;

public class GameWindow extends JFrame {
	
	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = -2237037338716988955L;
	
	private static final int barHeight = 28;
	
	protected JFrame frame;
	protected Grid grid;
	
	public GameWindow() {
		// Initialize frame
		frame = new JFrame("Bomberman");
		
		// Stop program when closing
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Setting frame size
		frame.setSize(Grid.COLUMNS*Grid.CELL_SIZE, Grid.ROWS*Grid.CELL_SIZE + barHeight); // TODO Change these values
		
		// Centering the frame
		frame.setLocationRelativeTo(null);
		
		// Show the frame
		frame.setVisible(true);
		
		// No resizing
		frame.setResizable(false);
		
		// Init and add grid
		grid = new Grid();
		grid.setVisible(true);
		frame.add(grid);
	}

	public Grid getGrid() {
		return grid;
	}
}
