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
	
	public static Player addPlayer(int id, int posX, int posY) {
		Player player = new Player(id, game);
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
	
	public static ExplodableWall addExplodableWall(int posX, int posY) {
		ExplodableWall expWall = new ExplodableWall(game);
		Tile tile = game.getGrid().getTile(posX, posY);
		tile.add(expWall);
		expWall.setTile(tile);
		
		return expWall;		
	}
	
	public static Enemy addEnemy(int posX, int posY) {
		Enemy enemy = new Enemy(game);
		Tile tile = game.getGrid().getTile(posX, posY);
		tile.add(enemy);
		enemy.setTile(tile);
		
		return enemy;
	}
	
	public static Fire addFire(int posX, int posY, Direction dir, int range) {
		Fire fire = new Fire(game, dir, range);
		Tile tile = game.getGrid().getTile(posX, posY);
		
		if(tile.hasWall()) {
			if(tile.hasExplodableWall()) {
				for(GridObject go : tile) {
					if(go instanceof ExplodableWall) go.remove();
				}
			}
			return null;
		}
		
		tile.add(fire);
		fire.setTile(tile);
		
		return fire;
	}
}
