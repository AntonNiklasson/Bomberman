package com.anwpteuz.bomberman;

public class PowerupBombRange extends Powerup {

	public PowerupBombRange(Game g) {
		super(g);
		// TODO Auto-generated constructor stub
	}

	public void applyTo(Player player) {
		player.setBombRange(player.getBombRange());
	}
}
