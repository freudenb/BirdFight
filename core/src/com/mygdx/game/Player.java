package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Player extends GameObject{
	
	private float drawPower;
	private float currDrawPower;
	private float MaxDrawPower;
	private boolean isDrawing;
	private boolean isJumping;
	private float MaxSpeed;
	private int health;
	private int score;
	
	
	// PowerUP-Slots
	/*
	 * [0] SpeedPowerUp
	 * [1] ArrowCountPowerUp
	 * [2] DrawPowerPowerUp
	 * 
	 *
	 */
	private Array<BasicPowerUp> powerUps;
	
	
	public Player(Vector2 pos, Vector2 vel, Sprite spr){
		super(GameObjTypes.PLAYER,pos,vel,spr);
	
		drawPower = 3500;
		currDrawPower = 0;
		MaxDrawPower = Settings.playerMaxDrawPower;
		isDrawing = false;
		isJumping = false;
		MaxSpeed = Settings.playerMaxSpeed;
		health = 5;
		setScore(0);
		powerUps = new Array<BasicPowerUp>(3);
		
		for(int i = 0; i< 3; i++){
			powerUps.add(null);
		}
	}
	
	
	public void setMaxSpeed(float mSpeed){
		MaxSpeed = mSpeed;
	}
	
	
	public float getMaxSpeed(){
		return MaxSpeed;
	}
	
	public void update(float delta){
		super.update(delta);
		
		if(powerUps.get(2) != null){
			currDrawPower = MaxDrawPower;
		}
		else if(currDrawPower <= MaxDrawPower && isDrawing){
			currDrawPower += drawPower * delta;
		}
		
		if(isJumping){
			velocity.y -= 1000 * delta; 
			if(position.y <= 150){
				position.y = 150;
				velocity.y = 0;
				isJumping = false;
				
			}
		}
		
		
		for(BasicPowerUp pu : powerUps){
			if(pu != null)pu.update(delta);
		}
	}
	
	
	public void draw(Batch batch){
		super.draw(batch); 
	}
	
	public void startDraw(){

		isDrawing = true;
	}

	public void startJumping(){
		
		if(!isJumping){
			velocity.y = 600;
			isJumping = true;
		}
	}
	
	public void releaseDraw(){


		
		
		//calc angle, see nature of code http://natureofcode.com/book/chapter-3-oscillation/
		if(powerUps.get(1) != null){
			
			float mx = Gdx.input.getX();
			float my = Gdx.graphics.getHeight() - Gdx.input.getY();
			Vector2 d = new Vector2(mx - getPosition().x, my - getPosition().y);
			d.nor();
			d.scl(getcurrentDrawPower());
			
			
			ArrowCountPowerUp aUp = (ArrowCountPowerUp) powerUps.get(1);
			float angle = (float) Math.atan2(d.y, d.x);
			float a = 45;
			float s = (float) (Math.toDegrees(angle) - a/2);
			float steps = a/aUp.arrows;
			int stepsR = Math.round(steps);
			for(int i = (int)s; i< (float) (Math.toDegrees(angle)+ a/2); i+=stepsR){
			
				Vector2 v = new Vector2();
				v.x  = (float) Math.cos(Math.toRadians(i)) * d.len();
				v.y  = (float) Math.sin(Math.toRadians(i)) * d.len();
				Notify(Message.PLAYERSHOOT, new GameObject(GameObjTypes.PLAYERPROJECTILE,new Vector2(getPosition()), v, new Sprite()));
				
			}
			
		}
		else{
			Notify(Message.SHOOT,this);
		}
	
		
		currDrawPower = 0;
		isDrawing = false;
	}
	
	public void MoveLeft(){
		
		if(!isJumping){
			velocity.x = -MaxSpeed;
		}
		
	}
	
	public void MoveRight(){
		
		if(!isJumping){
			velocity.x = MaxSpeed;
		}
	}

	public void StopMove(){
		
		if(!isJumping){
			velocity.x = 0;
		}
	}
	
	
	
	public void applySpeedPowerUp(SpeedPowerUp sPU){
		applyPowerUp(sPU, 0);
	}
	
	public void applyArrowPowerUp(ArrowCountPowerUp arrowCountPowerUp){
		applyPowerUp(arrowCountPowerUp, 1);
	}
	
	public void applyDrawPowerPowerUP(DrawPowerPowerUp sPU){
		applyPowerUp(sPU, 2);
	}

	private void applyPowerUp(BasicPowerUp pu, int i){
		if(i >= powerUps.size){	
			return;
		}
		if(powerUps.get(i) == null){
			
				pu.setActive();
				powerUps.set(i, pu);
			
		}
		else{
			//powerup already set => increase power up duration
		}
	}
	
	public void removePowerUp(int i){

		if(i < powerUps.size){
			powerUps.set(i, null);
		}
	}
	
	public boolean hasPowerUp(int i){
		if(i < powerUps.size){
			if(powerUps.get(i)!= null){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
	}
	
	public void changeHealthBy(float ch){
		health += ch;
	}
	
	public float getHealth(){
		return health;
	}
	
	public float getcurrentDrawPower(){
		return this.currDrawPower;
	}


	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}
}
