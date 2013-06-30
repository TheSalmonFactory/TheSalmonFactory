package com.me.thesalmonfactory;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.me.thesalmonfactory.helpers.GameContext;

public class Level {
	
	LevelParser currentLvl;
	Texture m_BorderTexture;
	Texture m_BorderColRect;
	
	public Level() {
		currentLvl = new LevelParser(0);
		m_BorderTexture = new Texture(Gdx.files.internal("img/BorderRect.png"));
		m_BorderColRect = new Texture(Gdx.files.internal("img/BorderRectCol.png"));
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
				context.DrawTile((int)(j * GameContext.TILE_WIDTH), (int)(i * GameContext.TILE_WIDTH), 
				currentLvl.mapMatrix[j][i][2]);
			}
		}
		
		for(Vector2 vec : LevelParser.WATER_LIST) { 
			context.Batch.draw(m_BorderTexture, vec.x, vec.y, GameContext.TILE_WIDTH, GameContext.TILE_WIDTH, 0, 0, 32, 32, false, false);
		}
		
		for(Vector2 vec : LevelParser.FISH_ALLOW_LIST) { 
			context.Batch.draw(m_BorderColRect, vec.x, vec.y, GameContext.TILE_WIDTH, GameContext.TILE_WIDTH, 0, 0, 32, 32, false, false);
		}
	}

	public void Update(GameContext context) {
		// TODO update stuff
	}
	
	public static Vector2 ValidateRobotPosition(Vector2 pos) { 
		Vector2 validation = new Vector2(0,0);
		float widthCheck = GameContext.TILE_WIDTH * 0.5f;
		for(Vector2 vec : LevelParser.WATER_LIST) {
			Vector2 testVecY = new Vector2(0, vec.y - pos.y);
			Vector2 testVecX = new Vector2(vec.x - pos.x, 0);
			if(( validation.y == 0 || validation.x == 0)&& testVecX.len() < widthCheck && testVecY.len() < widthCheck) {
				if(testVecY.len() > widthCheck / 2) {
					Gdx.app.log("p.y < v.y", "p.y == " + pos.y + " && v.y == " + vec.y + " && TW = " + GameContext.TILE_WIDTH);
					if(pos.y > vec.y) { 
						validation.y = pos.y - vec.y - GameContext.TILE_WIDTH;
					}
					else {
						validation.y = pos.y - vec.y + GameContext.TILE_WIDTH;
					}
				}
				if(testVecX.len() > widthCheck / 2) {
					if(pos.x > vec.x) { 
						validation.x = pos.x - vec.x - GameContext.TILE_WIDTH;
					}
					else {
						validation.x =  pos.x - vec.x + GameContext.TILE_WIDTH;
					}
				}
			}
		}
		return ValidatePosition(pos, validation);
	}
	
	public static Vector2 ValidateSalmonPosition(Vector2 pos) { 
		Vector2 validation = new Vector2(0,0);
		float widthCheck = GameContext.TILE_WIDTH * 0.4f;
		for(Vector2 vec : LevelParser.FISH_ALLOW_LIST) {
			Vector2 testVecY = new Vector2(0, vec.y - pos.y);
			Vector2 testVecX = new Vector2(vec.x - pos.x, 0);
			if(( validation.y == 0 || validation.x == 0)&& testVecX.len() < widthCheck && testVecY.len() < widthCheck) {
				if(testVecY.len() > widthCheck / 2) {
					Gdx.app.log("p.y < v.y", "p.y == " + pos.y + " && v.y == " + vec.y + " && TW = " + GameContext.TILE_WIDTH);
					if(pos.y > vec.y) { 
						validation.y = pos.y - vec.y - GameContext.TILE_WIDTH;
					}
					else {
						validation.y = pos.y - vec.y + GameContext.TILE_WIDTH;
					}
				}
				if(testVecX.len() > widthCheck / 2) {
					if(pos.x > vec.x) { 
						validation.x = pos.x - vec.x - GameContext.TILE_WIDTH;
					}
					else {
						validation.x =  pos.x - vec.x + GameContext.TILE_WIDTH;
					}
				}
			}
		}
		return ValidatePosition(pos, validation);
	}
	
	public static Vector2 ValidatePosition(Vector2 pos, Vector2 validation) {
		if(validation.x == 0) {
			if(pos.x < GameContext.m_OffsetX) {
				validation.x -= GameContext.m_OffsetX - pos.x; 
			}
			else if (pos.x > 13 * GameContext.TILE_WIDTH) {
				validation.x -= 13 * GameContext.TILE_WIDTH - pos.x;
			}
		}
		if(validation.y == 0) {
			Gdx.app.log("p.y < v.y", "pos.y == " + pos.y);
			if(pos.y < GameContext.m_OffsetY) {
				validation.y -= GameContext.m_OffsetY - pos.y; 
			}
			else if (pos.y > 8 * GameContext.TILE_WIDTH - GameContext.m_OffsetY * 2 ) {
				validation.y -= 8 * GameContext.TILE_WIDTH - GameContext.m_OffsetY * 2 - pos.y;
			}
		}
		return ValidateCommonPosition(pos, validation);
	}
	
	public static Vector2 ValidateCommonPosition(Vector2 pos, Vector2 validation) { 
		float widthCheck = GameContext.TILE_WIDTH * 0.5f;
		for(Vector2 vec : LevelParser.COMMON_BLOCK_LIST) {
			Vector2 testVecY = new Vector2(0, vec.y - pos.y);
			Vector2 testVecX = new Vector2(vec.x - pos.x, 0);
			if(testVecX.len() < widthCheck && testVecY.len() < widthCheck) {
				if(validation.y == 0  && testVecY.len() > widthCheck / 2) {
					if(pos.y > vec.y) { 
						validation.y = pos.y - vec.y - GameContext.TILE_WIDTH;
					}
					else {
						validation.y = pos.y - vec.y + GameContext.TILE_WIDTH;
					}
				}
				if(validation.x == 0 && testVecX.len() > widthCheck / 2) {
					if(pos.x > vec.x) { 
						validation.x = pos.x - vec.x - GameContext.TILE_WIDTH;
					}
					else {
						validation.x =  pos.x - vec.x + GameContext.TILE_WIDTH;
					}
				}
			}
		}
		return validation;
	}
}
