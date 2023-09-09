package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class ScoreScreen implements Screen, InputProcessor{
	
	
	private BirdFight app;
	private BitmapFont bFont;
	private int score;
	private Sprite background;
	
	public ScoreScreen(BirdFight app, int score){
		
		this.app = app;
		Gdx.input.setInputProcessor(this);
		this.score = score;
		bFont = new BitmapFont();
		bFont.setColor(Color.FIREBRICK);
		
		background = new Sprite(new Texture(Gdx.files.internal("background2.jpg")));
		background.setSize(1920, 1080);
	}

	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
	
	
	
		Gdx.gl.glClearColor(0, 0,0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		app.batch.begin();
		background.draw(app.batch);
		bFont.draw(app.batch, "NICEEEEEEEEEEEEEE YOU GOT "+score+" POINTS :() ", Gdx.graphics.getWidth()/2 - 50, 600);
		for(int i = 0; i<200; i++){
			bFont.draw(app.batch, "NICEEEEEEEEEEEEEE YOU GOT "+score+" POINTS :() ", Gdx.graphics.getWidth()/2 - 50, 25 * i+1);
		}
		app.batch.end();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
	
		background.getTexture().dispose();
	}
	
	
	
	
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		if(keycode == Input.Keys.SPACE){
			
			//GameScreen
			app.nextLvL();
		}
		
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}



}
