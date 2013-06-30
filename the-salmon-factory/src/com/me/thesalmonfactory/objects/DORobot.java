package com.me.thesalmonfactory.objects;

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
		context.DrawEntity((int)m_Position.x, (int)m_Position.y, ROBOT_ID, 0, 0);
	}

	@Override
	public void Update(GameContext context) {
		// TODO Auto-generated method stub
		super.Update(context);	
	}
}
