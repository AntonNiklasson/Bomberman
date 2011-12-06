package com.anwpteuz.bomberman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

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
	protected int cellRange = 3;
	protected Timer timer;
	protected int explosionDelay = 3000;
	
	public Bomb() {
		timer = new Timer(explosionDelay, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				explode();
			}
		});
		timer.setRepeats(false);
	}

	@Override
	public void move(int toX, int toY) {
		// TODO Move?
	}
	
	public void explode() {
		// TODO BOOOOOOOOOOOM!
		
		Grid grid = getGame().getGrid();
		
		// Checking for ExplodableWall x-wise
			// Left
			for(int x = 0; x >= -cellRange; x--) {
				LinkedList<GridObject> tile = grid.getTile(x, getY());
				
				for(GridObject go : tile) {
					if(go instanceof ExplodableWall) {
						
					}
				}
			}
			// Right
			for(int x = 0; x <= cellRange; x++) {
				
			}
		
		
		
		// Checking for ExplodableWall y-wise
		for(int y = -1*cellRange; y <= cellRange; y++) {
			
		}
	}
}
