package com.anwpteuz.bomberman;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

/**
 * Bombs are added to Tiles when Players, and Enemies place them there.
 * Standard time until explosion is 3 sec.
 * @author antonniklasson
 *
 */
public class Bomb extends MoveableGridObject implements Updateable {	
	
	/**
	 * How far away from a bomb you'll
	 * have to be in order to survive.
	 */
	protected int cellRange = 2;
	protected int timer;
	protected int explosionDelay = 3000;
	protected boolean exploded = false;
	
	private ArrayList<Image> images = new ArrayList<Image>();
	private Image currentImage;
	
	public Bomb(Game g) {
		
		super(g);
		
		// Add images
		images.add(AssetsManager.getInstance().loadImage("bomb_1"));
		images.add(AssetsManager.getInstance().loadImage("bomb_2"));
		images.add(AssetsManager.getInstance().loadImage("bomb_3"));
		images.add(AssetsManager.getInstance().loadImage("bomb_4"));
		
		// Set first image
		currentImage = images.get(0);
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(currentImage, getTile().getX()*Grid.CELL_SIZE, getTile().getY()*Grid.CELL_SIZE, Grid.CELL_SIZE, Grid.CELL_SIZE, null);
	}
	
	public void explode() {
		// BOOOOOOOOOOOM!
		getTile().remove(this);
		
		
		// X
		GridObjectFactory.addFire(this.getTile().getX(), this.getTile().getY(), Direction.LEFT, cellRange);
		GridObjectFactory.addFire(this.getTile().getX(), this.getTile().getY(), Direction.RIGHT, cellRange);
		GridObjectFactory.addFire(this.getTile().getX(), this.getTile().getY(), Direction.UP, cellRange);
		GridObjectFactory.addFire(this.getTile().getX(), this.getTile().getY(), Direction.DOWN, cellRange);

		this.remove();
	}
	
	public void setCellRange(int newRange) {
		cellRange = newRange;
	}

	/**
	 * Handles when to explode and also what image to paint.
	 */
	@Override
	public void update() {
		timer += Game.targetTime;
		
		if(timer >= explosionDelay && exploded == false) {
			explode();
			exploded = true;
		} else {
			int imageIndex = (int)((timer / (float)explosionDelay) * images.size());
			currentImage = images.get(imageIndex);
		}
	}
}
