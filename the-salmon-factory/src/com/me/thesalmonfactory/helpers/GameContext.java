package com.me.thesalmonfactory.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameContext {
	public float GameTime;
	public OrthographicCamera Camera;
	public SpriteBatch Batch;
	
	public static int TILE_WIDTH = 32;
	public static final int HOR_TILES = 32;
	
	private Texture m_Spritesheet;
	private int m_OffsetX, m_OffsetY;
	
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
		Batch.draw(m_Spritesheet, x + m_OffsetX, 
				Gdx.app.getGraphics().getHeight() - y - Gdx.app.getGraphics().getHeight() / 7 - m_OffsetY, TILE_WIDTH, TILE_WIDTH, 
				(id % HOR_TILES) * 32, (id / HOR_TILES) * 32, 32, 32, false, false);
	}
}
