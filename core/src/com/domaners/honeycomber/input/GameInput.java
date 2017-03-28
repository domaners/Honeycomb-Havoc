package com.domaners.honeycomber.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.domaners.honeycomber.Main;
import com.domaners.honeycomber.ScoreArea;
import com.domaners.honeycomber.characters.Player;

public class GameInput {
	
	public static boolean isBuzzSound = false;
	
	public static void Keyboard(Player p, OrthographicCamera cam) {
		
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && !Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			p.setLeft(true);
			p.moveX(true);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && !Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			p.setLeft(false);
			p.moveX(true);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
			p.moveY(true);
		}
		if(!Gdx.input.isKeyPressed(Input.Keys.RIGHT) && !Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			p.moveX(false);
		}
		if(!Gdx.input.isKeyPressed(Input.Keys.UP)) {
			p.moveY(false);
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
		
		setBuzzSound(p);	
		setAnimation(p);
		
	}
	
	public static void Touch(Player p, OrthographicCamera cam) {
		
		float screenX = Gdx.app.getGraphics().getWidth() / 2;
		float touchX = Gdx.input.getX();
		
		if(Gdx.input.isTouched()) {
			p.getBuzzSound().play(1.0f);
			p.setY(p.getY() + p.MOVEMENT_SPEED_Y);
			if(touchX < screenX) {
				p.setLeft(true);
			} else { 
				p.setLeft(false);
			}
			p.moveX(true);
		} else {
			p.setY(p.getY() - p.MOVEMENT_SPEED_Y);
			p.moveX(false);
		}
		
		setAnimation(p);
		
	}
	
	private static void setBuzzSound(Player p) {
		if(Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isTouched()) {
			if(!isBuzzSound) {
				isBuzzSound = true;
				p.getBuzzSound().loop(Main.getGameVolume());
			}
		} else {
			if(isBuzzSound) {
				isBuzzSound = false;
				p.getBuzzSound().stop();
			}
		}
	}
	
	private static void setAnimation(Player p) {
		
		if(Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isTouched()) {
			if(p.isLeft()) {
				((Player)p).animationState = Player.playerState.flyLeft;
			} else {
				((Player)p).animationState = Player.playerState.flyRight;
			}
		} else {
			if(p.isLeft()) {
				((Player)p).animationState = Player.playerState.restLeft;
			} else {
				((Player)p).animationState = Player.playerState.restRight;
			}
		}
		
	}
	
}
