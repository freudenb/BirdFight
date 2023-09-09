package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class TargetProjectileMovement implements IMovement{

	private GameObject target;
	private Vector2 directionVector;
	private GameObject projectile;
	
	public TargetProjectileMovement(GameObject projectile, GameObject target){
		this.projectile = projectile;
		this.target = target;
		directionVector = new Vector2();

	}
	
	public void move(float deltaTime) {

		directionVector.x  = target.getPosition().x - projectile.getPosition().x;
		directionVector.y  = target.getPosition().y - projectile.getPosition().y;
		
		directionVector.nor();
		directionVector.scl(Settings.targetProjectileSpeed);
		
		projectile.getVelocity().x += directionVector.x * deltaTime;
		projectile.getVelocity().y += directionVector.y * deltaTime;
		
		projectile.getPosition().x += projectile.getVelocity().x * deltaTime;
		projectile.getPosition().y += projectile.getVelocity().y * deltaTime;
		
	}

}
