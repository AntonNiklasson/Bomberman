package com.anwpteuz.bomberman;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Enemy extends MoveableGridObject implements Updateable {

	Tile prevTile;
	int timer;
	
	// Image list
	HashMap<Direction, Image> images = new HashMap<Direction, Image>();
	
	private Direction lastMoveDirection = Direction.DOWN;
	
	public Enemy(Game g) {
		super(g);
		
		// Add images
		images.put(Direction.RIGHT, AssetsManager.getInstance().loadImage("enemy_right"));
		images.put(Direction.LEFT, AssetsManager.getInstance().loadImage("enemy_left"));
		images.put(Direction.UP, AssetsManager.getInstance().loadImage("enemy_up"));
		images.put(Direction.DOWN, AssetsManager.getInstance().loadImage("enemy_down"));
	}
	
	public Image getCurrentImage() {
		return images.get(lastMoveDirection);
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(getCurrentImage(), getTile().getX()*Grid.CELL_SIZE, getTile().getY()*Grid.CELL_SIZE, Grid.CELL_SIZE, Grid.CELL_SIZE, null);
	}
	
	public void placeBomb() {
		GridObjectFactory.addBomb(this.getTile().getX(), this.getTile().getY());
	}
	
	/**
	 * Tries to move the enemy to a neighbor tile.
	 * This method also drops a bomb if the enemy finds itself to be in a corner.
	 */
	public void moveToNextTile() {	
		Grid grid = getGame().getGrid();
		ArrayList<Tile> neighbours = grid.getTileNeighbours(getTile());
		Tile nextTile;
		
		for(int i = 0; i < neighbours.size(); i++) {
			Tile neighbour = neighbours.get(i);
			if(neighbour.has(Wall.class)) {
				neighbours.remove(neighbour);
				
				// The order changes when we remove a Tile
				i--;
			}
			
		}
		
		// If we are not in a corner, move to a random neighbor.
		if(neighbours.size() > 1) {
			do {
				nextTile = neighbours.get(new Random().nextInt(neighbours.size()));
			} while(nextTile == prevTile);
		} else {
			
			// We are in a corner. Move to the only neighbor we got.
			nextTile = neighbours.get(0);
			
			if(!this.getTile().has(Bomb.class))
				this.placeBomb();
		}
		
		if(nextTile != null) {
			moveTo(nextTile);
		}
	}
	
	@Override
	public void setTile(Tile tile) {
		prevTile = getTile();
		super.setTile(tile);
	}

	@Override
	public void update() {
		timer += Game.targetTime;
		
		if(timer >= 500) {
			timer = 0;
			moveToNextTile();
		}
	}
	
	/**
	 * Override to get lastest move direction.
	 */
	@Override
	public void move(Direction dir) {
		lastMoveDirection = dir;
		super.move(dir);
	}
}
