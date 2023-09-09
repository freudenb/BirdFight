package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.TimeUtils;

public class SpawnManager extends Subject{

	
	private String SpawnFile;
	private long LastSpawnTime;
	private Array<SpawnData>spawns;
	private int currentSpawn;
	
	public SpawnManager(String SpawnFile){
		
		this.SpawnFile = SpawnFile;
		LastSpawnTime = 0;
		currentSpawn = 0;
		spawns = new Array<SpawnData>();
		
		
		/*for(int i = 0; i < 15; i++){
			
			SpawnData sd = new SpawnData(GameObjTypes.BIRD, i*500);
			spawns.add(sd);
			sd = new SpawnData(GameObjTypes.SPEEDBIRD, i*500 + 50);
			spawns.add(sd);
			
		}
		
		
		SpawnData sd = new SpawnData(GameObjTypes.ARROWCOUNTPOWERUP, 5000, false);
		spawns.add(sd);
		sd = new SpawnData(GameObjTypes.DRAWPOWERPOWERUP, 10000, true);
		spawns.add(sd);
		sd = new SpawnData(GameObjTypes.SPEEDPOWERUP, 11000, false);
		spawns.add(sd);*/
		loadLevel(SpawnFile);
	}
	
	public void start(){
		//load file here
		LastSpawnTime = TimeUtils.millis();
	}
	
	
	public void update(){
		
		//check for next Bird spawn
		long t = TimeUtils.millis();
		if(currentSpawn < spawns.size && t - LastSpawnTime >= spawns.get(currentSpawn).spawnTime){
			System.out.println(spawns.get(currentSpawn).toJSONString());
			Notify(Message.SPAWN, spawns.get(currentSpawn).toJSONString());
			LastSpawnTime = t;
			if(currentSpawn < spawns.size){
				currentSpawn++;
			}
			
		}
		
	}
	
	private void loadLevel(String file){
	
			FileHandle filex = Gdx.files.local("levels/"+file);
			Json json = new Json();
			spawns = json.fromJson(Array.class, SpawnData.class , filex);
	

	}
	
	
	public int spawnsLeft(){
		return spawns.size - currentSpawn;
	}
	
}
