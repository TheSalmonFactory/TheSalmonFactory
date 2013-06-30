package com.me.thesalmonfactory;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.me.thesalmonfactory.helpers.GameContext;

public class EndScreen implements ScreenInterface  {

	private int m_Timer;
	@SuppressWarnings("unused")
	private Random m_Random;
	private Texture m_Screen;
	
	public EndScreen() {
		m_Timer = 0;
		m_Random = new Random();
		m_Screen = new Texture(Gdx.files.internal("img/GameOver.png"));
	}
	
	@Override
	public void Start() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Draw(GameContext context) {
		// TODO Auto-generated method stub
		int max = 13 * 8;
		for(int i = 0 ; i < max ; ++i) {
			context.DrawTile( i % 13 * GameContext.TILE_WIDTH, i / 13 * GameContext.TILE_WIDTH, 12 + ((i / 13)%15) * 32);
		}
		context.Batch.draw(m_Screen, (Gdx.app.getGraphics().getWidth() - 512) / 2, (Gdx.app.getGraphics().getHeight() - 512) / 2);
	}

	@Override
	public void Update(GameContext context) {
		// TODO Auto-generated method stub
		++m_Timer;
		if(m_Timer > 250 || Gdx.input.isTouched()) {
			Gdx.app.exit();
		}
	}

}
