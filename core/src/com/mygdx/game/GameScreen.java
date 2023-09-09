package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;


public class GameScreen implements Screen, InputProcessor{

	
	private BirdFight 				app;
	private Sprite 					background;
	private HUD						hud;
	private GameObjectStorage 		gos;
	private AssetManager 			am;
	private SpawnManager			spawnManager;
	private OrthographicCamera 		cam;
	
	public GameScreen(BirdFight app, String lvlfile){
		
		
		this.app = app;
		Gdx.input.setInputProcessor(this);
		
		cam = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		cam.position.set(Gdx.graphics.getWidth()/2f , Gdx.graphics.getHeight()/2f , 0);
		
		background = new Sprite(new Texture(Gdx.files.internal("background2.jpg")));
		background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		am = new AssetManager();
		am.load("bird1.png", Texture.class);
		am.load("egg.png", Texture.class);
		am.load("player.jpg", Texture.class);
		am.load("arrow1.png", Texture.class);
		am.load("arrowCountPowerUp.png", Texture.class);
		am.load("speedPowerUp.png", Texture.class);
		am.load("drawPowerPowerUp.png", Texture.class);
		am.load("hudBackground.png", Texture.class);
		am.load("hearth.png", Texture.class);
		am.load("scoreText.png", Texture.class);
		am.finishLoading();
		
		hud = new HUD(10 + cam.position.x - cam.viewportWidth/2f, cam.position.y + -10 + cam.viewportHeight/2f - am.get("hudBackground.png", Texture.class).getHeight());
		hud.setBackgroundTexture(am.get("hudBackground.png", Texture.class));
		hud.setLifeTexture(am.get("hearth.png", Texture.class));
		hud.setScoreTexture(am.get("scoreText.png", Texture.class));
	
		gos = new GameObjectStorage(am);
	
		spawnManager = new SpawnManager(lvlfile);
		spawnManager.addObserver(gos);
	
		
		/*for(int i = 0; i<25; i++){
			
			Bird b = new Bird(new Vector2(i*150 , 850), new Vector2(100,0), new Sprite(am.get("bird1.png", Texture.class)));
			b.getSprite().setSize(100, 50);
			//b.setMovement(new BasicBirdMovement(b));
			b.setMovement(new CircleBirdMovement(b, 100, 10, new Vector2(i*150 , 750)));
			b.setFireRate(2);
			gos.add(b, GameObjTypes.BIRD);
			b.addObserver(gos);
		}*/
		
		Player p = new Player(new Vector2(0,150), new Vector2(0,0), new Sprite(am.get("player.jpg", Texture.class)));
		p.getSprite().setSize(50,70);
		p.addObserver(gos);
		gos.add(p, GameObjTypes.PLAYER);
		
		
	}
	
	
	@Override
	public void show() {

		spawnManager.start();
	}

