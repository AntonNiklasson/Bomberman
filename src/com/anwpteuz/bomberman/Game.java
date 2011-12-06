package com.anwpteuz.bomberman;

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
		player = GridObjectFactory.addPlayer(5, 5);
		
		for(int x = 0; x < Grid.HEIGHT; x++) {
			GridObjectFactory.addWall(x, 0);
			GridObjectFactory.addWall(x, Grid.ROWS);
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
