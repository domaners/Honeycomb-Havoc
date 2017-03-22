package com.domaners.honeycomber.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;
import com.domaners.honeycomber.Main;

public class BackgroundElement implements Character {

	float x;
	float y;
	Sprite currentFrame;
	Rectangle hitbox;
	private int width;
	private int height;
	int points;
	private int movementSpeed = 50;
	private long animateTime;
	private Sound collisionSound;
	
	public BackgroundElement(double x, double y) {
	
		Texture tx = new Texture(Gdx.files.internal("Sky.png"));
		currentFrame = new Sprite(tx);
		height = tx.getHeight();
		width = tx.getWidth();
		points = 100;
		this.x = (float)x;
		this.y = (float)y;
		hitbox = new Rectangle((float)this.x, (float)this.y, height, width);
		animateTime = TimeUtils.millis();
	
	}
	
	public BackgroundElement(double x, double y, int speed) {
		
		this(x, y);
		this.movementSpeed = speed;
		
	}
	
	@Override
	public Rectangle getHitbox() {
		return this.hitbox;
	}

	@Override
	public void setHitbox(Rectangle hitbox) {
		this.hitbox = hitbox;
	}

	@Override
	public int getPoints() {
		return this.points;
	}

	@Override
	public void setPoints(int points) {
		this.points = points;
	}

	@Override
	public Sprite getSprite() {
		return currentFrame;
	}

	@Override
	public float getX() {
		return this.x;
	}

	@Override
	public float getY() {
		return this.y;
	}

	@Override
	public void setX(float x) {
		this.x = x;
	}

	@Override
	public void setY(float y) {
		this.y = y;
	}

	@Override
	public int getWidth() {
		return this.width;
	}

	@Override
	public int getHeight() {
		return this.height;
	}

	public long getAnimateTime() {
		return this.animateTime;
	}

	public void setAnimateTime(long animateTime) {
		this.animateTime = animateTime;
	}

	public void setMovementSpeed(int movementSpeed) {
		this.movementSpeed = movementSpeed;
	}

	@Override
	public float getMovementSpeed() {
		return this.movementSpeed;
	}

	@Override
	public Sound getCollisionSound() {
		return this.collisionSound;
	}
	
}
