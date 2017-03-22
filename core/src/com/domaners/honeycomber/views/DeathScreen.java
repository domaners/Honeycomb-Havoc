package com.domaners.honeycomber.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.TimeUtils;
import com.domaners.honeycomber.Main;
import com.domaners.honeycomber.input.DeathScreenInput;

public class DeathScreen implements ViewMode {

	public long gameScore;
	static long startTime;
	static long waitTimeMillis;
	
	public DeathScreen(long gameScore) {
		this.gameScore = gameScore;
		Main.setHiScore(gameScore);
		startTime = TimeUtils.millis();
		waitTimeMillis = 500L;
	}
	
	@Override
	public void render() {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		if(Gdx.app.getType() == ApplicationType.Desktop) {
			font.draw(batch, "PRESS 'S' TO RESTART", 0, 150);
		} else if (Gdx.app.getType() == ApplicationType.Android) {
			font.draw(batch, "TOUCH THE SCREEN TO RESTART", 0, 150);
		}
		font.draw(batch, "YOU BEE DEDD", 0, 100);
		font.draw(batch, "   Score: " + gameScore, 0, 50);
		font.draw(batch, "Hi Score: " + Main.getHiScore(), 0, 70);
		batch.end();
		
		if(TimeUtils.timeSinceMillis(startTime) > waitTimeMillis) {
			if(Gdx.app.getType() == ApplicationType.Desktop) {
				DeathScreenInput.Keyboard();
			} else if (Gdx.app.getType() == ApplicationType.Android) {
				DeathScreenInput.Touch();
			}
		}
		
		cam.update();
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
