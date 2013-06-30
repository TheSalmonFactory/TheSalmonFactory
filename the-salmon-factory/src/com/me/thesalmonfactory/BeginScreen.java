package com.me.thesalmonfactory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.me.thesalmonfactory.helpers.GameContext;

public class BeginScreen implements ScreenInterface  {

	private Music m_Music; 
	private Texture m_Screen;
	
	public BeginScreen() {
		m_Music = Gdx.audio.newMusic(Gdx.files.internal("audio/song2game.mp3"));
		m_Music.setLooping(true);
		m_Screen = new Texture(Gdx.files.internal("img/TapToStart.png"));
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
	}

	@Override
	public void Draw(GameContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Update(GameContext context) {
		// TODO Auto-generated method stub
		
	}

}
