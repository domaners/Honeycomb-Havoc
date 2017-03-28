package com.domaners.honeycomber;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GraphicsUtils {

	public static int centreAlignX(int width) {
		
		return (Main.WORLD_WIDTH / 2) - (width / 2);
		
	}
	
	public static int centreAlignY(int height) {
		
		return (Main.WORLD_HEIGHT / 2) - (height / 2);
		
	}
	
	public static Animation<TextureRegion> createAnimation(Texture texture, int cols, int rows) {
		
		TextureRegion[][] tmp = TextureRegion.split(texture, 
				texture.getWidth() / cols,
				texture.getHeight() / rows);
		
		TextureRegion[] frames = new TextureRegion[cols * rows];
		int index = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				frames[index++] = tmp[i][j];
			}
		}

		// Initialize the Animation with the frame interval and array of frames
		return new Animation<TextureRegion>(0.05f, frames);
		
	}

}
