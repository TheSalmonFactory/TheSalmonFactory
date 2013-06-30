package com.me.thesalmonfactory.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.me.thesalmonfactory.helpers.GameContext;

public class User extends Entity{
	
	public enum InputAction {
		NOTHING, LINE, MULTI_LINES, CIRCLE
	}
	
	public int m_ID;
	public boolean m_Active;
	
	private Vector2 m_StartPosition;
	public Vector2 m_CurrentPosition;
	private Vector2 m_CheckPosition;
	public Vector2 m_AveragePosition;
	private Vector2 m_Direction;
	
	private static final int MAX_CHECK_POSITIONS = 60;
	
	private Vector2[] m_CheckPositionArray;
	private int m_CurrentCheckPositionID;
	private int m_RealCheckPositionID;
	
	private static final int INITIAL_DIRECTION_DISTANCE_CHECK = 3;
	
	private static Texture m_UserTilesheet = null;
	
	public User(int x, int y, int id)
	{
		super(x, y, 0, 0);
		m_ID = id;
		m_Active = false;
		
		m_StartPosition = new Vector2(0,0);
		m_CurrentPosition = new Vector2(0,0);
		m_Direction = new Vector2(0,0);
		m_AveragePosition = new Vector2(0,0);
		m_CheckPosition = new Vector2(0,0);
		m_CheckPositionArray = new Vector2[MAX_CHECK_POSITIONS];
		for(int i = 0 ; i < MAX_CHECK_POSITIONS ; i++) {
			m_CheckPositionArray[i] = new Vector2(0,0);
		}
		m_CurrentCheckPositionID = 0;
		m_RealCheckPositionID = 0;
		
		if(m_UserTilesheet == null) { 
			m_UserTilesheet = new Texture(Gdx.files.internal("img/UserTilesheet.png"));
		}
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
		
		if(m_Active) {
			for(int i = 0 ; i < m_CurrentCheckPositionID ; ++i) {
				float u = ((m_ID/4) * 8) / 32.0f;
				float v = ((m_ID%4) * 8) / 32.0f;
				context.Batch.draw(m_UserTilesheet, m_CheckPositionArray[i].x, m_CheckPositionArray[i].y, 
						8, 8, u, v, u + 0.25f, v + 0.25f);
				
			}
		}
	}

	@Override
	public void Update(GameContext context) {
		// TODO Auto-generated method stub
		super.Update(context);
	}
	
	public InputAction UpdateCheck(GameContext context) {
		// TODO Auto-generated method stub
		super.Update(context);
		
		if(m_Active) {
			Vector2 lengthVector = new Vector2(  	m_CurrentPosition.x - m_StartPosition.x, 
													m_CurrentPosition.y - m_StartPosition.y);
			float length = lengthVector.len();
			if(m_Direction.x == 0 && m_Direction.y == 0 && 
					length > INITIAL_DIRECTION_DISTANCE_CHECK) {
				m_Direction = lengthVector;
			}
		}
		
		if(m_CurrentCheckPositionID == MAX_CHECK_POSITIONS) {
			for(int i = 0 ; i < MAX_CHECK_POSITIONS - 1 ; i++) {
				m_StartPosition.x = m_CheckPositionArray[i].x;
				m_StartPosition.y = m_CheckPositionArray[i].y;
				m_AveragePosition.x = m_StartPosition.x;
				m_AveragePosition.y = m_StartPosition.y;
				m_CheckPositionArray[i].x = m_CheckPositionArray[i+1].x;
				m_CheckPositionArray[i].y = m_CheckPositionArray[i+1].y;
			}
			--m_CurrentCheckPositionID;
			m_RealCheckPositionID = 0;
		}
		Vector2 lengthVector2 = new Vector2(  	m_CurrentPosition.x - m_CheckPosition.x, 
				m_CurrentPosition.y - m_CheckPosition.y);
		float length2 = lengthVector2.len();
		if(length2 > INITIAL_DIRECTION_DISTANCE_CHECK) {
			m_CheckPositionArray[m_CurrentCheckPositionID].x = m_CurrentPosition.x;
			m_CheckPositionArray[m_CurrentCheckPositionID].y = m_CurrentPosition.y;
			m_CheckPosition.x = m_CurrentPosition.x;
			m_CheckPosition.y = m_CurrentPosition.y;
			++m_CurrentCheckPositionID;
			++m_RealCheckPositionID;
		}
		
		if(m_RealCheckPositionID > 5) {
			Vector2 returnLengthVector = new Vector2(  	m_CurrentPosition.x - m_StartPosition.x, 
					m_CurrentPosition.y - m_StartPosition.y);
			float returnLength = returnLengthVector.len();
			if(returnLength < GameContext.TILE_WIDTH) {
				return Reset();
			}
		}
		return InputAction.NOTHING;
	}
	
