package com.domaners.honeycomber;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class ScoreArea {
	
	ShapeRenderer bg;
	
	public ScoreArea() {
		
		bg.setColor(0, 0, 0, 0);
		bg.rect(0, Main.WORLD_HEIGHT - 200, Main.WORLD_WIDTH, 200);
		
	}
	
	public ShapeRenderer getScoreArea() {
		
		return bg;
		
	}
}
