package com.domaners.honeycomber.views;

import static com.domaners.honeycomber.Main.WORLD_HEIGHT;
import static com.domaners.honeycomber.Main.WORLD_WIDTH;
import static com.domaners.honeycomber.GraphicsUtils.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.TimeUtils;
import com.domaners.honeycomber.characters.BackgroundElement;
import com.domaners.honeycomber.input.TitleScreenInput;

public class TitleScreen implements ViewMode {

public long gameScore;
private Sprite logo = new Sprite(new Texture(Gdx.files.internal("logo.png")));
private BackgroundElement[] bg = new BackgroundElement[2];

	public TitleScreen() {
		
		bg[0] = new BackgroundElement(0, 0);
		bg[1] = new BackgroundElement(bg[0].getWidth(), 0);
		
		// bg[1] = new Sprite(new Texture(Gdx.files.internal("Sky.png")));
		cam.setToOrtho(false, WORLD_WIDTH, WORLD_HEIGHT);
	
	}
	
	@Override
	public void render() {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		for(BackgroundElement b : bg) {
			if(b.getX() <= (0 - b.getWidth())) {
				b.setX(b.getWidth() - 1);
			} else if (TimeUtils.timeSinceMillis(b.getAnimateTime()) > b.getMovementSpeed()) { 
				b.setX(b.getX() - 1);
				b.setAnimateTime(TimeUtils.millis());
			}
			batch.draw(b.getSprite(), b.getX(), b.getY());
		}
		batch.draw(logo, centreAlignX(logo.getRegionWidth()), centreAlignY(logo.getRegionHeight()));
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
