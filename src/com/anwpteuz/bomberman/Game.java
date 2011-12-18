package com.anwpteuz.bomberman;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

/**
 * Core/root class. Encapsulates all components of the game. 
 * 
 * @author Matteus
 *
 */
public class Game extends Thread {
	
	public static final int targetTime = 16;
	
	private GameWindow window;
	private boolean isRunning;
	
	private ArrayList<Player> players = new ArrayList<Player>();

	
	public Game() {
		window = new GameWindow();
		
		GridObjectFactory.init(this);
		
		generateLevel();
	}
	
	/**
	 * Adds players, walls, explodable walls and other GridObjects to the grid.
	 */
	private void generateLevel() {
		// Add two players
		players.add(GridObjectFactory.addPlayer(1, 1, 1));
		players.add(GridObjectFactory.addPlayer(2, Grid.COLUMNS-2, Grid.ROWS-2));
		
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
		ArrayList<Tile> safeTiles = new ArrayList<Tile>();
		safeTiles.add(getGrid().getTile(1, 1));
		safeTiles.add(getGrid().getTile(2, 1));
		safeTiles.add(getGrid().getTile(1, 2));
		safeTiles.add(getGrid().getTile(Grid.COLUMNS-2, Grid.ROWS-2));
		safeTiles.add(getGrid().getTile(Grid.COLUMNS-2, Grid.ROWS-3));
		safeTiles.add(getGrid().getTile(Grid.COLUMNS-3, Grid.ROWS-2));
		
		Random randomizer = new Random();
		int expWallsLeft = 50;
		
		while(expWallsLeft > 0) {
			Tile tile;
			do {
				tile = getGrid().getTile(randomizer.nextInt(Grid.COLUMNS), randomizer.nextInt(Grid.ROWS));
			} while(tile.has(Wall.class) || tile.has(Player.class) || safeTiles.contains(tile));
			
			
			GridObjectFactory.addExplodableWall(tile.getX(), tile.getY());
			expWallsLeft--;
		}
		
		
		// Find an empty cell and place an enemy
		int x, y;
		do {
			x = randomizer.nextInt(Grid.COLUMNS);
			y = randomizer.nextInt(Grid.ROWS);
		}while(getGrid().getTile(x, y).has(Wall.class) || getGrid().getTile(x, y).has(Player.class));
		
		// Add enemies
		for(int enemyIndex = 0; enemyIndex < 1; enemyIndex++) {
			// Get safe tile.
			Tile tile;
			do {
				tile = getGrid().getTile(randomizer.nextInt(Grid.COLUMNS), randomizer.nextInt(Grid.ROWS));
			} while(tile.has(Wall.class) || tile.has(Player.class) || safeTiles.contains(tile) || !getGrid().tileHasPathToSafeTile(tile, 2));
			
			// Spawn enemy at tile
			GridObjectFactory.addEnemy(tile.getX(), tile.getY());
		}
	}
	
	@Override
	public void run() {
		super.run();
		
		isRunning = true;
		
		Log.get().info("Game running");
		while(isRunning) {
			
			// Get current time
			long startTime = System.currentTimeMillis();
			
			// Call update on every Updateable GridObject in this grid.
			for(Updateable updateable : getGrid().getAllUpdateables()) {
				updateable.update();
			}
			
			// Diff the time with the one before the update
			long endTime = System.currentTimeMillis();
			long deltaTime = endTime - startTime;
			
			// Sleep till to await for targetTime to elapse
			if(deltaTime < targetTime) {
				try {
					Thread.sleep(targetTime-deltaTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public synchronized void start() {
		Log.get().info("Game started");
		super.start();
	}
	
	public GameWindow getWindow() {
		return window;
	}
	
	public Grid getGrid() {
		return window.getGrid();
	}
	
	public Collection<Player> getPlayers() {
		return players;
	}
	
}
