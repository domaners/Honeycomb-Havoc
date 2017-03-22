package com.domaners.honeycomber.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.domaners.honeycomber.Main;
import com.domaners.honeycomber.views.InGame;

public class Thistle implements Character {

	float x;
	float y;
	Sprite currentFrame;
	Rectangle hitbox;
	private int width;
	private int height;
	final float MOVEMENT_SPEED;
	Sound collisionSound;
	
	public Thistle() {
		currentFrame = new Sprite(new Texture(Gdx.files.internal("thistle.png")));
		width = 50;
		height = Main.WORLD_HEIGHT;
		this.y = 0F;
		this.x = 0F;
		hitbox = new Rectangle((float)this.x, (float)this.y, width, height);
		MOVEMENT_SPEED = (float) (InGame.gameScore / 2000) + 2.0f;
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
	public Sprite getSprite() {
		return this.currentFrame;
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
		this.hitbox.x = x;
	}

	@Override
	public void setY(float y) {
		this.y = y;
		this.hitbox.y = y;
	}

	@Override
	public int getWidth() {
		return this.width;
	}

	@Override
	public int getHeight() {
		return this.height;
	}

	@Override
	public float getMovementSpeed() {
		// TODO Auto-generated method stub
		return this.MOVEMENT_SPEED;
	}

	@Override
	public void setPoints(int points) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Sound getCollisionSound() {
		return this.collisionSound;
	}

}