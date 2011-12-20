package com.anwpteuz.bomberman;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Players are controlled by actual users.
 * Player one: Arrows + Space for bomb
 * Player two: WASD + Q for bomb
 * 
 * @author antonniklasson
 *
 */
public class Player extends MoveableGridObject implements KeyEventDispatcher, Updateable {

	private static final String[] colorList = new String[] { "blue", "red" };
	
	/**
	 * The ID of the player should be different for each player.
	 * This is used by eg. defaultKeyBindings and paint so that it can give different bindings and looks to every player.
	 */
	private int id;
	
	// Image list
	HashMap<Direction, Image> images = new HashMap<Direction, Image>();
	
	// Bomb variables
	private int bombCapacity = 3;
	private int bombRange = 2;
	private ArrayList<Bomb> activeBombs = new ArrayList<Bomb>();
	
	private HashMap<Integer, String> keyBindings = new HashMap<Integer, String>();
	
	private Direction lastMoveDirection = Direction.DOWN;
	
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
		
		// Add images
		images.put(Direction.RIGHT, AssetsManager.getInstance().loadImage("player_" + getStringColor() + "_right"));
		images.put(Direction.LEFT, AssetsManager.getInstance().loadImage("player_" + getStringColor() + "_left"));
		images.put(Direction.UP, AssetsManager.getInstance().loadImage("player_" + getStringColor() + "_up"));
		images.put(Direction.DOWN, AssetsManager.getInstance().loadImage("player_" + getStringColor() + "_down"));
	}
	
	public Image getCurrentImage() {
		return images.get(lastMoveDirection);
	}
	
	public String getStringColor() {
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
		if(action.equals("move_left")) move(Direction.LEFT);
		else if(action.equals("move_right")) move(Direction.RIGHT);
		else if(action.equals("move_up")) move(Direction.UP);
		else if(action.equals("move_down")) move(Direction.DOWN);
		else if(action.equals("place_bomb")) placeBomb();
		else if(action.equals("exit_game")) System.exit(0);
		else return false;
		
		return true;
	}
	
	public boolean placeBomb() {
		// Refresh the bomb list
		refreshActiveBombs();
		
		// Check if we can place a bomb
		if(activeBombs.size() < bombCapacity && !this.getTile().has(Bomb.class)) {
			
			Bomb bomb = GridObjectFactory.addBomb(this.getTile().getX(), this.getTile().getY());
			bomb.setCellRange(bombRange);
			activeBombs.add(bomb);
			
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(getCurrentImage(), getTile().getX()*Grid.CELL_SIZE, getTile().getY()*Grid.CELL_SIZE, Grid.CELL_SIZE, Grid.CELL_SIZE, null);
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {

		if(!this.isAlive() || e.getID() != KeyEvent.KEY_PRESSED) return false;
		
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
	
	public void setBombRange(int newRange) {
		this.bombRange = newRange;
	}
	public int getBombRange() {
		return this.bombRange;
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
	
	/**
	 * Override MoveAbleGridObject.moveTo for implementing the following:
	 * - Checks for Fire collisions and dies in case of fire in next tile.
	 * - Checks for Powerups, and applies them accordingly.
	 */
	@Override
	public void moveTo(int toX, int toY) {				
		super.moveTo(toX, toY);
		
		Tile nextTile = getGame().getGrid().getTile(toX, toY);
		
		// Die if tile has fire
		if(nextTile.has(Fire.class)) {
			this.remove();
		}
		
		// Apply powerups if there's any
		if(nextTile.has(Powerup.class)) {
			for(GridObject go : nextTile) {
				if(go instanceof Powerup) {
					((Powerup) go).applyTo(this);
					Log.get().info("Powerup applied to " + this.id);
					go.remove();
				}
			}
		}
	}
	
	/**
	 * Override to get latest move direction.
	 */
	@Override
	public void move(Direction dir) {
		lastMoveDirection = dir;
		super.move(dir);
	}
}
