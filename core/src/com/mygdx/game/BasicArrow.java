package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class BasicArrow extends GameObject{

	
	private byte dmg;
	
	public BasicArrow(Vector2 pos, Vector2 vel, Sprite spr){
		super(GameObjTypes.PLAYERPROJECTILE, pos, vel, spr);
		dmg = 100; 

		
	}
	
	
	public void update(float delta){
		super.update(delta);
		
		velocity.y -= 1100 * delta;
		
	}
	
}
