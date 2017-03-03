package com.domaners.honeycomber.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.domaners.honeycomber.Main;
import com.domaners.honeycomber.views.InGame;
import com.domaners.honeycomber.views.TitleScreen;

public class TitleScreenInput {
	
	public static void Keyboard() {
		if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			Main.viewMode = new InGame();
		}
		if(Gdx.input.isKeyPressed(Input.Keys.T)) {
			Main.viewMode = new TitleScreen();
		}
	}
	
	public static void Touch() {
		if(Gdx.input.isTouched()) {
			Main.viewMode = new InGame();
		}
	}
	
}
