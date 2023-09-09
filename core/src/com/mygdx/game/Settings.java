package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Settings {

	public static float birdFireRate = 0.5f;
	public static float playerMaxSpeed = 400;
	public static float playerMaxDrawPower = 10000;
	public static float targetProjectileSpeed = 1500;
	public static float basicBirdSpeed = 250;
	public static float tripleBirdSpeed = 400;
	public static float targetBirdSpeed = 100;
	public static float speedBirdSpeed = 500;
	public static float PowerUpDropSpeed = 170;
	public static Vector2 leftBirdSpawnPosition = new Vector2(50,850);
	public static Vector2 rightBirdSpawnPosition = new Vector2(Gdx.graphics.getWidth() - 50,850);
	public static Vector2 leftPowerUpSpawnPosition = new Vector2(50 , Gdx.graphics.getHeight() + 100);
	public static Vector2 rightPowerUpSpawnPosition = new Vector2(Gdx.graphics.getWidth() - 200 , Gdx.graphics.getHeight() + 100);
	public static int ArrowCountPowerUpDuration = 10000;
	public static int SpeedPowerUpDuration = 10000;
	public static int DrawPowerPowerUpDuration = 10000;
	
	
	public static Vector2 getPowerUpSpawnPosition(boolean spawnRight){
		
		float margin = 525;
		Vector2 k;
		
		if(spawnRight){
			
			k = new Vector2(Settings.rightPowerUpSpawnPosition);
			k.x = (float) (k.x  - Math.random() * margin);
		}
		else{
			
			k = new Vector2(Settings.leftPowerUpSpawnPosition);
			k.x = (float) (k.x  + Math.random() * margin);
		}

		return k;
	}
	
	public static GameObject getObject(GameObjTypes type, AssetManager am, Vector2 pos, Vector2 vel){
		
		GameObject g = null;
		
		
		if(Utils.isPowerUp(type)){	
			
			String puTexture;
			switch(type){
			case ARROWCOUNTPOWERUP:
				puTexture  = "arrowCountPowerUp.png"; 
				break;
			case DRAWPOWERPOWERUP:
				puTexture  = "drawPowerPowerUp.png"; 
				break;
			case SPEEDPOWERUP:
				puTexture  = "speedPowerUp.png"; 
				break;
			default:
				puTexture  = "speedPowerUp.png"; 
				break;
			
			}
			g = new GameObject(type, pos, vel, new Sprite(am.get(puTexture, Texture.class)));
			g.getSprite().setSize(50, 50);
			g.setMovement(new PowerUpMovement(g));
		}
		else{
			
		
			switch(type){
			case BIRD:
				g = new Bird(pos, vel, new Sprite(am.get("bird1.png", Texture.class)));
				g.getSprite().setSize(100, 50);
				g.setMovement(new BasicBirdMovement((Bird) g));
				break;
			case BIRDPROJECTILE:
				break;
			case PLAYER:
				break;
			case PLAYERPROJECTILE:
				break;	
			case TARGETBIRD:
				g = new Bird(pos, vel, new Sprite(am.get("bird1.png", Texture.class)));
				g.getSprite().setSize(100, 50);
				g.setMovement(new BasicBirdMovement((Bird) g));
				break;
			case SPEEDBIRD:
				g = new Bird(pos, vel, new Sprite(am.get("bird1.png", Texture.class)));
				g.getSprite().setSize(100, 50);
				g.setMovement(new FunctionBirdMovement((Bird) g));
				break;
			case TRIPLEBIRD:
				g = new Bird(pos, vel, new Sprite(am.get("bird1.png", Texture.class)));
				g.getSprite().setSize(100, 50);
				g.setMovement(new CircleBirdMovement((Bird) g, pos));
				break;
				
			default:
				break;

			}
		}
		g.setObjType(type);
		
		return g;
		
		
	
	}
	
	public static float getBirdSpeed(GameObjTypes type){
		
		switch(type){
		case BIRD:
			return Settings.basicBirdSpeed;
			
		case BIRDPROJECTILE:
			break;
		case PLAYER:
			break;
		case PLAYERPROJECTILE:
			break;
		case POWERUP:
			break;
		case TARGETBIRD:
			return Settings.targetBirdSpeed;
		
		case TRIPLEBIRD:
			return Settings.tripleBirdSpeed;
			
		case SPEEDBIRD:
			return Settings.speedBirdSpeed;
			
		default:
			return 0;
		
		
		}
		return 0;
		
	}
	
	
	
	
	static{
		
		
		
	}
	
}
