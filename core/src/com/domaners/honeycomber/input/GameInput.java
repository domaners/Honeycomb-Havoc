package com.domaners.honeycomber.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.domaners.honeycomber.characters.Player;

public class GameInput {
	
	public static void Keyboard(Player p, OrthographicCamera cam) {
		
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			p.setY(p.getY() + Player.MOVEMENT_SPEED);
			p.setX(p.getX() - Player.MOVEMENT_SPEED);
			p.setLeft(true);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			p.setY(p.getY() + Player.MOVEMENT_SPEED);
			p.setX(p.getX() + Player.MOVEMENT_SPEED);
			p.setLeft(false);
		} 
		if(!Gdx.input.isKeyPressed(Input.Keys.RIGHT) && !Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			p.setY(p.getY() - Player.MOVEMENT_SPEED);
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
			p.setY(p.getY() + Player.MOVEMENT_SPEED);
			if(touchX < screenX) {
				p.setX(p.getX() - Player.MOVEMENT_SPEED);
				p.setLeft(true);
			} else { 
				p.setX(p.getX() + Player.MOVEMENT_SPEED);
				p.setLeft(false);
			}
		} else {
			p.setY(p.getY() - Player.MOVEMENT_SPEED);
			if(p.isLeft())
				p.setX(p.getX() - Player.MOVEMENT_SPEED);
			else 
				p.setX(p.getX() + Player.MOVEMENT_SPEED);
		}
		
	}
	
}
