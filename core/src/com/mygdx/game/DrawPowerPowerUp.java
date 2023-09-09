package com.mygdx.game;

public class DrawPowerPowerUp extends BasicPowerUp{

	public DrawPowerPowerUp(Player p, long duration) {
		super(p, duration);
		this.positionInPowerUpArray = 2;
	}

	@Override
	protected void onRemovale() {
		System.out.println("DrawPowerPowerUp faded");
		
	}

	@Override
	protected void onActivation() {
		
		
	}

	
	
}
