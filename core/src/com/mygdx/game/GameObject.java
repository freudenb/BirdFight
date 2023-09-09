package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class GameObject extends Subject implements IGameObject{

	
	protected Vector2 position;
	protected Vector2 velocity;
	protected Sprite sprite;
	protected GameObjTypes type;
	protected IMovement movement;
	
	public GameObject(GameObjTypes ty, Vector2 pos, Vector2 vel, Sprite spr){
		position = new Vector2(pos);
		velocity = new Vector2(vel);
		sprite = new Sprite(spr);
		type = ty;
		observers = new Array<IObserver>();
		movement = null;
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = new Vector2(position);
	}

	public Vector2 getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2 velocity) {
		this.velocity = new Vector2(velocity);
	}
	
	
	public void setSprite(Sprite spr) {
		sprite = spr;
	}
	
	
	public Sprite getSprite(){
		return sprite;
	}
	public void draw(Batch batch){
		
		sprite.setPosition(position.x, position.y);
		sprite.draw(batch);
	}
	
	public void update(float deltaTime){
		
		if(movement == null){
			position.x += velocity.x * deltaTime;
			position.y += velocity.y * deltaTime;
		}
		else{
			movement.move(deltaTime);
		}
	}
	

	public void setObjType(GameObjTypes type){
		this.type = type;
	}
	
	public GameObjTypes getObjType(){
		return this.type;
	}
	
	public void setMovement(IMovement m){
		movement = m;
	}
}
