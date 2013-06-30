package com.me.thesalmonfactory.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class GameContext {
	public float GameTime;
	public OrthographicCamera Camera;
	public SpriteBatch Batch;
	
	public static int TILE_WIDTH = 32;
	public static final int HOR_TILES = 32;
	
	private Texture m_Spritesheet;
	public static int m_OffsetX;
	public static int m_OffsetY;
	
	public void Initialize()
	{
		m_Spritesheet = new Texture(Gdx.files.internal("img/tilesheet-32.png"));
		TILE_WIDTH = (Gdx.app.getGraphics().getHeight() - 50 ) / 8;
		
		m_OffsetX = ( Gdx.app.getGraphics().getWidth() - 13 * TILE_WIDTH ) / 2;
		m_OffsetY = ( Gdx.app.getGraphics().getHeight() - 8 * TILE_WIDTH ) / 2;
	}
	
	public void Dispose()
	{
		Batch.dispose();
		m_Spritesheet.dispose();
	}
	
	public void DrawTile(int x, int y, int id)
	{
		//float uvExtra = 32.0f / 1024.0f;
		//float u = () / 1024.0f;
		//float v = ((id / HOR_TILES) * 32) / 1024.0f;
		//Batch.draw(m_Spritesheet, x, y, TILE_WIDTH, TILE_WIDTH, 
			//	u, v, u + uvExtra, v + uvExtra, false, true);
		Vector2 pos = GetCorrectPosition(new Vector2(x, y));
		Batch.draw(m_Spritesheet, (int)pos.x, (int)pos.y, TILE_WIDTH, TILE_WIDTH, 
				(id % HOR_TILES) * 32, (id / HOR_TILES) * 32, 32, 32, false, false);
	}
	
	public void DrawEntity(int x, int y, int tileID, int animationID, int animationFrame, float rotation)
	{
		//float uvExtra = 32.0f / 1024.0f;
		//float u = () / 1024.0f;
		//float v = ((id / HOR_TILES) * 32) / 1024.0f;
		//Batch.draw(m_Spritesheet, x, y, TILE_WIDTH, TILE_WIDTH, 
			//	u, v, u + uvExtra, v + uvExtra, false, true);
		//Batch.draw(m_Spritesheet, m_OffsetX + x, m_OffsetY + y, TILE_WIDTH, TILE_WIDTH, 
		//		(tileID % HOR_TILES) * 32, (tileID / HOR_TILES) * 32, 32, 32, false, false);
		Batch.draw(m_Spritesheet, m_OffsetX + x, m_OffsetY + y, 
				TILE_WIDTH / 2, TILE_WIDTH / 2, TILE_WIDTH, TILE_WIDTH, 1.0f, 1.0f, 
				rotation, (tileID % HOR_TILES) * 32, (tileID / HOR_TILES) * 32, 32, 32, false, false);
	}
	
	public static Vector2 GetPosition(int idX, int idY) {
		return new Vector2(idX * TILE_WIDTH, idY * TILE_WIDTH);
	}
	
	public static Vector2 GetCorrectPosition(Vector2 pos) {
		pos.x = pos.x + m_OffsetX; 
		pos.y = Gdx.app.getGraphics().getHeight() - pos.y - Gdx.app.getGraphics().getHeight() / 7 - m_OffsetY;
		return pos;
	}
	
	public static Vector2 SnapPosition(Vector2 pos) {
		pos.x = SnapValue(pos.x);
		pos.y = SnapValue(pos.y);
		return pos;
	}
	
	public static float SnapValue(float value) {
		int rest = (int)value % TILE_WIDTH;
		if(rest > TILE_WIDTH / 2) {
			value += TILE_WIDTH - rest;
		}
		else { 
			value -= rest;
		} 
		return value;
	}
}
