package com.mygdx.game;



public class SpeedPowerUp extends BasicPowerUp{

	
	private int increaseSpeedBy;
	
	public SpeedPowerUp(Player p, int duration) {
		super(p, duration);
		this.increaseSpeedBy = 200;
		positionInPowerUpArray = 0;
	}


	protected void onActivation() {
		
		player.setMaxSpeed(player.getMaxSpeed() + increaseSpeedBy);
	}
	
	protected void onRemovale() {
	    System.out.println("speed powerup effect faded");
		player.setMaxSpeed(player.getMaxSpeed() - increaseSpeedBy);
	}


	
}
