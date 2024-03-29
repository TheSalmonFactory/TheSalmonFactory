package com.me.thesalmonfactory.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.me.thesalmonfactory.helpers.GameContext;

public class DragObject extends  GameObject {
	
	protected static final int ROBOT_ID = 1;
	protected static final int SALMON_ID = 0;
	
	protected float m_LiveTimer;
	protected float m_DeadTiming;
	
	protected float m_Rotation;
	protected float m_TargetRotation;
	
	protected static final float DEFAULT_DEAD_TIME = 12;
	
	protected static final float DEFAULT_SPEED = 1750.0f;
	
	protected User m_Target;
	
	protected Vector2 m_Direction;
	protected Vector2 m_OldPosition;
	protected Vector2 m_TargetPos;
	protected Vector2 m_RealDirection;
	
	protected boolean m_GoingHor;
	
	protected float m_Speed;

	public DragObject(int x, int y, int tilesheetID) {
		super(x, y, tilesheetID);
		// TODO Auto-generated constructor stub
		m_Target = null;
		m_LiveTimer = 0;
		m_DeadTiming = DEFAULT_DEAD_TIME;
		
		m_Rotation = 0;
		m_TargetRotation = 0;
		
		m_Direction = new Vector2(0,0);
		m_RealDirection = new Vector2(0,0);
		
		m_GoingHor = false;
		
		m_OldPosition = new Vector2(x,y);
		
		m_TargetPos= new Vector2(x,y);
		
		m_Speed = DEFAULT_SPEED;
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

			m_Direction.x = m_Target.m_CurrentPosition.x - m_Position.x - GameContext.TILE_WIDTH;
			m_Direction.y = m_Target.m_CurrentPosition.y - m_Position.y - GameContext.TILE_WIDTH;
			
			float prevRotation = m_TargetRotation;
			m_Direction = m_Direction.nor();
			if(Math.abs(m_Direction.x) > Math.abs(m_Direction.y)) {
				if(m_Direction.x < 0) {
					m_Direction.x = -1;
					m_TargetRotation = 90;
				} 
				else {
					m_Direction.x = 1;
					m_TargetRotation = 270;
				}
				m_Direction.y = 0;
				m_GoingHor = true;
			}
			else {
				if(m_Direction.y < 0) {
					m_Direction.y = -1;
					m_TargetRotation = 180;
				} 
				else {
					m_Direction.y = 1;
					m_TargetRotation = 0;
				}
				m_Direction.x = 0;
				m_GoingHor = false;
			}
		
			m_OldPosition.x = m_Position.x;
			m_OldPosition.y = m_Position.y;
			m_RealDirection.x = m_Direction.x;
			m_Position.x = m_Target.m_CurrentPosition.x - GameContext.TILE_WIDTH / 2 ;
			m_Position.y = m_Target.m_CurrentPosition.y - GameContext.TILE_WIDTH / 2 ;
			
			Vector2 checkVecLen = new Vector2(m_OldPosition.x - m_Position.x,
					m_OldPosition.y - m_Position.y);
			if(checkVecLen.len() < 1) {
				m_TargetRotation = prevRotation;
			}
			
			if(Math.abs(m_Position.y - m_OldPosition.y) > GameContext.TILE_WIDTH / 2) {
				m_Position.y = m_OldPosition.y;
			}
			if(Math.abs(m_Position.x - m_OldPosition.x) > GameContext.TILE_WIDTH / 2) {
				m_Position.x = m_OldPosition.x;
			}
			
			if(m_Rotation < m_TargetRotation) {
				m_Rotation += 400 * context.GameTime;
			} 
			else {
				m_Rotation -= 400 * context.GameTime;
			}
			if(Math.abs(m_TargetRotation - m_Rotation) < 36 ) {
				m_Rotation = m_TargetRotation;
			}
		}
		
		m_LiveTimer += context.GameTime;
		if(m_LiveTimer > m_DeadTiming) {
			Dispose();
		}
	}
	
	public boolean Drag(User user) {
		if(IsPositionInRange((int)(user.m_CurrentPosition.x) + GameContext.m_OffsetX, 
						(int)user.m_CurrentPosition.y, 2.0f)) {
			m_TargetPos.x = m_Position.x;
			m_TargetPos.y = m_Position.y;
			m_OldPosition.x = m_Position.x;
			m_OldPosition.y = m_Position.y;
			m_Target = user;
			//Gdx.app.log("Touch", "touching something to drag!!!");
			return true;
		}
		return false;
	}
	
	public boolean UnsetUser(int userID) {
		if(m_Target != null && userID == m_Target.m_ID) {
			m_Target = null;
			return true;
		}
		return false;
	}
}
