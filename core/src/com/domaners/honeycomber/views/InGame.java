package com.domaners.honeycomber.views;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.TimeUtils;

import static com.domaners.honeycomber.Main.*;

import com.domaners.honeycomber.characters.Character;
import com.domaners.honeycomber.characters.Coin;
import com.domaners.honeycomber.characters.Player;
import com.domaners.honeycomber.characters.Thistle;
import com.domaners.honeycomber.characters.ThistleCoin;
import com.domaners.honeycomber.characters.Wasp;
import com.domaners.honeycomber.Main;
import com.domaners.honeycomber.input.GameInput;

public class InGame implements ViewMode {

	static final int MAX_COLLECTIBLES = 10;
	public static long gameScore;
	public static long nextThousand;
    static long startTime;
    SpriteBatch fontBatch;
    
    ShapeRenderer scoreArea;
    Player p;
	Sprite bg;
	ArrayList<Character> co = new ArrayList<Character>();
	boolean thistleCoinCheck = false;
	
	public InGame() {
		
		scoreArea = new ShapeRenderer();
		scoreArea.setAutoShapeType(true);
		
		cam.setToOrtho(false, WORLD_WIDTH, WORLD_HEIGHT);
		startTime = TimeUtils.millis();
		nextThousand = 100L;
		p = new Player(WORLD_WIDTH / 2, 0f);
        gameScore = 0;
        bg = new Sprite(new Texture(Gdx.files.internal("Sky.png")), WORLD_WIDTH, WORLD_HEIGHT);
        bg.setX(0f);
        bg.setY(0f);
        co.add(new Thistle());
        fontBatch = new SpriteBatch();
        
	}
	
	@Override
	public void render() {
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if(TimeUtils.timeSinceMillis(screenRefreshTime) > Main.REFRESH_RATE) {
			
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
				} else if (ch instanceof ThistleCoin) {
					if(!thistleCoinCheck) {
						thistleCoinCheck = true;
					} else {
						if(ch.getPoints() <= 0) {
							thistleCoinCheck = false;
							co.remove(ch);
							break;
						} else {
							ch.setPoints(ch.getPoints() - 1);
						}
					}
				}
				
			}
			
			batch.setProjectionMatrix(cam.combined);
			batch.begin();
			batch.draw(bg, bg.getX(), bg.getY(), WORLD_WIDTH, WORLD_HEIGHT);
			batch.draw(p.getCurrentAnimation(), p.getX(), p.getY(), p.getWidth(), p.getHeight());
			for(Character ch : co) {
				batch.draw(ch.getSprite().getTexture(), ch.getX(), ch.getY(), ch.getWidth(), ch.getHeight());
				if(Main.debug)
					font.draw(batch, Integer.toString(ch.getPoints()), ch.getX(), ch.getY());
			}
			batch.end();
			
			scoreArea.begin();
			scoreArea.setColor(0, 0, 0, 0);
			scoreArea.set(ShapeType.Filled);
			scoreArea.rect(0, Main.WORLD_HEIGHT - 50, Main.WORLD_WIDTH, 50);
			scoreArea.end();
			
			fontBatch.begin();
			font.draw(fontBatch, "   Score: " + gameScore, 20, Main.WORLD_HEIGHT - 30);
			font.draw(fontBatch, "Hi Score: " + Main.getHiScore(), 200, Main.WORLD_HEIGHT - 30);
			fontBatch.end();
			
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
				if(cha.get(i) instanceof Coin) {
					cha.get(i).getCollisionSound().play(Main.getGameVolume());
					gameScore += cha.get(i).getPoints();
					cha.remove(i);
				} else if(cha.get(i) instanceof ThistleCoin) {
					cha.get(i).getCollisionSound().play(Main.getGameVolume());
					thistleCoinCheck = false;
					resetThistle();
					cha.remove(i);
				} else if (cha.get(i) instanceof Wasp) {
					changeViewMode(new DeathScreen(gameScore));
				} else if (cha.get(i) instanceof Thistle) {
					changeViewMode(new DeathScreen(gameScore));
				}
			}
		}
	}
	
	private void addHoneycomb() {
		if(co.size() < MAX_COLLECTIBLES) {
			float rand = (float)Math.random() * 10;
			co.add(new Coin(0f, 0f));
			if(rand > 9.5f && !thistleCoinCheck) {
				co.add(new ThistleCoin(0f, 0f));
			} else if(rand > 6.0f) {
				co.add(new Wasp());
			}
		}
	}
	
	public void changeViewMode(ViewMode viewMode) {
		for(Character ch: co) {
			ch.dispose();
		}
		p.dispose();
		Main.viewMode = viewMode;
		
	}
	
	public void resetThistle() {
		for(Character ch: co) {
			if(ch instanceof Thistle) {
				((Thistle)ch).setLastMovedScore(InGame.gameScore);
				ch.setX(0f);
			}
		}
	}
	
	public void dispose() {
		p.dispose();
		batch.dispose();
	}

}
