package com.domaners.honeycomber;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.domaners.honeycomber.views.*;

public class Honeycomber extends ApplicationAdapter {
	
	public static ViewMode viewMode;
	public static final int WORLD_WIDTH = 500;
    public static final int WORLD_HEIGHT = 1000;
    private static long hiScore = 0;
	
	@Override
	public void create () {
	
		Gdx.graphics.setWindowedMode(WORLD_WIDTH, WORLD_HEIGHT);
		Gdx.graphics.setTitle("CameraBlast");
		viewMode = new InGame();
		
	}

	@Override
	public void render () {
		viewMode.render();
	}
	
	@Override
	public void dispose () {
		viewMode.dispose();
	}

	public static long getHiScore() {
		return hiScore;
	}

	public static void setHiScore(long hiScore) {
		if(hiScore > Honeycomber.hiScore) 
			Honeycomber.hiScore = hiScore;
	}
	
}
