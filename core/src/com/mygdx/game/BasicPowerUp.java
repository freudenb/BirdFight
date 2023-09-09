package com.mygdx.game;

import com.badlogic.gdx.utils.TimeUtils;

abstract public class BasicPowerUp implements IBasicPowerUp{

	
	
	long enableTime;
	long duration;
	Player player;
	protected int positionInPowerUpArray;
	
	public BasicPowerUp(Player p, long duration){
		this.duration =  duration;
		enableTime = 0;
		this.player = p;
		positionInPowerUpArray = 0;
	}
		
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		if(TimeUtils.millis() - enableTime >= duration){
			//remove Powerup
			removePowerUp();
		}
		
	}

	abstract protected void onRemovale();
	abstract protected void onActivation();

	
	public void removePowerUp(){
		onRemovale();
		player.removePowerUp(positionInPowerUpArray);
		
	}
	
	public void setActive(){
		onActivation();
		enableTime = TimeUtils.millis();
	}
}
