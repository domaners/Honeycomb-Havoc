package com.domaners.honeycomber;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;

public class Player extends Character {

	static final float MOVEMENT_SPEED = 3.0f;
	boolean goLeft = true;
	
	Player(float x, float y, Texture t) {
		super(x, y, t);
	}
	
	Player(Texture t, CharacterType type) {
		super(t, type);
	}

}
