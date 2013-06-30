package com.me.thesalmonfactory.objects;

import com.badlogic.gdx.math.Vector2;
import com.me.thesalmonfactory.helpers.GameContext;

public class Entity implements EntityInterface{
    public enum EntityState {
	    IDLE, MOVE, DEAD, TOUCH_DOWN, 
	    TOUCH_DRAG, TOUCH_UP
	}
   
	public Vector2 m_Position;
	protected Vector2 m_Dimensions;
	protected User m_User;
	
	public EntityState m_State;
	protected EntityState m_PreviousState;
	
	protected boolean m_CanMove;
	
	public Entity(int x, int y, int width, int height)
	{
		m_Position = new Vector2(x,y);
		m_Dimensions = new Vector2(width, height);
		
		m_User = null;
		
		m_State = EntityState.IDLE;
		m_PreviousState = m_State;
		
		m_CanMove = false;
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
		if(m_State == EntityState.TOUCH_DOWN) {
			if(m_PreviousState == m_State) {
				m_State = EntityState.TOUCH_DRAG;
			}
			else {
				m_PreviousState = m_State;
			}
		}
		else
		{
			m_PreviousState = m_State;
		}
	}
	
	/*public boolean SetTarget(int x, int y, int id)
	{
		if(m_CanMove) {
			if(m_User != null) {
				m_User.Dispose();
			}
			m_User = new User(x, y, id);
			m_PreviousState = m_State;
			m_State = EntityState.TOUCH_DOWN;
			return true;
		}
		return false;
	}
	
	public boolean RemoveTarget(int id)
	{
		if(m_CanMove) {
			if(id == m_User.m_ID) {
				m_User.Dispose();
				m_State = EntityState.IDLE;
				return true;
			}
		}
		return false;
	}
	*/
	protected void EnableMovement() {
		m_CanMove = true;
	}
	
	public boolean IsPositionInRange(int x, int y, float scalar) {
		//Vector2 pos = GameContext.GetCorrectPosition(new Vector2(x, y));
		//Vector2 pos = new Vector2(x, y);
		//return 	m_Position.x < pos.x && m_Position.x + m_Dimensions.x > pos.x &&
		//		m_Position.y < pos.y && m_Position.y + m_Dimensions.y > pos.y;
		Vector2 lengthVec = new Vector2(x - m_Position.x, y - m_Position.y);
		return lengthVec.len() < GameContext.TILE_WIDTH * scalar;
	}
	
	public boolean ExcecuteTouchAction(int x, int y, int id) {
		if(IsPositionInRange(x, y, 1.0f)) {
			return ExcecuteFunction(x, y, id);
		}
		return false;
	}
	
	//override this function for specialized Touch Input Interaction 
	protected boolean ExcecuteFunction(int x, int y, int id) {
		//return SetTarget(x, y, id);
		return false;
	}
}
