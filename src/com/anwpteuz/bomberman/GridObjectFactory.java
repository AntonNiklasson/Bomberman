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
	
	public static void addPlayer(int posX, int posY) {
		
	}
	
	public static void addEnemy(int posX, int posY) {
		game.getGrid().addGridObject(new Enemy(1, 1));
	}
}
