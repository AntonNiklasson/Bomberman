package com.anwpteuz.bomberman;

public class GridObjectFactory {
	
	private static Game game;
	
	public static void init(Game g) {
		game = g;
	}
	
	public static Bomb addBomb(int posX, int posY) {
		Bomb bomb = new Bomb(game);
		game.getGrid().getTile(posX, posY).add(bomb);
		
		return bomb;
	}
	
	public static Player addPlayer(int posX, int posY) {
		Player player = new Player(game);
		game.getGrid().getTile(posX, posY).add(player);
		return player;
	}
	
	public static Wall addWall(int posX, int posY) {
		Wall wall = new Wall(game);
		game.getGrid().getTile(posX, posY).add(wall);
		return wall;
	}
	
	public static void addEnemy(int posX, int posY) {
		Enemy enemy = new Enemy(game);
		game.getGrid().getTile(posX, posY).add(enemy);
	}
}
