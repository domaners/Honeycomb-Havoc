package com.domaners.honeycomber.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.graphics.GL20;
import com.domaners.honeycomber.Honeycomber;
import com.domaners.honeycomber.input.DeathScreenInput;

public class DeathScreen implements ViewMode {

	public long gameScore;
	
	public DeathScreen(long gameScore) {
		this.gameScore = gameScore;
		Honeycomber.setHiScore(gameScore);
	}
	
	@Override
	public void render() {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		font.draw(batch, "PRESS 'S' TO RESTART", 0, 150);
		font.draw(batch, "YOU BE DEDD", 0, 100);
		font.draw(batch, "   Score: " + gameScore, 0, 50);
		font.draw(batch, "Hi Score: " + Honeycomber.getHiScore(), 0, 70);
		batch.end();
		
		if(Gdx.app.getType() == ApplicationType.Desktop) {
			DeathScreenInput.Keyboard();
		} else if (Gdx.app.getType() == ApplicationType.Android) {
			DeathScreenInput.Touch();
		}
		
		cam.update();
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
