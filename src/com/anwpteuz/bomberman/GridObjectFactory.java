package com.anwpteuz.bomberman;


/**
 * Add GridObjects to tiles
 * @author antonniklasson
 *
 */
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
		
		if(tile.has(Wall.class)) {
			if(tile.has(ExplodableWall.class)) {
				for(int i = 0; i < tile.size(); i++) {
					if(tile.get(i) instanceof ExplodableWall) {
						tile.get(i).remove();
						i--;
					}
				}
			}
			return null;
		}
		
		tile.add(fire);
		fire.setTile(tile);
		
		if(tile.has(Player.class)) {
			for(int i = 0; i < tile.size(); i++) {
				if(tile.get(i) instanceof Player) {
					tile.get(i).remove();
					i--;
				}
			}
		}
		
		
		return fire;
	}
}
