package com.me.thesalmonfactory;

import com.badlogic.gdx.Gdx;
import com.me.thesalmonfactory.helpers.GameContext;

public class Level {
	
	LevelParser currentLvl;
	
	public Level() {
		currentLvl = new LevelParser(0);
	}
	
	public void Dispose() {
		// TODO dispose stuff
	}

	public void Draw(GameContext context) {
		for(int i = 0 ; i < LevelParser.matrixHeight ; i++)
		{
			for(int j = 0 ; j < LevelParser.matrixWidth ; j++)
			{
				Gdx.app.log("Test", "ID == " + currentLvl.mapMatrix[j][i][0]);
				context.DrawTile((int)(j * GameContext.TILE_WIDTH), 
				(int)(i * GameContext.TILE_WIDTH), currentLvl.mapMatrix[j][i][0]);
				//context.DrawTile(j, i, currentLvl.mapMatrix[j][i][1]);
			}
		}
	}

	public void Update(GameContext context) {
		// TODO update stuff
	}
}