	public void Record(int x, int y) { 
		m_CurrentPosition.x = x;
		m_CurrentPosition.y = y;
		m_AveragePosition.x = (m_AveragePosition.x + x) / 2;
		m_AveragePosition.y = (m_AveragePosition.y + y) / 2;
		
	}
	
	public void StartRecord(int x, int y) { 
		m_StartPosition.x = x;
		m_StartPosition.y = y;
		m_CurrentPosition.x = x;
		m_CurrentPosition.y = y;
		m_AveragePosition.x = x;
		m_AveragePosition.y = y;
		m_CheckPosition.x = x;
		m_CheckPosition.y = y;
		m_CurrentCheckPositionID = 0;
		m_RealCheckPositionID = 0;
		m_Active = true;
	}
	
	public InputAction StopRecord(int x, int y) {
		InputAction action = CheckForAction();
		
		m_Active = false;
		m_Direction.x = 0;
		m_Direction.y = 0;
		
		return action;
	}
	
	private InputAction CheckForAction() {
		//check for circle!
		if(m_CurrentCheckPositionID > 3) {
			float previousLength = 0;
			float currentLength = 0;
			float checkLength = GameContext.TILE_WIDTH * 0.35f;
			boolean checkCircle = true;
			for(int i = 0 ; i < m_CurrentCheckPositionID && i < MAX_CHECK_POSITIONS ; i++) {
				Vector2 checkLengthVec = new Vector2(m_CheckPositionArray[i].x - m_AveragePosition.x, 
						m_CheckPositionArray[i].y - m_AveragePosition.y);
				currentLength = checkLengthVec.len();
				if(i != 0) {
					if(Math.abs(currentLength - previousLength) > checkLength) {
						checkCircle = false;
						break;
					}
				} 
				else {
					checkLength = currentLength * 0.35f;
				}
				previousLength = currentLength;
				
			}
			if(checkCircle) {
				return InputAction.CIRCLE;
			}
		}
		//check for line
		Vector2 lengthVector = new Vector2(m_CurrentPosition.x -m_StartPosition.x, m_CurrentPosition.y - m_StartPosition.y);
		float length = lengthVector.len();
		if(length > GameContext.TILE_WIDTH * 0.75 && 
				length < GameContext.TILE_WIDTH * 1.25) {
			return InputAction.LINE;
		}
		//nothing happened
		return InputAction.NOTHING;
	}
	
	public InputAction Reset() {
		InputAction action = CheckForAction();
		
		m_Active = true;
		m_Direction.x = 0;
		m_Direction.y = 0;
		m_StartPosition.x = m_CurrentPosition.x;
		m_StartPosition.y = m_CurrentPosition.y;
		m_AveragePosition.x = m_CurrentPosition.x;
		m_AveragePosition.y = m_CurrentPosition.y;
		m_CheckPosition.x = m_CurrentPosition.x;
		m_CheckPosition.y = m_CurrentPosition.y;
		m_CurrentCheckPositionID = 0;
		m_RealCheckPositionID = 0 ;
		
		return action;
	}
}
