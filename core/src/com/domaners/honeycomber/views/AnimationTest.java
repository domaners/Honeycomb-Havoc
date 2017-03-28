package com.domaners.honeycomber.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationTest implements ViewMode {

	private static final int FRAME_COLS = 6, FRAME_ROWS = 5;

	Animation<TextureRegion> walkAnimation; // Must declare frame type (TextureRegion)
	Texture walkSheet;
	SpriteBatch spriteBatch;
	
	float stateTime;
	
	public AnimationTest() {
		
		walkSheet = new Texture(Gdx.files.internal("sprite-animation1.png"));
		TextureRegion[][] tmp = TextureRegion.split(walkSheet, 
				walkSheet.getWidth() / FRAME_COLS,
				walkSheet.getHeight() / FRAME_ROWS);
		
		TextureRegion[] walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		int index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				walkFrames[index++] = tmp[i][j];
			}
		}

		// Initialize the Animation with the frame interval and array of frames
		walkAnimation = new Animation<TextureRegion>(0.05f, walkFrames);

		// Instantiate a SpriteBatch for drawing and reset the elapsed animation
		// time to 0
		stateTime = 0f;
		
	}
	
	@Override
	public void render() {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time
		
		// Get current frame of animation for the current stateTime
		TextureRegion currentFrame = walkAnimation.getKeyFrame(stateTime, true);
		
		
		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		
		batch.draw(currentFrame, 50, 50); // Draw current frame at (50, 50)
		
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
		walkSheet.dispose();
	}

}
