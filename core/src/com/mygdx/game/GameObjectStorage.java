package com.mygdx.game;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;

public class GameObjectStorage implements IObserver {
	
	private  AssetManager am;
	private  HashMap<GameObjTypes, Array<GameObject>>GameObj;
	
	
	public GameObjectStorage(AssetManager am){
			this.am = am;
			
			GameObj = new  HashMap<GameObjTypes, Array<GameObject>>();
			
			GameObj.put(GameObjTypes.BIRD, new Array<GameObject>());
			GameObj.put(GameObjTypes.BIRDPROJECTILE, new Array<GameObject>());
			GameObj.put(GameObjTypes.PLAYERPROJECTILE, new Array<GameObject>());
			GameObj.put(GameObjTypes.PLAYER, new Array<GameObject>());
			GameObj.put(GameObjTypes.POWERUP, new Array<GameObject>());
			
			
			/*for(GameObjTypes key : GameObjTypes.values()){
				GameObj.put(key, new Array<GameObject>());
			}*/
	}
	
	
	public  void  add(GameObject o, GameObjTypes type){
		if(type == GameObjTypes.BIRD || 
		 type == GameObjTypes.TRIPLEBIRD || 
		 type == GameObjTypes.SPEEDBIRD || 
		 type == GameObjTypes.TARGETBIRD
		){
			GameObj.get(GameObjTypes.BIRD).add(o);		
		}
		else{
			GameObj.get(type).add(o);
		}
		
	}
	
	public  void addProjectile(GameObjTypes projType, Vector2 pos, Vector2 vel){
		
			
			if(projType == GameObjTypes.BIRDPROJECTILE){
				GameObj.get(projType).add(new BasicBirdProjectile(pos, vel, setUpProjectileSprite(GameObjTypes.BIRDPROJECTILE)));
			}
			else if (projType == GameObjTypes.PLAYERPROJECTILE){
				GameObj.get(projType).add(new BasicArrow(pos, vel, setUpProjectileSprite(GameObjTypes.PLAYERPROJECTILE)));
			}

	}
	
	
	public HashMap<GameObjTypes, Array<GameObject>> getAllObjects(){
		return this.GameObj;
	}
	
	
	
	public Array<GameObject> getObjects(GameObjTypes type){
		return GameObj.get(type);
	}


	@Override
	public void onNotify(Message m, GameObject k) {
		// TODO Auto-generated method stub
		
		if(m == Message.PLAYERSHOOT){
			addProjectile(GameObjTypes.PLAYERPROJECTILE, k.getPosition(), k.getVelocity());	
		}
		else if(m == Message.SHOOT){
			GameObjTypes t = k.getObjType();
			if(t == GameObjTypes.BIRD || t == GameObjTypes.SPEEDBIRD){
				
				addProjectile(GameObjTypes.BIRDPROJECTILE, new Vector2(k.getPosition().x +50, k.getPosition().y), new Vector2(k.getVelocity().x * 0.1f ,-190));
			
			}
			else if(t == GameObjTypes.PLAYER){
				
				Player p = (Player) k;
				
				float mx = Gdx.input.getX();
				float my = Gdx.graphics.getHeight() - Gdx.input.getY();
				Vector2 d = new Vector2(mx - p.getPosition().x, my - p.getPosition().y);
				d.nor();
				d.scl(p.getcurrentDrawPower());
		
				addProjectile(GameObjTypes.PLAYERPROJECTILE, new Vector2(k.getPosition().x + 10, k.getPosition().y), d);
			}
			else if(t == GameObjTypes.TRIPLEBIRD){
				
				addProjectile(GameObjTypes.BIRDPROJECTILE, new Vector2(k.getPosition().x +50, k.getPosition().y), new Vector2(0 ,-250));
				addProjectile(GameObjTypes.BIRDPROJECTILE, new Vector2(k.getPosition().x +50, k.getPosition().y), new Vector2(-60 ,-250));
				addProjectile(GameObjTypes.BIRDPROJECTILE, new Vector2(k.getPosition().x +50, k.getPosition().y), new Vector2(60 ,-250));
				
				
			}
			else if(t == GameObjTypes.TARGETBIRD){	
				
				Bird b = (Bird) k;
				Player p = (Player) getObjects(GameObjTypes.PLAYER).get(0);
				
					
				/*Vector2 d = new Vector2(p.getPosition().x - b.getPosition().x, p.getPosition().y - b.getPosition().y);
				d.nor();
				d.scl(200);		
				addProjectile(GameObjTypes.BIRDPROJECTILE, new Vector2(k.getPosition().x +50, k.getPosition().y), d);*/
				addTargetProjectile(b,p);
			}
		}
				
	}
	


	public void onNotify(Message m, String s) {
		
		if(m == Message.SPAWN){
			
			Json json = new Json();
			SpawnData sd = new SpawnData();
			sd = json.fromJson(SpawnData.class, s);
			GameObject g;
			
			
			if(Utils.isPowerUp(sd.type)){
				
				g = Settings.getObject(sd.type, am, Settings.getPowerUpSpawnPosition(sd.spawnRight), new Vector2(0,-Settings.PowerUpDropSpeed));
				add(g,GameObjTypes.POWERUP);
			}
			else{
				
				float v = Settings.getBirdSpeed(sd.type);
				Vector2 p = new Vector2(Settings.leftBirdSpawnPosition);
				if(sd.spawnRight){
					v = -v;
					p = new Vector2(Settings.rightBirdSpawnPosition);
				}
				g = Settings.getObject(sd.type, am, p, new Vector2(v, 0));
				g.addObserver(this);
				add(g, sd.type);
	
			}
			
				
			System.out.println("[GameObjectStorage] "+sd.toJSONString());
		
		}
		
	}
	
	private void addTargetProjectile(GameObject Bird, GameObject Target){
		
		
		BasicBirdProjectile bp = new BasicBirdProjectile(new Vector2(Bird.getPosition()), new Vector2(), setUpProjectileSprite(GameObjTypes.BIRDPROJECTILE));
		bp.setMovement(new TargetProjectileMovement(bp, Target));
		add(bp, GameObjTypes.BIRDPROJECTILE);
	}

	
	private Sprite setUpProjectileSprite(GameObjTypes type){
		Sprite s = new Sprite();
		if(type == GameObjTypes.BIRDPROJECTILE){
			s = new Sprite(am.get("egg.png",Texture.class));
			s.setColor(10, 10, 155, 255);
			s.setSize(30,50);
		}
		else if(type == GameObjTypes.PLAYERPROJECTILE){
			s = new Sprite(am.get("arrow1.png",Texture.class));
			s.setSize(30,50);
		}
	
		return s;
	}


}
