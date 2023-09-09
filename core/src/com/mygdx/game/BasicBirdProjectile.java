package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class BasicBirdProjectile extends GameObject{
	
	private static byte dmg;
	
	public BasicBirdProjectile(Vector2 pos, Vector2 vel, Sprite spr){
		super(GameObjTypes.BIRDPROJECTILE, pos, vel, spr);
		dmg = 10;
	}

	public byte getDmg() {
		return dmg;
	}

	public void setDmg(byte dmg) {
		this.dmg = dmg;
	}
	
	
	public void update(float deltaTime){
		//velocity.x += 2000 * deltaTime;
		velocity.y -= 1500 * deltaTime;
		super.update(deltaTime);
		
	}
}
