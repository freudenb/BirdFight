package com.mygdx.game;

public class PowerUpMovement implements IMovement{

	
	
	private GameObject powerUp;
	
	PowerUpMovement(GameObject pu){
		
		powerUp = pu;
	}
	
	public void move(float deltaTime) {
		//stop falling
		if(powerUp.getPosition().y <= 150){
			powerUp.getVelocity().y = 0;
		}
		else{

			powerUp.getPosition().x += powerUp.getVelocity().x * deltaTime;
			powerUp.getPosition().y += powerUp.getVelocity().y * deltaTime;
		}
		
		
		
	}

}
