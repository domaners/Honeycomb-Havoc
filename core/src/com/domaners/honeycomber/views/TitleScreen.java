package com.domaners.honeycomber.views;

import static com.domaners.honeycomber.Main.WORLD_HEIGHT;
import static com.domaners.honeycomber.Main.WORLD_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.domaners.honeycomber.input.TitleScreenInput;

public class TitleScreen implements ViewMode {

public long gameScore;
private Sprite logo = new Sprite(new Texture(Gdx.files.internal("logo.png")));


	// Character test = new Character(100f, 100f, new Texture(Gdx.files.internal("honeycomb.jpg")));
	
	@Override
	public void render() {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		batch.draw(logo, WORLD_WIDTH / 2, WORLD_HEIGHT / 2);
		font.draw(batch, "THIS IS 'BEE GAME'", 0, 100);
		font.draw(batch, "A STUDIO DOMANERS PRODUCTION", 0, 480);
		if(Gdx.app.getType() == ApplicationType.Desktop) {
			font.draw(batch, "PRESS SPACE BAR TO START", 0, 460);
		} else if (Gdx.app.getType() == ApplicationType.Android) {
			font.draw(batch, "TOUCH SCREEN TO START", 0, 440);
		}
		batch.end();
		
		if(Gdx.app.getType() == ApplicationType.Desktop) {
			TitleScreenInput.Keyboard();
		} else if (Gdx.app.getType() == ApplicationType.Android) {
			TitleScreenInput.Touch();
		}
		
		cam.update();
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
