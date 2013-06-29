package com.me.thesalmonfactory.objects;

import com.me.thesalmonfactory.helpers.GameContext;

public class User extends Entity{
	
	public int m_ID;
	
	public User(int x, int y, int id)
	{
		super(x, y, 0, 0);
		m_ID = id;
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
