package com.mygdx.game;

public class ArrowCountPowerUp extends BasicPowerUp {

	
	public int arrows;
	
	public ArrowCountPowerUp(Player p, int duration) {
		super(p, duration);
		arrows = 5;
		positionInPowerUpArray = 1;
	}


	protected void onRemovale() {
		System.out.println("arrow count powerup faded");
	}

	protected void onActivation() {
			
	}
	
	

}
