package com.me.thesalmonfactory;

import com.badlogic.gdx.Gdx;
import com.me.thesalmonfactory.helpers.GameContext;
import com.me.thesalmonfactory.helpers.ObjectManager;
import com.me.thesalmonfactory.objects.DORobot;
import com.me.thesalmonfactory.objects.DOSalmon;

public class Game implements ScreenInterface {
	
	public ObjectManager m_ObjectManager;
	public Level m_CurrentLevel;

	public Game() {
		m_ObjectManager = new ObjectManager();
		m_CurrentLevel = new Level();
	}
	
	@Override
	public void Start() {
		// TODO Auto-generated method stub
	}

	@Override
	public void stop() {
		//Clear ObjectManager when game stops!
		m_ObjectManager.Clear();
	}

	@Override
	public void Dispose() {
		m_ObjectManager.Dispose();
	}

	@Override
	public void Draw(GameContext context) {
		m_ObjectManager.Draw(context);
		m_CurrentLevel.Draw(context);
	}

	@Override
	public void Update(GameContext context) {
		m_ObjectManager.Update(context);
		m_CurrentLevel.Update(context);
	}
	
	public void ProcessTouchDown(int x, int y, int userID) {
		m_ObjectManager.ProcessTouchDown(x, y, userID);
	}
	
	public void ProcessTouchUp(int x, int y, int userID) {
		m_ObjectManager.ProcessTouchUp(x, y, userID);
	}
	
	public void ProcessTouchDrag(int x, int y, int userID) {
		m_ObjectManager.ProcessTouchDrag(x, y, userID);
	}
	
	public void ProcessActionLine(int x, int y, int id) {
		
	}
	
	public void ProcessActionMultiLines(int x, int y, int id) {
		
	}
	
	public void ProcessActionCircle(int x, int y, int id) {
		if(x < 50 && y < 50) {
			CreateNewSalmon(x,y);
		} 
		else if(x > Gdx.app.getGraphics().getWidth() - 50 &&
			y > Gdx.app.getGraphics().getHeight() - 50) {
			CreateNewRobot(x,y);
		}
	}
	
	public void CreateNewSalmon(int x, int y) {
		DOSalmon salmon = new DOSalmon(x + 100, y + 100);
		m_ObjectManager.AddObject(salmon);
	}
	
	public void CreateNewRobot(int x, int y) {
		DORobot robot = new DORobot(x - 100, y - 100);
		m_ObjectManager.AddObject(robot);
	}
}