	@Override
	public void render(float delta) {
		
		checkForNextLvL();
		getInput();
		update(delta);
		
	
		//cam.position.set(gos.getObjects(GameObjTypes.PLAYER).get(0).getPosition().x, gos.getObjects(GameObjTypes.PLAYER).get(0).getPosition().y,0);
		cam.update();
		app.batch.setProjectionMatrix(cam.combined);
		
		Gdx.gl.glClearColor(1, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		app.batch.begin();
		draw(app.batch);
		app.batch.end();
	
		
	}
	
	
	public void update(float delta){
		
		
		spawnManager.update();
		updateGameObjects(delta);
	}
	
	public void draw(Batch batch){
			
		background.draw(app.batch);
		
		for(GameObjTypes key : gos.getAllObjects().keySet()){
			
			for(GameObject g : gos.getAllObjects().get(key)){
				
				
				g.draw(batch);
			}
		
		}
	
		Player p = (Player) gos.getObjects(GameObjTypes.PLAYER).get(0);
		hud.draw(batch, (int)p.getHealth() , p.getScore());
	}
	

	
	public void updateGameObjects(float delta){
		
		for(GameObjTypes key : gos.getAllObjects().keySet()){
					
					for(GameObject g : gos.getAllObjects().get(key)){
						
						g.update(delta);
						//remove projectile if on ground
						if(key == GameObjTypes.BIRDPROJECTILE || key == GameObjTypes.PLAYERPROJECTILE){
							if(g.getPosition().y <= 100){
								gos.getObjects(key).removeValue(g, false);
							}
							
						}
						
						if(key == GameObjTypes.BIRDPROJECTILE){
							
							Player p = (Player) gos.getObjects(GameObjTypes.PLAYER).get(0);
							
							//player <=> egg collision
							if(g.getSprite().getBoundingRectangle().overlaps(p.getSprite().getBoundingRectangle())){
								//System.out.println("player hit by egg");
								p.changeHealthBy(-1);
								gos.getObjects(GameObjTypes.BIRDPROJECTILE).removeValue(g, false);
							}
						}
						else if(key == GameObjTypes.BIRD){
								//bird <=> projectile collision
							
								for(GameObject pj : gos.getObjects(GameObjTypes.PLAYERPROJECTILE)){
									
									//check for bird hit by projectile	
									if(g.getSprite().getBoundingRectangle().overlaps(pj.getSprite().getBoundingRectangle())){
										gos.getObjects(GameObjTypes.BIRD).removeValue(g, false);
										gos.getObjects(GameObjTypes.PLAYERPROJECTILE).removeValue(pj, false);
										//increase players score
										Player p = (Player) gos.getObjects(GameObjTypes.PLAYER).get(0);
										p.setScore(p.getScore()+10);
									}
								}
							
							
						}
						else if(key == GameObjTypes.POWERUP){
							// player <=> powerUp collect
							Player p = (Player) gos.getObjects(GameObjTypes.PLAYER).get(0);
							
							if(g.getSprite().getBoundingRectangle().overlaps(p.getSprite().getBoundingRectangle())){
								
								switch(g.getObjType()){
									case ARROWCOUNTPOWERUP:
										p.applyArrowPowerUp(new ArrowCountPowerUp(p, Settings.ArrowCountPowerUpDuration));
										break;
									case DRAWPOWERPOWERUP:
										p.applyDrawPowerPowerUP(new DrawPowerPowerUp(p , Settings.DrawPowerPowerUpDuration));
										break;
									case SPEEDPOWERUP:
										p.applySpeedPowerUp(new SpeedPowerUp(p, Settings.SpeedPowerUpDuration));			
										break;
									default:
										System.out.println("[GameScreen] :collision-player-powerup: wrong GameObjType in powerUp array");
										break;
								}
								
								gos.getObjects(key).removeValue(g, false);
							}
						}
						
						
					}
				
		}
		
		
	}
	
	private void checkForNextLvL(){
		//System.out.println(""+ gos.getObjects(GameObjTypes.BIRD).size + " | " + spawnManager.spawnsLeft() );
		if(gos.getObjects(GameObjTypes.BIRD).size == 0 && spawnManager.spawnsLeft() == 0){
			Player p = (Player) gos.getObjects(GameObjTypes.PLAYER).get(0);
			app.setScreen(new ScoreScreen(app,p.getScore()));		
		}
	}
	
	private void getInput(){
		
		if(Gdx.input.isKeyPressed(Keys.D)){
			Player p = (Player) gos.getObjects(GameObjTypes.PLAYER).get(0);
			p.MoveRight();
		}
		else if(Gdx.input.isKeyPressed(Keys.A)){
			
			Player p = (Player) gos.getObjects(GameObjTypes.PLAYER).get(0);
			p.MoveLeft();
		}
		else{
			Player p = (Player) gos.getObjects(GameObjTypes.PLAYER).get(0);
			p.StopMove();
		}
		
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
		// TODO Auto-generated method stub
		background.getTexture().dispose();
		am.dispose();
	}


	
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		
		
		
		if(keycode == Input.Keys.SPACE){
			Player p = (Player) gos.getObjects(GameObjTypes.PLAYER).get(0);
			p.startJumping();
		
		}
		/*
		if(keycode == Input.Keys.A){	
			Player p = (Player) gos.getObjects(GameObjTypes.PLAYER).get(0);
			p.MoveLeft();
		}
		else if(keycode == Input.Keys.D){	
			Player p = (Player) gos.getObjects(GameObjTypes.PLAYER).get(0);
			p.MoveRight();
		}
		else if(keycode == Input.Keys.S){	
			Player p = (Player) gos.getObjects(GameObjTypes.PLAYER).get(0);
			p.StopMove();
		}*/
		
		return false;
	}


	@Override
	public boolean keyUp(int keycode) {

		
	
		/*
		if(keycode == Input.Keys.A){	
			Player p = (Player) gos.getObjects(GameObjTypes.PLAYER).get(0);
			p.StopMove();
		}
		
		if(keycode == Input.Keys.D){	
			Player p = (Player) gos.getObjects(GameObjTypes.PLAYER).get(0);
			p.StopMove();
		}*/
		
		if(keycode == Input.Keys.ESCAPE){	
			  Gdx.app.exit();
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
		if(Input.Buttons.LEFT == button){
			
			Player p = (Player) gos.getObjects(GameObjTypes.PLAYER).get(0);
			p.startDraw();
		}
		
		
		
		return false;
	}


	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {


		if(Input.Buttons.LEFT == button){
			
			Player p = (Player) gos.getObjects(GameObjTypes.PLAYER).get(0);
			p.releaseDraw();
		}
		
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
