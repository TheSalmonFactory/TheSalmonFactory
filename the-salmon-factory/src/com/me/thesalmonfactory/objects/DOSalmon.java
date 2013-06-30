package com.me.thesalmonfactory.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.me.thesalmonfactory.Level;
import com.me.thesalmonfactory.helpers.GameContext;

public class DOSalmon extends  DragObject {

	public DOSalmon(int x, int y) {
		super(x, y, SALMON_ID);
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
		context.DrawEntity((int)m_Position.x, (int)m_Position.y, SALMON_ID, 0, 0, m_Rotation);
	}

	@Override
	public void Update(GameContext context) {
		// TODO Auto-generated method stub
		super.Update(context);
		Vector2 validation = Level.ValidateSalmonPosition(m_Position);
		m_Position.x += validation.x;
		m_Position.y += validation.y;
	}
}
