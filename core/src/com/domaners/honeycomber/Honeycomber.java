package com.domaners.honeycomber;

import java.util.ArrayList;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.domaners.honeycomber.Character.CharacterType;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Honeycomber extends ApplicationAdapter {
	
	enum GameState { ALIVE, DEAD };

	GameState gameState = GameState.ALIVE;
	static final int MAX_COLLECTIBLES = 10;
	static final int WORLD_WIDTH = 500;
    static final int WORLD_HEIGHT = 1000;
    static long gameScore;
    static long hiScore;
    static long startTime;
    
    Viewport viewport;
	SpriteBatch batch;
	OrthographicCamera cam, deathCam;
	Player p;
	BitmapFont font;
	Sprite bg;
	ArrayList<Character> co = new ArrayList<Character>();
	
	@Override
	public void create () {
	
		Gdx.graphics.setWindowedMode(WORLD_WIDTH, WORLD_HEIGHT);
		Gdx.graphics.setTitle("CameraBlast");
		batch = new SpriteBatch();
		startTime = TimeUtils.millis();
		
		cam = new OrthographicCamera();
		cam.setToOrtho(false, WORLD_WIDTH, WORLD_HEIGHT);
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, cam);
		
        deathCam = new OrthographicCamera();
		deathCam.setToOrtho(false, WORLD_WIDTH, WORLD_HEIGHT);
        
        
        p = new Player(0f, 0f, new Texture(Gdx.files.internal("player.jpg")));
        font = new BitmapFont();
        gameScore = 0;
        bg = new Sprite(new Texture(Gdx.files.internal("background.jpg")), WORLD_WIDTH, WORLD_HEIGHT);
        bg.setX(0f);
        bg.setY(0f);
        
        addHoneycomb();
        
	}

	@Override
	public void render () {
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		batch.draw(bg, bg.getX(), bg.getY());
		font.draw(batch, "   Score: " + gameScore, 0, 50);
		font.draw(batch, "Hi Score: " + hiScore, 0, 70);
		
		batch.draw(p.getTexture(), p.getX(), p.getY());
		for(Character ch : co) { 
			batch.draw(ch.getTexture(), ch.getX(), ch.getY());
		}
		batch.end();
		
		if (TimeUtils.timeSinceMillis(startTime) > 1000) { 
			addHoneycomb();
			startTime = TimeUtils.millis();
		}
		
		checkCollisions(co);
		
		if(Gdx.app.getType() == ApplicationType.Desktop) {
			checkKeyboardInput();
		} else if (Gdx.app.getType() == ApplicationType.Android) {
			checkTouchInput();
		}
		
		cam.update();
		
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
	
	void checkCollisions(ArrayList<Character> cha) {
		for(int i = 0; i < cha.size(); i++) {
			boolean b = cha.get(i).getHitbox().overlaps(p.getHitbox());
			if(b) {
				if(cha.get(i).charType == CharacterType.HONEYCOMB) {
					gameScore += cha.get(i).getPoints();
					cha.remove(i);
				} else if (cha.get(i).charType == CharacterType.WASP) {
					playerKilled();
				}
			}
		}
	}
	
	public void checkKeyboardInput() {
		
		float x = p.getHitbox().getX();
		float y = p.getHitbox().getY();
		
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			p.setX(x -= Player.MOVEMENT_SPEED);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			p.setX(x += Player.MOVEMENT_SPEED);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			p.setY(y -= Player.MOVEMENT_SPEED);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
			p.setY(y += Player.MOVEMENT_SPEED);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.Z)) {
			if(cam.zoom <= 3.0) {
				cam.zoom += 0.05f;
			}
		}
		if(Gdx.input.isKeyPressed(Input.Keys.X)) {
			if(cam.zoom >= 0.5) {
				cam.zoom -= 0.05f;
			}
		}
		
	}
	
	public void checkTouchInput() {
		
		float screenX = Gdx.app.getGraphics().getWidth() / 2;
		float touchX = Gdx.input.getX();
		
		if(Gdx.input.isTouched()) {
			p.setY(p.getY() + Player.MOVEMENT_SPEED);
			if(touchX < screenX) {
				p.setX(p.getX() - Player.MOVEMENT_SPEED);
				p.goLeft = true;
			} else { 
				p.setX(p.getX() + Player.MOVEMENT_SPEED);
				p.goLeft = false;
			}
		} else {
			p.setY(p.getY() - Player.MOVEMENT_SPEED);
			if(p.goLeft)
				p.setX(p.getX() - Player.MOVEMENT_SPEED);
			else 
				p.setX(p.getX() + Player.MOVEMENT_SPEED);
		}
		
	}
	
	private void playerKilled() {
		if(gameScore > hiScore)
			hiScore = gameScore;
		gameScore = 0;
		p.setX(0f);
		p.setY(0f);
		co.clear();
		gameState = GameState.DEAD;
	}
	
	private void addHoneycomb() {
		if(co.size() < Honeycomber.MAX_COLLECTIBLES) {
			float rand = (float)Math.random() * 10;
			if(rand <= 9.0f) {
				co.add(new Character(new Texture(Gdx.files.internal("honeycomb.jpg")), CharacterType.HONEYCOMB));
			} else {
				co.add(new Character(new Texture(Gdx.files.internal("wasp.jpg")), CharacterType.WASP));
			}
		}
	}
	
}
