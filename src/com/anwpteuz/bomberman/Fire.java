package com.anwpteuz.bomberman;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Fire extends GridObject implements Updateable {
	
	private int range;
	private Direction direction;
	private int millisLifetime = 1000;
	private int millisLived;
	private int millisSpreadTime = 200;
	private boolean isParent;
	
	/**
	 * 
	 * @param g The Game
	 * @param dirX -1 0 1 x-wise
	 * @param dirY -1 0 1 y-wise
	 * @param range The number of Fires left to place in direction given
	 */
	public Fire(Game g, Direction dir, int range) {
		super(g);
		
		this.range  = range;
		this.direction = dir;
	}
	
	private void placeFireChild() {
		Tile nextTile = getGame().getGrid().nextTile(this.getTile(), this.direction);
		GridObjectFactory.addFire(nextTile.getX(), nextTile.getY(), direction, range-1);
	}
	
	@Override
	public void paint(Graphics g) {
		
		if(new Random().nextInt(1) == 0)
			g.setColor(Color.YELLOW);
		else
			g.setColor(Color.RED);	
		
		
		int padding = 3;
		g.fillRect(
				getTile().getX()*Grid.CELL_SIZE + padding,
				getTile().getY()*Grid.CELL_SIZE + padding,
				Grid.CELL_SIZE - (2 * padding),
				Grid.CELL_SIZE - (2 * padding)
		);
	}

	@Override
	public void update() {
		millisLived += Game.targetTime;
		
		/**
		 * Run placeFireChild only if all the following applies:
		 * 1. Range isn't reached
		 * 2. This ain't yet a parent
		 * 3. It has lived long enough to spread
		 */
		if(range > 0 && !isParent && millisLived >= millisSpreadTime) { 
			placeFireChild();
			isParent = true;
		}
		
		if(millisLived >= millisLifetime)
			this.getTile().remove(this);
		
	}
}
