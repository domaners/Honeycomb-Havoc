package com.domaners.honeycomber.views;

import static com.domaners.honeycomber.Honeycomber.*;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public interface ViewMode {

	OrthographicCamera cam = new OrthographicCamera();
	Viewport viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, cam);
	SpriteBatch batch = new SpriteBatch();
	BitmapFont font = new BitmapFont();
    	
	void render();
	void dispose();
	
}