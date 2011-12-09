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
	
	public void moveToNextTile() {	
		Grid grid = getGame().getGrid();
		ArrayList<Tile> neighbours = grid.getTileNeighbours(getTile());
		Tile nextTile;
		
		for(int i = 0; i < neighbours.size(); i++) {
			Tile neighbour = neighbours.get(i);
			if(neighbour.hasWall()) {
				neighbours.remove(neighbour);
				
				// The order changes when we remove a Tile
				i--;
			}
			
		}
		
		if(neighbours.size() > 1) {
			do {
				nextTile = neighbours.get(new Random().nextInt(neighbours.size()));
			} while(nextTile == prevTile);
		} else {
			nextTile = prevTile;
			
			if(!nextTile.hasBomb())
				this.placeBomb();
		}
		
		if(nextTile != null) {
			moveTo(nextTile);
			Log.get().info(nextTile.getX() + " " + nextTile.getY());
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
