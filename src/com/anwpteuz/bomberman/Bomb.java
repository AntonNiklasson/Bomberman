package com.anwpteuz.bomberman;

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
		
		for(int x = -1*cellRange; x <= cellRange; x++) {
			for(int y = -1*cellRange; y <= cellRange; y++) {
				
			}
		}
	}
}
