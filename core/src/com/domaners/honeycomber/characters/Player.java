package com.domaners.honeycomber.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.domaners.honeycomber.Main;

public class Player implements Character {

	float x;
	float y;
	Sprite currentFrame;
	Rectangle hitbox;
	public int width;
	public int height;
	int points;
	
	public static final float MOVEMENT_SPEED = 3.0f;
	private boolean goLeft = true;
	
	public Player(float x, float y) {
		currentFrame = new Sprite(new Texture(Gdx.files.internal("player.png")));
		width = 80;
		height = 80;
		hitbox = new Rectangle(x, y, height, width);
		this.x = x;
		this.y = y;
	}
	
	public boolean isLeft() {
		return this.goLeft;
	}
	
	public void setLeft(boolean goLeft) {
		this.goLeft = goLeft;
	}
	
	public void setX(float x) {
		float frameWidth = width;
		float worldWidth = Main.WORLD_WIDTH;
		if(x > 0 && x < worldWidth - frameWidth) {
			this.x = x;
			this.hitbox.x = x;
		}
	}
	
	public void setY(float y) {
		float frameHeight = height;
		float worldHeight = Main.WORLD_HEIGHT;
		if(y > 0 && y < worldHeight - frameHeight) {
			this.y = y;
			this.hitbox.y = y;
		}
	}

	@Override
	public Rectangle getHitbox() {
		return this.hitbox;
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
	public void setHitbox(Rectangle hitbox) {
		// TODO Auto-generated method stub
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getPoints() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getMovementSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}

}
