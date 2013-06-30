package com.me.thesalmonfactory.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;
import com.me.thesalmonfactory.Level;
import com.me.thesalmonfactory.helpers.GameContext;

public class DOSalmon extends  DragObject {
	
	private Sound m_MoveSound;
	private boolean m_MoveSoundPlaying;

	public DOSalmon(int x, int y) {
		super(x, y, SALMON_ID);
		// TODO Auto-generated constructor stub
		
		m_MoveSound = Gdx.audio.newSound(Gdx.files.internal("audio/SFX_SalmonMoves.wav"));
		m_MoveSoundPlaying = false;
	}
	
	@Override
	public void Initialize() {
		// TODO Auto-generated method stub
		super.Initialize();
	}

	@Override
	public void Dispose() {
		// TODO Auto-generated method stub
		super.Dispose();
		m_MoveSound.stop();
		m_MoveSoundPlaying = false;
		m_MoveSound.dispose();
	}

	@Override
	public void Draw(GameContext context) {
		// TODO Auto-generated method stub
		super.Draw(context);
		context.DrawEntity((int)m_Position.x, (int)m_Position.y, SALMON_ID, 0, 0, m_Rotation);
	}

	@Override
	public void Update(GameContext context) {
		// TODO Auto-generated method stub
		super.Update(context);
		Vector2 validation = Level.ValidateSalmonPosition(m_Position);
		//m_Position.x += validation.x;
		//m_Position.y += validation.y;
		if(validation.x != 0) {
			//Gdx.app.log("validation", "validation.x == " + validation.x);
			m_Position.x -= validation.x;
		}
		if(validation.y != 0) {
			//Gdx.app.log("validation", "validation.x == " + validation.x);
			m_Position.y -= validation.y;
		}
		
		if(m_Target != null) {
			if(!m_MoveSoundPlaying) {
				m_MoveSound.loop();
				m_MoveSoundPlaying = true;
			}
		} 
		else {
			m_MoveSound.stop();
			m_MoveSoundPlaying = false;
		}
	}
}
