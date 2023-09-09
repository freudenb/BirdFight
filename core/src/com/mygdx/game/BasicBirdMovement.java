package com.mygdx.game;


public class BasicBirdMovement implements IMovement{

	
	
	protected Bird b;
	protected float angle;
	protected float radialPower;
	protected float angleSpeed;
	
	public BasicBirdMovement(Bird bird){
		
		b = bird;
		angle = 0;
		radialPower = 100f;
		angleSpeed = 5f;
	}
	
	public BasicBirdMovement(Bird bird, float radialPowa, float angSpeed){
		
		b = bird;
		angle = 0;
		radialPower = radialPowa;
		angleSpeed = angSpeed;
	}

	public void move(float deltaTime) {

		b.getVelocity().y = (float) Math.sin(angle) * radialPower;
		angle += angleSpeed * deltaTime;

		b.getPosition().x += b.getVelocity().x * deltaTime;
		b.getPosition().y += b.getVelocity().y * deltaTime;
		
	}
	
	
}
