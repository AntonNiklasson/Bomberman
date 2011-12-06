package com.anwpteuz.bomberman;

import java.util.Random;

/**
 * Core/root class. Encapsulates all components of the game. 
 * 
 * @author Matteus
 *
 */
public class Game extends Thread {
	
	private GameWindow window;
	private boolean isRunning;
	
	private Player player;
	
	public Game() {
		window = new GameWindow();
		
		GridObjectFactory.init(this);
		player = GridObjectFactory.addPlayer(1, 1);
		
		// Add top and bottom walls
		for(int x = 0; x < Grid.COLUMNS; x++) {
			GridObjectFactory.addWall(x, 0);
			GridObjectFactory.addWall(x, Grid.ROWS-1);
		}
		
		// Add left and right walls
		for(int y = 1; y < Grid.ROWS; y++) {
			GridObjectFactory.addWall(0, y);
			GridObjectFactory.addWall(Grid.COLUMNS-1, y);
		}
		
		// Add grid pattern of walls
		for(int x = 2; x < (Grid.COLUMNS - 2); x += 2) {
			for(int y = 2; y < (Grid.ROWS - 2); y += 2) {
				GridObjectFactory.addWall(x, y);
			}
		}
		
		
		/*
		 * Adding some randomized ExplodableWall objects
		 */
		boolean[][] addTo = new boolean[Grid.COLUMNS][Grid.ROWS];
		Random randomizer = new Random();
		
		for(int i = 0; i < 45; i++) {
			int x = randomizer.nextInt(Grid.COLUMNS);
			int y = randomizer.nextInt(Grid.ROWS);
			
			addTo[x][y] = true;
		}
		
		for(int x = 0; x < Grid.COLUMNS; x++) {
			for(int y = 0; y < Grid.ROWS; y++) {
				Tile tile = getGrid().getTile(x, y);
				
				if(addTo[x][y] && !tile.hasWall() && !tile.hasPlayer()) {
					GridObjectFactory.addExplodableWall(x, y);
				}
			}
		}
	}
	
	@Override
	public void run() {
		super.run();
		
		Log.get().info("Game running");
		while(isRunning) {
			
		}
		
	}
	
	@Override
	public synchronized void start() {
		super.start();
		Log.get().info("Game started");
	}
	
	public GameWindow getWindow() {
		return window;
	}
	
	public Grid getGrid() {
		return window.getGrid();
	}
	
	public Player getPlayer() {
		return player;
	}
	
	
}
