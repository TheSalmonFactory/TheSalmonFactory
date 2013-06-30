package com.me.thesalmonfactory.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;
import com.me.thesalmonfactory.Level;
import com.me.thesalmonfactory.helpers.GameContext;

public class DORobot extends  DragObject {

	private static Sound ROBOT_POP_SOUND;
	private static Sound ROBOT_PUSH_SOUND;
	
	private Sound m_MoveSound;
	private boolean m_MoveSoundPlaying;
	
	public DORobot(int x, int y) {
		super(x, y, ROBOT_ID);
		// TODO Auto-generated constructor stub
		if(ROBOT_POP_SOUND == null) {
			ROBOT_POP_SOUND = Gdx.audio.newSound(Gdx.files.internal("audio/SFX_RobotPop.mp3"));
			ROBOT_PUSH_SOUND = Gdx.audio.newSound(Gdx.files.internal("audio/SFX_RobotDies.mp3"));
		}
		
		ROBOT_POP_SOUND.play();
		
		m_MoveSound = Gdx.audio.newSound(Gdx.files.internal("audio/SFX_RobotMoves.mp3"));
		m_MoveSoundPlaying = false;
		
		m_DeadTiming *= 1.5f;
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
		ROBOT_PUSH_SOUND.play();
	}

	@Override
	public void Draw(GameContext context) {
		// TODO Auto-generated method stub
		super.Draw(context);
		context.DrawEntity((int)m_Position.x, (int)m_Position.y, ROBOT_ID, 0, 0, m_Rotation);
	}

	@Override
	public void Update(GameContext context) {
		// TODO Auto-generated method stub
		super.Update(context);	
		Vector2 validation = Level.ValidateRobotPosition(m_Position);
		/*if(validation.y != 0) {
			m_Position.y = m_OldPosition.y;//GameContext.SnapValue(m_OldPosition.y);
		}
		if(validation.y != 0) {
			m_Position.x = m_OldPosition.x;//GameContext.SnapValue(m_OldPosition.x);
		}*/
		if(validation.x != 0) {
			//Gdx.app.log("validation", "validation.x == " + validation.x);
			m_Position.x -= validation.x;
		}
		if(validation.y != 0) {
			//Gdx.app.log("validation", "validation.x == " + validation.x);
			m_Position.y -= validation.y;
		}
		//m_Position.y += validation.y;
		
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
