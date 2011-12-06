package com.anwpteuz.bomberman;

public class GridObjectFactory {
	
	private static Game game;
	
	public static void init(Game g) {
		game = g;
	}
	
	public static Bomb addBomb(int posX, int posY) {
		Bomb bomb = new Bomb(game);
		Tile tile = game.getGrid().getTile(posX, posY);
		tile.add(bomb);
		bomb.setTile(tile);
		
		return bomb;
	}
	
	public static Player addPlayer(int posX, int posY) {
		Player player = new Player(game);
		Tile tile = game.getGrid().getTile(posX, posY);
		tile.add(player);
		player.setTile(tile);
		
		return player;
	}
	
	public static Wall addWall(int posX, int posY) {
		Wall wall = new Wall(game);
		Tile tile = game.getGrid().getTile(posX, posY);
		tile.add(wall);
		wall.setTile(tile);
		
		return wall;
	}
	
	public static Enemy addEnemy(int posX, int posY) {
		Enemy enemy = new Enemy(game);
		Tile tile = game.getGrid().getTile(posX, posY);
		tile.add(enemy);
		enemy.setTile(tile);
		
		return enemy;
	}
}
