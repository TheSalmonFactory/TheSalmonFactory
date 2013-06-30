package com.me.thesalmonfactory.objects;

import com.badlogic.gdx.Gdx;
import com.me.thesalmonfactory.helpers.GameContext;

public class DragObject extends  GameObject {
	
	protected static final int ROBOT_ID = 1;
	protected static final int SALMON_ID = 0;
	
	protected User m_Target;

	public DragObject(int x, int y, int tilesheetID) {
		super(x, y, tilesheetID);
		// TODO Auto-generated constructor stub
		m_Target = null;
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
	}

	@Override
	public void Update(GameContext context) {
		// TODO Auto-generated method stub
		super.Update(context);	
		if(m_Target != null) {
			m_Position.x = -GameContext.m_OffsetX + m_Target.m_CurrentPosition.x;
			m_Position.y = -GameContext.m_OffsetY + m_Target.m_CurrentPosition.y;
		}
	}
	
	public boolean Drag(User user) {
		if(m_Target == null && 
				IsPositionInRange((int)user.m_CurrentPosition.x, 
						Gdx.app.getGraphics().getHeight() - (int)user.m_CurrentPosition.y)) {
			m_Target = user;
			return true;
		}
		return false;
	}
	
	public boolean UnsetUser(int userID) {
		if( m_Target!= null && userID == m_Target.m_ID) {
			m_Target = null;
			return true;
		}
		return false;
	}
}
