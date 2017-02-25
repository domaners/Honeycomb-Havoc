package com.domaners.honeycomber.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.domaners.honeycomber.Main;

public class Coin implements Character {

	float x;
	float y;
	Sprite currentFrame;
	Rectangle hitbox;
	private int width;
	private int height;
	int points;
	
	public Coin(double x, double y) {
		currentFrame = new Sprite(new Texture(Gdx.files.internal("Coin.png")));
		height = 85;
		width = 85;
		points = 100;
		if(x == 0f && y == 0f) {
			this.x = (float)(Math.random() * (Main.WORLD_WIDTH - width));
			this.y = (float)(Math.random() * (Main.WORLD_HEIGHT - height));
		} else {
			this.x = (float)x;
			this.y = (float)y;
		}
		hitbox = new Rectangle((float)this.x, (float)this.y, height, width);
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
		// TODO Auto-generated method stub
		return currentFrame;
	}

	@Override
	public float getX() {
		// TODO Auto-generated method stub
		return this.x;
	}

	@Override
	public float getY() {
		// TODO Auto-generated method stub
		return this.y;
	}

	@Override
	public void setX(float x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setY(float y) {
		// TODO Auto-generated method stub
		
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	@Override
	public float getMovementSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}

}
