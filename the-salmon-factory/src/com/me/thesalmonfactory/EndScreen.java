package com.me.thesalmonfactory;

import com.badlogic.gdx.Gdx;
import com.me.thesalmonfactory.helpers.GameContext;

public class EndScreen implements ScreenInterface  {

	private int m_Timer;
	
	public EndScreen() {
		m_Timer = 0;
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
	}

	@Override
	public void Update(GameContext context) {
		// TODO Auto-generated method stub
		++m_Timer;
		if(m_Timer > 100) {
			Gdx.app.exit();
		}
	}

}
