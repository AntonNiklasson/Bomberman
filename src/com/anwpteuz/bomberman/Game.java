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
	
	public Game() {
		window = new GameWindow();
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
}
