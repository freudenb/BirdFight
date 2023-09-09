package com.mygdx.game;

import com.badlogic.gdx.Gdx;

public class FunctionBirdMovement implements IMovement{

	
	protected Bird b;
	
	public FunctionBirdMovement(Bird b){
		this.b = b;
		
	}
	
	@Override
	public void move(float deltaTime) {
		// TODO Auto-generated method stub
		
		
		
		b.getPosition().x += b.getVelocity().x * deltaTime;
		
		float x = b.getPosition().x;
		x-= Gdx.graphics.getWidth()/2;
		
		b.getPosition().y =  0.0007f * (x * x);
		
		//System.out.println("[FunctionBirdMovement] y: "+b.getPosition().y+ " | x: "+b.getPosition().x);
		
	}

	
	
	
}
