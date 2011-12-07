package com.anwpteuz.bomberman;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class Enemy extends MoveableGridObject {

	Tile prevTile;
	
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
	
	
	public void moveToNextTile() {	
		Grid grid = getGame().getGrid();
		ArrayList<Tile> neighbours = grid.getTileNeighbours(getTile());
		Tile nextTile;
		
		for(Tile neighbour : neighbours) {
			if(neighbour.hasWall()) neighbours.remove(neighbour); 
		}
		
		if(neighbours.size() > 1) {
			do {
				nextTile = neighbours.get(new Random().nextInt(neighbours.size()));
			} while(nextTile == prevTile);
		} else {
			nextTile = prevTile;
		}
		
		moveTo(nextTile);
	}
	
	@Override
	public void setTile(Tile tile) {
		prevTile = getTile();
		super.setTile(tile);
	}
}
