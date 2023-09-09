package com.mygdx.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BirdFight extends Game{
	
	public SpriteBatch batch;
	private GameScreen gs;
	private int currLvL;
	
	@Override
	public void create () {
		
		batch = new SpriteBatch();
		currLvL = 1;
		
		gs = new GameScreen(this, "level"+currLvL+".json");
		this.setScreen(gs);
		/*ScoreScreen sr = new ScoreScreen(this,111);
		this.setScreen(sr);*/
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.end();
		super.render();
	}
	
	
	public void nextLvL(){
		currLvL++;
		
		System.out.println("[BirdFight] Loading LvL: "+currLvL);
		gs = new GameScreen(this, "level"+currLvL+".json");
		this.setScreen(gs);

	}
	
	@Override
	public void dispose () {
		batch.dispose();

	}
	
	

}
