package com.anwpteuz.bomberman;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author antonniklasson
 *
 */
public class Player extends MoveableGridObject implements KeyEventDispatcher, Updateable {

	private static final Color[] colorList = new Color[] { Color.RED, Color.BLUE };
	
	/**
	 * The ID of the player should be different for each player.
	 * This is used by eg. defaultKeyBindings and paint so that it can give different bindings and looks to every player.
	 */
	private int id;
	
	// Bomb variables
	private int bombCapacity = 3;
	private ArrayList<Bomb> activeBombs = new ArrayList<Bomb>();
	
	private HashMap<Integer, String> keyBindings = new HashMap<Integer, String>();
	
	/**
	 * Creates a new instance of the Player.
	 * 
	 * @param id The ID should 
	 * @param g
	 */
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
	
	public boolean placeBomb() {
		// Refresh the bomb list
		refreshActiveBombs();
		
		// Check if we can place a bomb
		if(activeBombs.size() < bombCapacity && !this.getTile().hasBomb()) {
			activeBombs.add(
				GridObjectFactory.addBomb(this.getTile().getX(), this.getTile().getY())
			);
			return true;
		} else {
			return false;
		}
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
	
	/**
	 * Sets the bomb capacity, which is the number of bombs the player carries + the number of active bombs the player have on the grid.
	 * @param bombCapacity The new bomb capacity
	 */
	public void setBombCapacity(int bombCapacity) {
		this.bombCapacity = bombCapacity;
	}
	
	/**
	 * Gets the bomb capacity.
	 * The bomb capacity is the number of bombs the player carries + the number of active bombs the player have on the grid.
	 * @return
	 */
	public int getBombCapacity() {
		return bombCapacity;
	}
	
	/**
	 * Refreshes the {@link Player#activeBombs}. Removes dead bombs.
	 */
	private void refreshActiveBombs() {
		// Remove dead bombs from the active bombs list
		for(int i = 0; i < activeBombs.size(); i++) {
			Bomb bomb = activeBombs.get(i);
			if(bomb.isAlive() == false) {
				Log.get().info("Removing dead bomb.");
				activeBombs.remove(i);
				i--;
			}
		}
	}
}
