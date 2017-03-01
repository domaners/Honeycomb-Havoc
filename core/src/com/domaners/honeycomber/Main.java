package com.domaners.honeycomber;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.domaners.honeycomber.views.TitleScreen;
import com.domaners.honeycomber.views.ViewMode;

public class Main extends ApplicationAdapter {
	
	public static ViewMode viewMode;
	public static final int WORLD_WIDTH = 450;
    public static final int WORLD_HEIGHT = 800;
    private static long hiScore = 0;
    public static final String BUILD_NO = "v0.05";
	public static final boolean debug = true;
	public static Preferences prefs;
    
	@Override
	public void create () {
	
		prefs = Gdx.app.getPreferences("save-data");
		try {
			hiScore = prefs.getLong("hi-score");
		} catch (Exception e) {
			prefs.putLong("hi-score", 0);
		}
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
			prefs.putLong("hi-score", hiScore);
			prefs.flush();
	}
	
}
