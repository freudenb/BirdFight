package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class CircleBirdMovement extends BasicBirdMovement{

	protected Vector2 mPoint;
	protected float radius;
	
	public CircleBirdMovement(Bird bird, Vector2 mP) {
		super(bird);
		mPoint = mP;
		radius = 200;
	
	}
	
	
	
	public CircleBirdMovement(Bird bird, float radialPowa, float angSpeed, Vector2 mP){
		super(bird,radialPowa,angSpeed);
		mPoint = mP;
		radius = 150;
	}

	
	
	public void move(float deltaTime){
		
		mPoint.x += b.getVelocity().x * deltaTime;
		mPoint.y += b.getVelocity().y * deltaTime;
		
		b.getPosition().x = mPoint.x + (float) Math.sin(angle) * radius;
	    b.getPosition().y = mPoint.y + (float) Math.cos(angle) * radius;
		
		this.angle += this.angleSpeed * deltaTime;
		
		
	}
}
