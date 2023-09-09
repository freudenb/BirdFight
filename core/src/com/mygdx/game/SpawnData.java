package com.mygdx.game;

public class SpawnData {
	public GameObjTypes type;
	public int spawnTime;
	public boolean spawnRight;
	
	public SpawnData(){
		
		type = GameObjTypes.BIRD;
		spawnTime = 0;
		spawnRight = true;
	}
	
	
	public SpawnData(GameObjTypes t, int spawnT){
		
		type = t;
		spawnTime = spawnT;
		spawnRight = true;
	}
	
	public SpawnData(GameObjTypes t, int spawnT, boolean spawnR){
		this(t,spawnT);
		spawnRight = spawnR;
	}
	

	public String toJSONString(){
		
		return "{\"type\":\""+type.toString()+"\", \"spawnTime\":\""+spawnTime+"\", \"spawnRight\":\""+spawnRight+"\"}";
		
	}
}
