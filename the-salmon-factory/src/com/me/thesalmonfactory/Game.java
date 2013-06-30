package com.me.thesalmonfactory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.me.thesalmonfactory.helpers.GameContext;
import com.me.thesalmonfactory.helpers.ObjectManager;
import com.me.thesalmonfactory.objects.DORobot;
import com.me.thesalmonfactory.objects.DOSalmon;

public class Game implements ScreenInterface {
	
	public static ObjectManager m_ObjectManager;
	public static Level m_CurrentLevel;
	private Music m_Music; 
	private Sound m_RotationSound;
	private int m_RotationDelayTime;
	
	private Texture m_LevelNotFinishedMessage;
	
	public static int level = 0;

	public Game() {
		m_ObjectManager = new ObjectManager();
		m_CurrentLevel = new Level(level);
		m_Music = Gdx.audio.newMusic(Gdx.files.internal("audio/song1game.mp3"));
		m_Music.setLooping(true);
		m_RotationSound =  Gdx.audio.newSound(Gdx.files.internal("audio/SFX_Rotation.mp3"));
		m_RotationDelayTime = 0;
		
		m_LevelNotFinishedMessage = new Texture(Gdx.files.internal("img/LevelNotFinishedLevel.png"));
	}
	
	@Override
	public void Start() {
		// TODO Auto-generated method stub
		m_Music.play();
	}

	@Override
	public void stop() {
		//Clear ObjectManager when game stops!
		m_ObjectManager.Clear();
		m_Music.stop();
	}

	@Override
	public void Dispose() {
		m_ObjectManager.Dispose();
		m_LevelNotFinishedMessage.dispose();
	}

	@Override
	public void Draw(GameContext context) {
		m_CurrentLevel.Draw(context);
		m_ObjectManager.Draw(context);
		if(level > 0) {
			context.Batch.draw(m_LevelNotFinishedMessage, 100, -350);
		}
	}

	@Override
	public void Update(GameContext context) {
		m_ObjectManager.Update(context);
		m_CurrentLevel.Update(context);
		++m_RotationDelayTime;
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
		if(m_RotationDelayTime > 30) {
			m_RotationSound.play();
			m_RotationDelayTime = 0;
		}
	}
	
	public void CheckForNewGameObject(int x, int y, int id) {
		Vector2 lengthVec = new Vector2(x - LevelParser.SALMON_ROT.x, y - LevelParser.SALMON_ROT.y);
		if( lengthVec.len() < GameContext.TILE_WIDTH * 2 ) {
			CreateNewSalmon((int)LevelParser.SALMON_SPAWN.x, (int)LevelParser.SALMON_SPAWN.y);
			return;
		}
		lengthVec = new Vector2(x - LevelParser.ROBOT_ROT.x, y - LevelParser.ROBOT_ROT.y);
		if( lengthVec.len() < GameContext.TILE_WIDTH * 2 ) {
			CreateNewRobot((int)LevelParser.ROBOT_SPAWN.x, (int)LevelParser.ROBOT_SPAWN.y);
		}
	}
	
	public void CreateNewSalmon(int x, int y) {
		DOSalmon salmon = new DOSalmon(x + GameContext.m_OffsetX, y);
		m_ObjectManager.AddObject(salmon);
	}
	
	public void CreateNewRobot(int x, int y) {
		DORobot robot = new DORobot(x + GameContext.m_OffsetX, y);
		m_ObjectManager.AddObject(robot);
	}
	
	public static void LoadNewLevel() {
		++level;
		m_ObjectManager.Clear();
		m_CurrentLevel.Dispose();
		m_CurrentLevel = new Level(level);
	}
}
