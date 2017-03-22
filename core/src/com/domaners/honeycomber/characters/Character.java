package com.domaners.honeycomber.characters;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public interface Character {
	
	float x = 0f;
	float y = 0f;
	Sprite currentFrame = new Sprite();
	Rectangle hitbox = new Rectangle();
	public int height = 0;
	public int width = 0;
	int points = 0;
	int collisionOffset = 10;
	Sound collisionSound = null;
	
	public Rectangle getHitbox();
	public void setHitbox(Rectangle hitbox);
	public int getPoints();
	public void setPoints(int points);
	public float getMovementSpeed();
	public Sound getCollisionSound();
	
	public Sprite getSprite();
	public float getX();
	public float getY();
	public void setX(float x);
	public void setY(float y);
	public int getWidth();
	public int getHeight();
	
}
