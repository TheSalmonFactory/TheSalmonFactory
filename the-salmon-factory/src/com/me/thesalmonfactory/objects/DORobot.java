package com.me.thesalmonfactory.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.me.thesalmonfactory.Level;
import com.me.thesalmonfactory.helpers.GameContext;

public class DORobot extends  DragObject {

	public DORobot(int x, int y) {
		super(x, y, ROBOT_ID);
		// TODO Auto-generated constructor stub
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
	}
}
