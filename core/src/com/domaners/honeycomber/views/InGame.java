package com.domaners.honeycomber.views;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.TimeUtils;

import static com.domaners.honeycomber.Main.*;

import com.domaners.honeycomber.characters.Character;
import com.domaners.honeycomber.characters.Coin;
import com.domaners.honeycomber.characters.Player;
import com.domaners.honeycomber.characters.Wasp;
import com.domaners.honeycomber.Main;
import com.domaners.honeycomber.input.GameInput;

public class InGame implements ViewMode {

	static final int MAX_COLLECTIBLES = 10;
	public static long gameScore;
    static long startTime;
    
    Player p;
	Sprite bg;
	ArrayList<Character> co = new ArrayList<Character>();
	
	public InGame() {
		
		cam.setToOrtho(false, WORLD_WIDTH, WORLD_HEIGHT);
		startTime = TimeUtils.millis();
		p = new Player(0f, 0f);
        gameScore = 0;
        bg = new Sprite(new Texture(Gdx.files.internal("Sky.png")), WORLD_WIDTH, WORLD_HEIGHT);
        bg.setX(0f);
        bg.setY(0f);
        
	}
	
	@Override
	public void render() {

		if(TimeUtils.timeSinceMillis(screenRefreshTime) > Main.REFRESH_RATE) {
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	
			batch.setProjectionMatrix(cam.combined);
			batch.begin();
			batch.draw(bg, bg.getX(), bg.getY(), WORLD_WIDTH, WORLD_HEIGHT);
			font.draw(batch, "   Score: " + gameScore, 0, 50);
			font.draw(batch, "Hi Score: " + Main.getHiScore(), 0, 70);
			batch.draw(p.getSprite(), p.getX(), p.getY(), p.getWidth(), p.getHeight());
			for(Character ch : co) {
				if(ch instanceof Wasp) {
					if(ch.getX() < (0f - ch.getWidth())) {
						co.remove(ch);
						break;
					} else {
						ch.setX(ch.getX() - ch.getMovementSpeed());
					}
				} else if (ch instanceof Coin) {
					if(ch.getPoints() <= 0) {
						co.remove(ch);
						break;
					} else {
						ch.setPoints(ch.getPoints() - 1);
					}
				}
				batch.draw(ch.getSprite().getTexture(), ch.getX(), ch.getY(), ch.getWidth(), ch.getWidth());
				if(Main.debug)
					font.draw(batch, Integer.toString(ch.getPoints()), ch.getX(), ch.getY());
			}
			batch.end();
		}
		
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
				System.out.println("COIN!");
				if(cha.get(i) instanceof Coin) {
					gameScore += cha.get(i).getPoints();
					cha.remove(i);
				} else if (cha.get(i) instanceof Wasp) {
					Main.viewMode = new DeathScreen(gameScore);
				}
			}
		}
	}
	
	private void addHoneycomb() {
		if(co.size() < MAX_COLLECTIBLES) {
			float rand = (float)Math.random() * 10;
			if(rand <= 7.0f) {
				co.add(new Coin(0f, 0f));
			} else {
				co.add(new Wasp());
			}
		}
	}
	
	public void dispose() {
		batch.dispose();
	}

}
