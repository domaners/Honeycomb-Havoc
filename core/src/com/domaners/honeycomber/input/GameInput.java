package com.domaners.honeycomber.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.domaners.honeycomber.characters.Player;

public class GameInput {
	
	public static void Keyboard(Player p, OrthographicCamera cam) {
		
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && !Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			p.getBuzzSound().play(1.0f);
			p.setLeft(true);
			p.moveX(true);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && !Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			p.getBuzzSound().play(1.0f);
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
		
	}
	
	public static void Touch(Player p, OrthographicCamera cam) {
		
		float screenX = Gdx.app.getGraphics().getWidth() / 2;
		float touchX = Gdx.input.getX();
		
		if(Gdx.input.isTouched()) {
			p.getBuzzSound().play(1.0f);
			p.setY(p.getY() + p.MOVEMENT_SPEED_Y);
			if(touchX < screenX) {
				// p.setX(p.getX() - p.MOVEMENT_SPEED_Y);
				p.setLeft(true);
			} else { 
				// p.setX(p.getX() + p.MOVEMENT_SPEED_Y);
				p.setLeft(false);
			}
			p.moveX(true);
		} else {
			p.setY(p.getY() - p.MOVEMENT_SPEED_Y);
			p.moveX(false);
		}
		
	}
	
}
