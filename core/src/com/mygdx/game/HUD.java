package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class HUD {

	
	private Vector2 position;
	private Sprite background;
	private Sprite lifeSprite;
	private Sprite scoreSprite;
	private BitmapFont bmf;
	
	public HUD(float posx, float posy){
		
		position = new Vector2(posx, posy);
		background = new Sprite();
		lifeSprite = new Sprite();
		scoreSprite = new Sprite();
		bmf = new BitmapFont();
		bmf.setColor(new Color(255.f/255.f,204.f/255.f,0,1));
	}
	
	
	public void setBackgroundTexture(Texture t){
		background = new Sprite(t);
		background.setPosition(position.x, position.y);
	}
	
	public void setLifeTexture(Texture t){
		lifeSprite = new Sprite(t);
	}
	
	public void setScoreTexture(Texture t){
		scoreSprite = new Sprite(t);
		scoreSprite.setPosition(position.x + 10 , position.y + 20);
		
	}
	
	
	public void draw(Batch batch, int playerLife, int score){
		
		background.draw(batch);
		for(int i = 0; i<playerLife; i++){
			float x = position.x + 10 + i * lifeSprite.getWidth() + 10 * i;
			float y =  position.y + background.getHeight() - (15 + lifeSprite.getHeight()) ;
			lifeSprite.setPosition(x, y);
			lifeSprite.draw(batch);
		}
		scoreSprite.draw(batch);
		bmf.draw(batch, Integer.toString(score), scoreSprite.getBoundingRectangle().x + scoreSprite.getBoundingRectangle().width + 10, scoreSprite.getBoundingRectangle().y + 15);
	}
	
}
