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
	
	public void Initialize()
	{
		m_Spritesheet = new Texture(Gdx.files.internal("img/tilesheet-32.png"));
		TILE_WIDTH = (Gdx.app.getGraphics().getHeight() - 50 ) / 8;
	}
	
	public void Dispose()
	{
		Batch.dispose();
		m_Spritesheet.dispose();
	}
	
	public void DrawTile(int x, int y, int id)
	{
		float uvExtra = 32.0f / 1024.0f;
		float u = ((id % HOR_TILES) * TILE_WIDTH) / 1024.0f;
		float v = ((id / HOR_TILES) * TILE_WIDTH) / 1024.0f;
		Batch.draw(m_Spritesheet, x, y, TILE_WIDTH, TILE_WIDTH, 
				u, v, u + uvExtra, v + uvExtra);
	}
}
