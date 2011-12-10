package com.anwpteuz.bomberman;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.HashMap;

/**
 * 
 * @author antonniklasson
 *
 */
public class Player extends MoveableGridObject implements KeyEventDispatcher, Updateable {

	private static final Color[] colorList = new Color[] { Color.RED, Color.BLUE };
	
	private int id;
	private int bombCapacity = 3;
	private HashMap<Integer, String> keyBindings = new HashMap<Integer, String>();
	
	public Player(int id, Game g) {
		super(g);
		this.id = id;
		defaultKeyBindings();
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(this);
	}
	
	public Color getColor() {
		return colorList[id-1];
	}
	
	/**
	 * Maps default key bindings for the player taking player id into account.
	 */
	public void defaultKeyBindings() {
		keyBindings.clear();
		
		// ID specifics:
		switch(id) {
		case 1:
			keyBindings.put(KeyEvent.VK_LEFT, "move_left");
			keyBindings.put(KeyEvent.VK_RIGHT, "move_right");
			keyBindings.put(KeyEvent.VK_UP, "move_up");
			keyBindings.put(KeyEvent.VK_DOWN, "move_down");
			keyBindings.put(KeyEvent.VK_SPACE, "place_bomb");
			break;
		case 2:
			keyBindings.put(KeyEvent.VK_A, "move_left");
			keyBindings.put(KeyEvent.VK_D, "move_right");
			keyBindings.put(KeyEvent.VK_W, "move_up");
			keyBindings.put(KeyEvent.VK_S, "move_down");
			keyBindings.put(KeyEvent.VK_Q, "place_bomb");
			break;
		}
		
		// Others
		keyBindings.put(KeyEvent.VK_ESCAPE, "exit_game");
	}
	
	/**
	 * Executes an action from the specified action.
	 * @param action The action to execute. Eg. "move_left" will move the player one grid to the left.
	 * @return Returns true if a action was found for the specified string.
	 */
	protected boolean executeAction(String action) {
		if(action.equals("move_left")) move(-1, 0);
		else if(action.equals("move_right")) move(1, 0);
		else if(action.equals("move_up")) move(0, -1);
		else if(action.equals("move_down")) move(0, 1);
		else if(action.equals("place_bomb")) placeBomb();
		else if(action.equals("exit_game")) System.exit(0);
		else return false;
		
		return true;
	}
	
	public void placeBomb() {
		GridObjectFactory.addBomb(this.getTile().getX(), this.getTile().getY());
	}
	
	@Override
	public void paint(Graphics g) {
		g.setColor(getColor());
		g.fillOval(getTile().getX()*Grid.CELL_SIZE, getTile().getY()*Grid.CELL_SIZE, Grid.CELL_SIZE, Grid.CELL_SIZE);
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {

		if(e.getID() != KeyEvent.KEY_PRESSED) return false;
		
		if(keyBindings.containsKey(e.getKeyCode())) {
			String action = keyBindings.get(e.getKeyCode());
			boolean success = executeAction(action);
			if(success == false) {
				Log.get().info("Unknown action: " + action);
			}
		}
		
		// Left, Right, Up, Down
		/* OLD CODE
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
		
		*/
		
		return false;
	}

	@Override
	public void update() {
		// Called on every update call from Game
	}
}
