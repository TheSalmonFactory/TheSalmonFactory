package com.me.thesalmonfactory.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameContext {
	public float GameTime;
	public OrthographicCamera Camera;
	public SpriteBatch Batch;
	
	public static final int TILE_WIDTH = 32;
	public static final int HOR_TILES = 32;
	
	private Texture m_Spritesheet;
	
	public void Initialize()
	{
		m_Spritesheet = new Texture(Gdx.files.internal("img/tilesheet-32.png"));
	}
	
	public void Dispose()
	{
		Batch.dispose();
		m_Spritesheet.dispose();
	}
	
	public void DrawTile(int x, int y, int id)
	{
		Batch.draw(m_Spritesheet, x, y, (id % HOR_TILES) * TILE_WIDTH, 
				(id / HOR_TILES) * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
	}
}
