package com.me.thesalmonfactory.objects;

import com.me.thesalmonfactory.helpers.GameContext;

public class DragObject extends  GameObject {
	
	protected static final int ROBOT_ID = 1;
	protected static final int SALMON_ID = 0;

	public DragObject(int x, int y, int tilesheetID) {
		super(x, y, tilesheetID);
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
	}

	@Override
	public void Update(GameContext context) {
		// TODO Auto-generated method stub
		super.Update(context);	
	}
}
