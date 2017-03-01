package com.domaners.honeycomber.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.domaners.honeycomber.Main;
import com.domaners.honeycomber.views.InGame;

public class Wasp implements Character {

	float x;
	float y;
	Sprite currentFrame;
	Rectangle hitbox;
	private int width;
	private int height;
	final float MOVEMENT_SPEED;
	
	public Wasp() {
		System.out.println("Wasp: ");
		currentFrame = new Sprite(new Texture(Gdx.files.internal("wasp.jpg")));
		width = 100;
		height = 100;
		this.y = (float)(Math.random() * (Main.WORLD_HEIGHT - height));
		this.x = Main.WORLD_WIDTH;
		hitbox = new Rectangle((float)this.x + 10, (float)this.y + 10, height - 20, width - 20);
		MOVEMENT_SPEED = (float) (InGame.gameScore / 2000) + 2.0f;
	}
	
	@Override
	public Rectangle getHitbox() {
		return this.hitbox;
	}

	@Override
	public void setHitbox(Rectangle hitbox) {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

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

}
