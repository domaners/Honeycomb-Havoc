package com.domaners.honeycomber;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.domaners.honeycomber.views.*;

public class Main extends ApplicationAdapter {
	
	public static ViewMode viewMode;
	public static final int WORLD_WIDTH = 450;
    public static final int WORLD_HEIGHT = 800;
    private static long hiScore = 0;
    public static final String BUILD_NO = "v0.04";
	
	@Override
	public void create () {
	
		Gdx.graphics.setWindowedMode(WORLD_WIDTH, WORLD_HEIGHT);
		Gdx.graphics.setTitle("Honeycomb Havoc");
		viewMode = new TitleScreen();
		
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
		if(hiScore > Main.hiScore) 
			Main.hiScore = hiScore;
	}
	
}
