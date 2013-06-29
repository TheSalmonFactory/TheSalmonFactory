package com.me.thesalmonfactory.objects;

import com.badlogic.gdx.math.Vector2;
import com.me.thesalmonfactory.helpers.GameContext;

public class Entity implements EntityInterface{

	protected Vector2 m_Position;
	
	public Entity(int x, int y)
	{
		m_Position = new Vector2(x,y);
	}
	
	@Override
	public void Initialize() {
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
		
	}

}
