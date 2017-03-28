package com.domaners.honeycomber;

import com.badlogic.gdx.Application.ApplicationType;
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
    public static final String BUILD_NO = "v0.07";
	public static final boolean debug = false;
	public static Preferences prefs;
	public static final long REFRESH_RATE = 1000 / 30; // 30 FPS animation speed
	private static float gameVolume = 0f;
    
	@Override
	public void create () {
	
		prefs = Gdx.app.getPreferences("save-data");
		hiScore = prefs.getLong("hi-score");
		gameVolume = prefs.getFloat("game-volume");
		
		Gdx.graphics.setWindowedMode(WORLD_WIDTH, WORLD_HEIGHT);
		Gdx.graphics.setTitle("Honeycomb Havoc");
		
	}

	@Override
	public void render () {
		if(viewMode == null)
			viewMode = new TitleScreen();
		
		viewMode.render();
	}
	
	@Override
	public void dispose () {
		viewMode.dispose();
	}

	public static long getHiScore() {
		return hiScore;
	}
	
	public static float getGameVolume() {
		return gameVolume;
	}
	
	public static void setGameVolume(float soundMuted) {
		Main.gameVolume = soundMuted;
		prefs.putFloat("game-volume", 0f);
	}

	public static void setHiScore(long hiScore) {
		if(hiScore > Main.hiScore) {
			Main.hiScore = hiScore;
			prefs.putLong("hi-score", hiScore);
			prefs.flush();
			if(Gdx.app.getType() == ApplicationType.Android) {
				// Games.Leaderboards.submitScore(mGoogleApiClient, LEADERBOARD_ID, 1337);
			}
		}
	}
	
}
