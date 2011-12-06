package com.anwpteuz.bomberman;

public class GridObjectFactory {
	
	private static Game game;
	
	public static void init(Game g) {
		game = g;
	}
	
	public static Bomb addBomb(int posX, int posY) {
		Bomb bomb = new Bomb();
		game.getGrid().addGridObject(bomb, posX, posY);
		
		return bomb;
	}
	
	public static Player addPlayer(int posX, int posY) {
		Player player = new Player(posX, posY);
		game.getGrid().addGridObject(player, posX, posY);
		return player;
	}
	
	public static Wall addWall(int posX, int posY) {
		Wall wall = new Wall(posX, posY);
		game.getGrid().addGridObject(wall, posX, posY);
		return wall;
	}
}
