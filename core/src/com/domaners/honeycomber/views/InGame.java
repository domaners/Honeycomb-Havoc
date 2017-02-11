package com.domaners.honeycomber.views;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.TimeUtils;
import com.domaners.honeycomber.Character;

import static com.domaners.honeycomber.Honeycomber.*;
import com.domaners.honeycomber.Player;
import com.domaners.honeycomber.Character.CharacterType;
import com.domaners.honeycomber.Honeycomber;
import com.domaners.honeycomber.input.GameInput;

public class InGame implements ViewMode {

	static final int MAX_COLLECTIBLES = 10;
	static long gameScore;
    static long startTime;
    
    Player p;
	Sprite bg;
	ArrayList<Character> co = new ArrayList<Character>();
	
	public InGame() {
		
		cam.setToOrtho(false, WORLD_WIDTH, WORLD_HEIGHT);
		
		startTime = TimeUtils.millis();
        
        p = new Player(0f, 0f, new Texture(Gdx.files.internal("player.jpg")));
        gameScore = 0;
        bg = new Sprite(new Texture(Gdx.files.internal("background.jpg")), WORLD_WIDTH, WORLD_HEIGHT);
        bg.setX(0f);
        bg.setY(0f);
        
        addHoneycomb();
        
	}
	
	@Override
	public void render() {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		batch.draw(bg, bg.getX(), bg.getY());
		font.draw(batch, "   Score: " + gameScore, 0, 50);
		font.draw(batch, "Hi Score: " + Honeycomber.getHiScore(), 0, 70);
		
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
			GameInput.Keyboard(p, cam);
		} else if (Gdx.app.getType() == ApplicationType.Android) {
			GameInput.Touch(p, cam);
		}
		
		cam.update();
		
	}
	
	void checkCollisions(ArrayList<Character> cha) {
		for(int i = 0; i < cha.size(); i++) {
			boolean b = cha.get(i).getHitbox().overlaps(p.getHitbox());
			if(b) {
				if(cha.get(i).charType == CharacterType.HONEYCOMB) {
					gameScore += cha.get(i).getPoints();
					cha.remove(i);
				} else if (cha.get(i).charType == CharacterType.WASP) {
					Honeycomber.viewMode = new DeathScreen(gameScore);
				}
			}
		}
	}
	
	private void addHoneycomb() {
		if(co.size() < MAX_COLLECTIBLES) {
			float rand = (float)Math.random() * 10;
			if(rand <= 9.0f) {
				co.add(new Character(new Texture(Gdx.files.internal("honeycomb.jpg")), CharacterType.HONEYCOMB));
			} else {
				co.add(new Character(new Texture(Gdx.files.internal("wasp.jpg")), CharacterType.WASP));
			}
		}
	}
	
	public void dispose() {
		batch.dispose();
	}

}
