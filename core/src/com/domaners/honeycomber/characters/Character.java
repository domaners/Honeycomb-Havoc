package com.domaners.honeycomber.characters;

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
	
	public Rectangle getHitbox();
	public void setHitbox(Rectangle hitbox);
	public int getPoints();
	public float getMovementSpeed();
	
	public Sprite getSprite();
	public float getX();
	public float getY();
	public void setX(float x);
	public void setY(float y);
	public int getWidth();
	public int getHeight();
	
}
