package com.domaners.honeycomber.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.domaners.honeycomber.Main;
import com.domaners.honeycomber.views.AnimationTest;
import com.domaners.honeycomber.views.InGame;
import com.domaners.honeycomber.views.TitleScreen;

public class TitleScreenInput {
	
	public static void Keyboard() {
		if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			Main.viewMode = new InGame();
		}
		if(Gdx.input.isKeyPressed(Input.Keys.T)) {
			Main.viewMode = new AnimationTest();
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.M)) {
			if(Main.getGameVolume() > 0.0f) {
				Main.setGameVolume(0f);
			} else {
				Main.setGameVolume(1.0f);
			}
		}
	}
	
	public static void Touch() {
		if(Gdx.input.isTouched()) {
			Main.viewMode = new InGame();
		}
	}
	
}
