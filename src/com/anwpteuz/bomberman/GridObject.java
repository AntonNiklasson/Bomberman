package com.anwpteuz.bomberman;
/**
 * 
 * @author antonniklasson
 *
 */
public class GridObject {
	
	protected int positionX, positionY;
	private static Game game;
	
	public void init(Game g) {
		game = g;
	}
	
	protected Game getGame() {
		return game;
	}
	
	public GridObject() {
		
	}
}
