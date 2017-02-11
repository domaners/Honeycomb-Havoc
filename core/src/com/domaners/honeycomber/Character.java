package com.domaners.honeycomber;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Character {
	
	public enum CharacterType { HONEYCOMB, WASP };
	
	public CharacterType charType;
	float x, y;
	private Texture currentFrame;
	private Rectangle hitbox;
	private int points = 100;
	
	public int getPoints() {
		return points;
	}

	public Rectangle getHitbox() {
		return hitbox;
	}

	public void setHitbox(Rectangle hitbox) {
		this.hitbox = hitbox;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
	Character(float x, float y, Texture t) {
		this.x = x;
		this.y = y;
		currentFrame = t;
		hitbox = new Rectangle(this.x, this.y, currentFrame.getWidth(), currentFrame.getHeight());
	}
	
	public Character(Texture t, CharacterType type) {
		this.charType = type;
		this.currentFrame = t;
		this.x = (float) (Math.random() * Honeycomber.WORLD_WIDTH) - (this.getTexture().getWidth());
		this.y = (float) (Math.random() * Honeycomber.WORLD_HEIGHT) - (this.getTexture().getHeight());
		hitbox = new Rectangle(this.x, this.y, currentFrame.getWidth(), currentFrame.getHeight());
		System.out.println(this.x + " " + this.y);
	}
	
	public Texture getTexture() {
		return currentFrame;
	}
	
	public float getX() {
		return this.x;
	}
	
	public float getY() {
		return this.y;
	}
	
	public void setX(float x) {
		int frameWidth = currentFrame.getWidth();
		float worldWidth = Honeycomber.WORLD_WIDTH;
		if(x > 0 && x < worldWidth - frameWidth) {
			this.x = x;
			this.hitbox.x = x;
		}
	}
	
	public void setY(float y) {
		int frameHeight = currentFrame.getHeight();
		float worldHeight = Honeycomber.WORLD_HEIGHT;
		if(y > 0 && y < worldHeight - frameHeight) {
			this.y = y;
			this.hitbox.y = y;
		}
	}

}
