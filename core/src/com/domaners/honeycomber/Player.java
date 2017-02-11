package com.domaners.honeycomber;

import com.badlogic.gdx.graphics.Texture;

public class Player extends Character {

	public static final float MOVEMENT_SPEED = 3.0f;
	private boolean goLeft = true;
	
	public Player(float x, float y, Texture t) {
		super(x, y, t);
	}
	
	Player(Texture t, CharacterType type) {
		super(t, type);
	}
	
	public boolean isLeft() {
		return this.goLeft;
	}
	
	public void setLeft(boolean goLeft) {
		this.goLeft = true;
	}

}
