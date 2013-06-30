package com.me.thesalmonfactory.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class CircleFeedback {
	
	private int m_X, m_Y;
	public int m_UserID;
	private float m_TargetSeconds;
	private float m_CurrentSeconds;
	
	private static final int SCALE_VALUE = 2;
	private static final float MAX_CIRCLE_DELAY = 0.25f;
	
	private static Texture m_Texture = null;
	private float m_DelayTime;
	
	public CircleFeedback(int x, int y, float seconds, int userID) { 
		m_CurrentSeconds = 0;
		m_TargetSeconds = seconds;
		m_X = x - GameContext.m_OffsetX / 2;
		m_Y = y - GameContext.m_OffsetY;
		m_UserID = userID;
		
		m_DelayTime = 0;
		
		if(m_Texture == null) { 
			m_Texture = new Texture(Gdx.files.internal("img/Circle.png"));
		}
	}

	public void Dispose() {
		// Do Nothing...
	}

	public void Draw(GameContext context) {
		float scale = m_CurrentSeconds / m_TargetSeconds * SCALE_VALUE;
		// Draw Texture and use the scale value && m_X && m_Y
		context.Batch.draw(m_Texture, m_X, m_Y, GameContext.TILE_WIDTH/2, GameContext.TILE_WIDTH/2, GameContext.TILE_WIDTH , 
				GameContext.TILE_WIDTH, scale, scale, 0, 0, 0, 32, 32, false, false);
	}

	public boolean Update(GameContext context) {
		m_CurrentSeconds += context.GameTime;
		m_DelayTime += context.GameTime;
		if(m_DelayTime > MAX_CIRCLE_DELAY || 
				m_CurrentSeconds > m_TargetSeconds)
		{
			Dispose();
			return true;
		}
		return false;
	}
	
	public boolean Feed(int userID) {
		if(userID == m_UserID) {
			m_DelayTime = 0;
			return true;
		}
		return false;
	}
}