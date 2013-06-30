package com.me.thesalmonfactory;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.me.thesalmonfactory.helpers.GameContext;

public class BeginScreen implements ScreenInterface  {

	private Music m_Music; 
	private Texture m_Screen;
	@SuppressWarnings("unused")
	private Random m_Random;
	
	public BeginScreen() {
		m_Music = Gdx.audio.newMusic(Gdx.files.internal("audio/song2game.mp3"));
		m_Music.setLooping(true);
		m_Screen = new Texture(Gdx.files.internal("img/TapToStart.png"));
		m_Random = new Random();
	}
	
	@Override
	public void Start() {
		// TODO Auto-generated method stub
		m_Music.play();
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		m_Music.stop();
	}

	@Override
	public void Dispose() {
		// TODO Auto-generated method stub
		m_Music.dispose();
		m_Screen.dispose();
	}

	@Override
	public void Draw(GameContext context) {
		// TODO Auto-generated method stub
		int max = 13 * 8;
		for(int i = 0 ; i < max ; ++i) {
			context.DrawTile( i % 13 * GameContext.TILE_WIDTH, i / 13 * GameContext.TILE_WIDTH, 6 + ((i / 13)%4) * 32);
		}
		context.Batch.draw(m_Screen, (Gdx.app.getGraphics().getWidth() - 512) / 2, (Gdx.app.getGraphics().getHeight() - 512) / 2);
	}

	@Override
	public void Update(GameContext context) {
		// TODO Auto-generated method stub
		
	}

}
