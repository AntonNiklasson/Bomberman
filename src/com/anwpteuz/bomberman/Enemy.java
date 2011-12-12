package com.anwpteuz.bomberman;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class Enemy extends MoveableGridObject implements Updateable {

	Tile prevTile;
	int timer;
	
	public Enemy(Game g) {
		super(g);
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.YELLOW);
		
		int padding = 3;
		g.fillRect(
				getTile().getX()*Grid.CELL_SIZE + padding,
				getTile().getY()*Grid.CELL_SIZE + padding,
			 	Grid.CELL_SIZE - 2*padding,
				Grid.CELL_SIZE - 2*padding
		);
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
}
