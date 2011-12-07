package com.anwpteuz.bomberman;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * 
 * @author antonniklasson
 *
 */
public class Player extends MoveableGridObject implements KeyEventDispatcher {

	private int bombCapacity = 3;
	
	public Player(Game g) {
		super(g);
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(this);
	}
	
	public void placeBomb() {
		GridObjectFactory.addBomb(this.getTile().getX(), this.getTile().getY());
	}
	
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval(getTile().getX()*Grid.CELL_SIZE, getTile().getY()*Grid.CELL_SIZE, Grid.CELL_SIZE, Grid.CELL_SIZE);
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {

		if(e.getID() != KeyEvent.KEY_PRESSED) return false;
		
		// Left, Right, Up, Down
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			this.move(getTile().getX()-1, getTile().getY());
		} else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			this.move(getTile().getX()+1, getTile().getY());
		} else if(e.getKeyCode() == KeyEvent.VK_UP) {
			this.move(getTile().getX(), getTile().getY()-1);
		} else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			this.move(getTile().getX(), getTile().getY()+1);
		}
		
		// Place bomb
		else if(e.getKeyCode() == KeyEvent.VK_SPACE)
			this.placeBomb();
		
		
		return false;
	}
}
