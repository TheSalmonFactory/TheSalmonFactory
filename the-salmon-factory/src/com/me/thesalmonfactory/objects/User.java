package com.me.thesalmonfactory.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.me.thesalmonfactory.helpers.GameContext;

public class User extends Entity{
	
	public enum InputAction {
		NOTHING, LINE, MULTI_LINES, CIRCLE
	}
	
	public int m_ID;
	public boolean m_Active;
	
	private Vector2 m_StartPosition;
	private Vector2 m_CurrentPosition;
	private Vector2 m_Direction;
	
	private static final int INITIAL_DIRECTION_DISTANCE_CHECK = 5;
	
	public User(int x, int y, int id)
	{
		super(x, y, 0, 0);
		m_ID = id;
		m_Active = false;
		
		m_StartPosition = new Vector2(0,0);
		m_CurrentPosition = new Vector2(0,0);
		m_Direction = new Vector2(0,0);
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
		
		if(m_Active) {
			Vector2 lengthVector = new Vector2(  	m_CurrentPosition.x - m_StartPosition.x, 
													m_CurrentPosition.x - m_StartPosition.x);
			float length = lengthVector.len();
			if(m_Direction.x == 0 && m_Direction.y == 0 && 
					length > INITIAL_DIRECTION_DISTANCE_CHECK) {
				m_Direction = lengthVector;
			}
		}
	}
	
	public void Record(int x, int y) { 
		m_CurrentPosition.x = x;
		m_CurrentPosition.y = y;
	}
	
	public void StartRecord(int x, int y) { 
		m_StartPosition.x = x;
		m_StartPosition.y = y;
		m_Active = true;
	}
	
	public InputAction StopRecord(int x, int y) {
		InputAction action = CheckForAction(x, y);
		
		m_Active = false;
		m_Direction.x = 0;
		m_Direction.y = 0;
		
		return action;
	}
	
	private InputAction CheckForAction(int x, int y) {
		Vector2 lengthVector = new Vector2(m_CurrentPosition.x -m_StartPosition.x, m_CurrentPosition.y - m_StartPosition.x);
		float length = lengthVector.len();
		Gdx.app.log("Length", "Length == " + length);
		Gdx.app.log("Position Info", "m_CurrentPosition == " + m_CurrentPosition.x + "," + m_CurrentPosition.y + " && m_StartPosition == " 
				+ m_StartPosition.x + "," + m_StartPosition.y);
		if(length > GameContext.TILE_WIDTH * 0.75 && 
				length < GameContext.TILE_WIDTH * 1.25) {
			return InputAction.LINE;
		}
		return InputAction.NOTHING;
	}
}
