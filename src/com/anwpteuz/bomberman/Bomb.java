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
				explode();
			}
		});
		timer.start();
		timer.setRepeats(false);
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
		// BOOOOOOOOOOOM!
		getTile().remove(this);
		
		
		// X
		
			GridObjectFactory.addFire(this.getTile().getX(), this.getTile().getY(), Direction.LEFT, cellRange);
		
		GridObjectFactory.addFire(this.getTile().getX(), this.getTile().getY(), Direction.RIGHT, cellRange);
		
		GridObjectFactory.addFire(this.getTile().getX(), this.getTile().getY(), Direction.UP, cellRange);
		
		GridObjectFactory.addFire(this.getTile().getX(), this.getTile().getY(), Direction.DOWN, cellRange);
	}
}
