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
	
	protected static final float DEFAULT_DEAD_TIME = 5;
	
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
			if(m_Direction.len() > GameContext.TILE_WIDTH ) {
				Vector2 lengthVecCheck = new Vector2(m_TargetPos.x - m_Position.x, m_TargetPos.y - m_Position.y);
				if(lengthVecCheck.len() < GameContext.TILE_WIDTH) {
					m_Direction = m_Direction.nor();
					if(Math.abs(m_Direction.x) > Math.abs(m_Direction.y)) {
						if(m_Direction.x < 0) {
							m_Direction.x = -1;
							m_Rotation = 90;
						} 
						else {
							m_Direction.x = 1;
							m_Rotation = 270;
						}
						m_Direction.y = 0;
						m_GoingHor = true;
					}
					else {
						if(m_Direction.y < 0) {
							m_Direction.y = -1;
							m_Rotation = 180;
						} 
						else {
							m_Direction.y = 1;
							m_Rotation = 0;
						}
						m_Direction.x = 0;
						m_GoingHor = false;
					}
					m_RealDirection.x = m_Direction.x;
					m_RealDirection.y = m_Direction.y;
					m_TargetPos.x = m_Position.x + m_Direction.x * GameContext.TILE_WIDTH;
					m_TargetPos.y = m_Position.y + m_Direction.y * GameContext.TILE_WIDTH;
					m_TargetPos = GameContext.SnapPosition(m_TargetPos);
				//m_Rotation = (float)Math.atan2(m_RealDirection.x, m_RealDirection.y) * 2.0f * (float)Math.PI;
				//Gdx.app.log("Rotation", " == " + m_Rotation);
					
				}
				m_Position.x += m_RealDirection.x * context.GameTime * m_Speed;
				m_Position.y += m_RealDirection.y * context.GameTime * m_Speed;
				
				if(m_GoingHor) {
					m_Position.y = GameContext.SnapValue(m_Position.y);
				}
				else {
					m_Position.x = GameContext.SnapValue(m_Position.x);
				}
			}
		}
		
		m_LiveTimer += context.GameTime;
		if(m_LiveTimer > m_DeadTiming) {
			m_State = EntityState.DEAD;
		}
	}
	
	public boolean Drag(User user) {
		if(m_Target == null && 
				IsPositionInRange((int)(user.m_CurrentPosition.x), 
						Gdx.app.getGraphics().getHeight() - (int)user.m_CurrentPosition.y - GameContext.m_OffsetY)) {
			m_TargetPos.x = m_Position.x;
			m_TargetPos.y = m_Position.y;
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
