package com.me.thesalmonfactory;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.me.thesalmonfactory.helpers.GameContext;

public class Level {
	
	LevelParser currentLvl;
	Texture m_BorderTexture;
	
	public Level() {
		currentLvl = new LevelParser(0);
		m_BorderTexture = new Texture(Gdx.files.internal("img/BorderRect.png"));
	}
	
	public void Dispose() {
		// TODO dispose stuff
		m_BorderTexture.dispose();
	}

	public void Draw(GameContext context) {
		for(int i = 0 ; i < LevelParser.matrixHeight ; i++)
		{
			for(int j = 0 ; j < LevelParser.matrixWidth ; j++)
			{
				context.DrawTile((int)(j * GameContext.TILE_WIDTH), (int)(i * GameContext.TILE_WIDTH), 
				currentLvl.mapMatrix[j][i][0]);
				context.DrawTile((int)(j * GameContext.TILE_WIDTH), (int)(i * GameContext.TILE_WIDTH), 
				currentLvl.mapMatrix[j][i][1]);
			}
		}
		
		for(Vector2 vec : LevelParser.WATER_LIST) { 
			context.Batch.draw(m_BorderTexture, vec.x, vec.y, GameContext.TILE_WIDTH, GameContext.TILE_WIDTH, 0, 0, 32, 32, false, false);
		}
	}

	public void Update(GameContext context) {
		// TODO update stuff
	}
	
	public static Vector2 ValidateRobotPosition(Vector2 pos) { 
		Vector2 validation = new Vector2(0,0);
		/*for(Vector2 vec : LevelParser.WATER_LIST) {
			if( validation.y == 0 && Math.abs(vec.y - pos.y) < GameContext.TILE_WIDTH) { 
				if(pos.y < vec.y) { 
					Gdx.app.log("p.y < v.y", "p.y == " + pos.y + " && v.y == " + vec.y + " && TW = " + GameContext.TILE_WIDTH);
					//validation.y = GameContext.TILE_WIDTH - pos.y - vec.y;
				}
				/*else {
					validation.y = pos.y - vec.y - GameContext.TILE_WIDTH;
				}
			}
		}*/
		return ValidatePosition(pos, validation);
	}
	
	public static Vector2 ValidateSalmonPosition(Vector2 pos) { 
		Vector2 validation = new Vector2(0,0);
		/*for(Vector2 vec : LevelParser.WATER_LIST) {
			if(validation.x == 0 && Math.abs(vec.x - pos.x) < GameContext.TILE_WIDTH) {
				validation.x = pos.x - vec.x;
			}
			if(validation.y == 0 && Math.abs(vec.y - pos.y) < GameContext.TILE_WIDTH) {
				validation.y = pos.y - vec.y;
			}
			if(validation.x != 0 && validation.y != 0) {
				return validation;
			}
		}*/
		return ValidatePosition(pos, validation);
	}
	
	public static Vector2 ValidatePosition(Vector2 pos, Vector2 validation) {
		if(pos.x < GameContext.m_OffsetX * 3 || 
				(pos.x > Gdx.app.getGraphics().getWidth() - GameContext.m_OffsetX * 3)) {
			validation.x = 0;
		}
		if(pos.y < GameContext.m_OffsetY * 3 || 
				(pos.y > Gdx.app.getGraphics().getHeight() - GameContext.m_OffsetY * 3)) {
			validation.y = 0;
		}
		return validation;
	}
}
