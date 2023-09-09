package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

public class Bird extends GameObject{

	
	private float FireRate;
	private long lastFireTime;
	private long elapsedTime;
	private GameObjTypes prjType;
	
	
	public Bird(Vector2 pos, Vector2 vel, Sprite sprite) {
		super(GameObjTypes.TARGETBIRD, pos,vel,sprite);
		lastFireTime = TimeUtils.millis();
		elapsedTime = 0;	
		FireRate = 0.4f;
		prjType = GameObjTypes.BIRDPROJECTILE;
	
	}


	public void update(float deltaTime){
		
		super.update(deltaTime);
	
		checkForFire();
		//change direction
		float mx = Gdx.graphics.getWidth() * 0.5f;
		if(Math.abs(position.x - mx) >= mx){
			velocity.x *= -1;
		}
		
		
	
	}

	public float getFireRate() {
		return FireRate;
	}

	public void setFireRate(float fireRate) {
		FireRate = fireRate;
	}
	
	public GameObjTypes getProjectileType(){
		return this.prjType;
	}
	
	protected void checkForFire(){
		//fire
		long curTime = TimeUtils.millis();
		elapsedTime = curTime - lastFireTime;
		if(elapsedTime >= 1000/FireRate){
			Notify(Message.SHOOT, this);
			//gObjects.addProjectile(GameObjTypes.BIRDPROJECTILE, new Vector2(this.getPosition().x +50, this.getPosition().y), new Vector2(velocity.x * 0.1f ,-190));
			lastFireTime = curTime;
		}
		
	
	}
	

	
}
