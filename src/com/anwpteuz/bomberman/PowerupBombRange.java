package com.anwpteuz.bomberman;

import java.awt.Color;
import java.awt.Graphics;

public class PowerupBombRange extends Powerup {

	public PowerupBombRange(Game g) {
		super(g);
		// TODO Auto-generated constructor stub
	}

	public void applyTo(Player player) {
		player.setBombRange(player.getBombRange());
	}
	
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.RED);
		
		int padding = 13;
		g.fillOval(
				this.getTile().getX()*Grid.CELL_SIZE + padding,
				this.getTile().getY()*Grid.CELL_SIZE + padding,
				Grid.CELL_SIZE - (2 * padding),
				Grid.CELL_SIZE - (2 * padding)
		);
	}
}