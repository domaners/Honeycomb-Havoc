package com.domaners.honeycomber.characters;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;
import com.domaners.honeycomber.Main;
import com.domaners.honeycomber.GraphicsUtils;

public class Player implements Character {

	public enum playerState { flyLeft, flyRight, restLeft, restRight };
	public playerState animationState;
	
	float x;
	float y;
	Animation<TextureRegion> playerFlyRight, playerFlyLeft, playerRestLeft, playerRestRight;
	Rectangle hitbox;
	public int width;
	public int height;
	int points;
	private Sound collisionSound, buzzSound;
	public float MOVEMENT_SPEED_Y = 3.0f;
	public float MOVEMENT_SPEED_X = 0.0f;
	private boolean goLeft = true;
	long accelerationRateMillisX, accelerationRateMillisY;
	long accelerationRefreshTimeX = TimeUtils.millis();
	long accelerationRefreshTimeY = TimeUtils.millis();
	float animationTime = 0.0f;

	public Player(float x, float y) {
		
		playerFlyRight = GraphicsUtils.createAnimation(new Texture(Gdx.files.internal("player-fly-right.png")), 2, 1);
		playerFlyLeft = GraphicsUtils.createAnimation(new Texture(Gdx.files.internal("player-fly-left.png")), 2, 1);
		playerRestRight = GraphicsUtils.createAnimation(new Texture(Gdx.files.internal("player-rest-right.png")), 1, 1);
		playerRestLeft = GraphicsUtils.createAnimation(new Texture(Gdx.files.internal("player-rest-left.png")), 1, 1);
		
		buzzSound = Gdx.audio.newSound(Gdx.files.internal("sounds/playerBuzz.wav"));
		// currentFrame = new Sprite(new Texture(Gdx.files.internal("player.png")));
		width = 80;
		height = 80;
		hitbox = new Rectangle(x + collisionOffset, y + collisionOffset, height - (collisionOffset * 2), width - (collisionOffset * 2));
		this.x = x;
		this.y = y;
		accelerationRateMillisX = 1L;
		accelerationRefreshTimeX = TimeUtils.millis();
		accelerationRateMillisY = 1L;
		accelerationRefreshTimeY = TimeUtils.millis();
		animationState = playerState.restRight;
	}
	
	public TextureRegion getCurrentAnimation() {
		
		TextureRegion currentFrame;
		animationTime += Gdx.graphics.getDeltaTime();
		
		switch(this.animationState) {
		
		case flyLeft: 
			currentFrame = playerFlyLeft.getKeyFrame(animationTime, true);
			break;
		case flyRight:
			currentFrame = playerFlyRight.getKeyFrame(animationTime, true);
			break;
		case restLeft:
			currentFrame = playerRestLeft.getKeyFrame(animationTime, true);
			break;
		default:
			currentFrame = playerRestRight.getKeyFrame(animationTime, true);
			break;
			
		}

		return currentFrame;
		
	}
	
	public boolean isLeft() {
		return this.goLeft;
	}
	
	public void setLeft(boolean goLeft) {
		this.goLeft = goLeft;
	}
	
	public void moveX(boolean movedByPlayer) {
		float frameWidth = this.width;
		float worldWidth = Main.WORLD_WIDTH;
		this.updateMovementSpeedX(movedByPlayer);
		this.x = this.x + this.MOVEMENT_SPEED_X;
		if(x < 0)
			x = 0;
		else if (x > worldWidth - frameWidth)
			x = worldWidth - frameWidth;
		this.hitbox.x = x + collisionOffset;
	}
	
	public void updateMovementSpeedX(boolean increaseMomentum) {
		if(TimeUtils.timeSinceMillis(this.accelerationRefreshTimeX) > this.accelerationRateMillisX) {
			this.accelerationRefreshTimeX = TimeUtils.millis();
			if(increaseMomentum) {
				if(goLeft && this.MOVEMENT_SPEED_X >= -3.0F)
					this.MOVEMENT_SPEED_X = this.MOVEMENT_SPEED_X - 0.2f;
				else if(!goLeft && this.MOVEMENT_SPEED_X <= 3.0F)
					this.MOVEMENT_SPEED_X = this.MOVEMENT_SPEED_X + 0.2f;
			} else {
				if(this.MOVEMENT_SPEED_X > 0.0f)
					this.MOVEMENT_SPEED_X = this.MOVEMENT_SPEED_X - 0.03f;
				else if(this.MOVEMENT_SPEED_X < 0.0f)
					this.MOVEMENT_SPEED_X = this.MOVEMENT_SPEED_X + 0.03f;
			}
		}
	}
	
	public void moveY(boolean movedByPlayer) {
		float frameHeight = this.height;
		float worldHeight = Main.WORLD_HEIGHT;
		this.updateMovementSpeedY(movedByPlayer);
		this.y = this.y + this.MOVEMENT_SPEED_Y;
		if(this.y < 0)
			this.y = 0;
		else if(this.y > worldHeight - frameHeight)
			this.y = worldHeight - frameHeight;
		this.hitbox.y = y + collisionOffset;
	}
	
	public void updateMovementSpeedY(boolean increaseMomentum) {
		if(TimeUtils.timeSinceMillis(this.accelerationRefreshTimeY) > this.accelerationRateMillisY) {
			this.accelerationRefreshTimeY = TimeUtils.millis();
			if(increaseMomentum && this.MOVEMENT_SPEED_Y <= 3.0F) {
				this.MOVEMENT_SPEED_Y = this.MOVEMENT_SPEED_Y + 0.4f;
			} else if (this.MOVEMENT_SPEED_Y >= -3.0F) {
				this.MOVEMENT_SPEED_Y = this.MOVEMENT_SPEED_Y - 0.5f;
			}
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
		return this.MOVEMENT_SPEED_X;
	}

	@Override
	public void setPoints(int points) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Sound getCollisionSound() {
		return this.collisionSound;
	}

	public Sound getBuzzSound() {
		return buzzSound;
	}

	@Override
	public void setX(float x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setY(float y) {
		float frameHeight = height;
		float worldHeight = Main.WORLD_HEIGHT;
		if(y > 0 && y < worldHeight - frameHeight) {
			this.y = y;
			this.hitbox.y = y + collisionOffset;
		}
	}

	@Override
	public void dispose() {
		this.buzzSound.stop();
	}

}
