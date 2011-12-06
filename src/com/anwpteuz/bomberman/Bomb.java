package com.anwpteuz.bomberman;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.Timer;

/**
 * 
 * @author antonniklasson
 *
 */
public class Bomb extends MoveableGridObject {	
	
	/**
	 * How far away from a bomb you'll
	 * have to be in order to survive.
	 */
	protected int cellRange = 2;
	protected Timer timer;
	protected int explosionDelay = 3000;
	
	public Bomb(Game g) {
		
		super(g);
		
		timer = new Timer(explosionDelay, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				Log.get().info("BOOOM");
				explode();
			}
		});
		timer.start();
		timer.setRepeats(false);
	}

	@Override
	public void move(int toX, int toY) {
		// TODO Move?
	}
	
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.GREEN);
		
		int padding = 10;
		g.fillRect(
				getTile().getX()*Grid.CELL_SIZE + padding,
				getTile().getY()*Grid.CELL_SIZE + padding,
				Grid.CELL_SIZE - (2 * padding),
				Grid.CELL_SIZE - (2* padding)
		);
	}
	
	public void explode() {
		// TODO BOOOOOOOOOOOM!
		
		Grid grid = getGame().getGrid();
		
		
		/*
		 * Checking for ExplodableWall x-wise
		 */
		
		// Left
		for(int x = 0; x >= -cellRange; x--) {
			Tile tile = grid.getTile(getTile().getX()-x, getTile().getY());
			
			for(GridObject go : tile) {
				if(go instanceof ExplodableWall) {
					tile.remove(go);
					
					// Break here since bombs only can explode one wall at a time
					break;
				}
			}
		}
		// Right
		for(int x = 0; x <= -cellRange; x++) {
			Tile tile = grid.getTile(getTile().getX()+x, getTile().getY());
			
			for(GridObject go : tile) {
				if(go instanceof ExplodableWall) {
					tile.remove(go);
					
					// Break here since bombs only can explode one wall at a time
					break;
				}
			}
		}
		
		
		
		/*
		 * Checking for ExplodableWall y-wise
		 */
		
		// Up
		for(int y = 0; y >= -cellRange; y--) {
			Tile tile = grid.getTile(getTile().getX(), getTile().getY()-y);
			
			for(GridObject go : tile) {
				if(go instanceof ExplodableWall) {
					tile.remove(go);
					
					// Break here since bombs only can explode one wall at a time
					break;
				}
			}
		}
		
		// Down
		for(int y = 0; y <= -cellRange; y++) {
			Tile tile = grid.getTile(getTile().getX(), getTile().getY()+y);
			
			for(GridObject go : tile) {
				if(go instanceof ExplodableWall) {
					tile.remove(go);
					
					// Break here since bombs only can explode one wall at a time
					break;
				}
			}
		}
	}
}
