package com.me.thesalmonfactory;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.me.thesalmonfactory.helpers.GameContext;
import com.me.thesalmonfactory.input.*;

public class TheSalmonFactory implements ApplicationListener {
	   
   private enum ApplicationState {
	    BEGINSCREEN, GAME, ENDSCREEN
	}
   
   private ApplicationState m_State;
   private Game m_Game;
   private BeginScreen m_BeginScreen;
   private EndScreen m_EndScreen;
   
   private Player m_Player;
   
   public GameContext m_Context;
   
   private Texture m_ExitButton;
   
   private InputProcessorGame m_GameIP;
   
   private Vector2 m_ExitButtonPos;
   
   private Sound m_ButtonSound;
   
   @Override
   public void create() {
      //Create Context
      m_Context = new GameContext();
      m_Context.Initialize();
      m_Context.GameTime = 0.01f;
      
      // create the camera and the SpriteBatch
      m_Context.Camera = new OrthographicCamera();
      m_Context.Camera.setToOrtho(false, 1280, 720);
      m_Context.Batch = new SpriteBatch();
      
      //Create Screens
      m_Game = new Game();
      m_BeginScreen = new BeginScreen();
      m_EndScreen = new EndScreen();
      
      //Create InputProcessors ( catch input events )
      InputMultiplexer multiplexer = new InputMultiplexer();
      multiplexer.addProcessor(new InputProcessorUI());
      m_GameIP = new InputProcessorGame(m_Game); 
      multiplexer.addProcessor(m_GameIP);
      Gdx.input.setInputProcessor(multiplexer);
      
      //State of the game
      m_State = ApplicationState.BEGINSCREEN;
      m_BeginScreen.Start();
      
      //Player game
      m_Player = new Player();
      m_Player.Initialize();
      
      m_ExitButton = new Texture(Gdx.files.internal("img/ButtonExit.png"));
      
      m_ExitButtonPos = new Vector2(Gdx.app.getGraphics().getWidth() - GameContext.TILE_WIDTH, 
					   Gdx.app.getGraphics().getHeight() - GameContext.TILE_WIDTH - GameContext.m_OffsetY);
      
      m_ButtonSound = Gdx.audio.newSound(Gdx.files.internal("audio/SFX_Button.wav"));
   }

   @Override
   public void render() {
      // clear the screen with a dark blue color. The
      // arguments to glClearColor are the red, green
      // blue and alpha component in the range [0,1]
      // of the color to be used to clear the screen.
      Gdx.gl.glClearColor(0, 0, 0.2f, 1);
      Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
      m_Context.Batch.setProjectionMatrix(m_Context.Camera.combined);
      m_Context.Batch.begin();
      Draw();
      m_Context.Batch.end();
      // update
      Update();
   }
   
   @Override
   public void dispose() {
      // dispose of all the native resources
      m_Context.Dispose();
      m_Player.Dispose();
      m_Game.Dispose();
      m_BeginScreen.Dispose();
      m_EndScreen.Dispose();
      m_ExitButton.dispose();
      m_ButtonSound.dispose();
   }

   @Override
   public void resize(int width, int height) {
   }

   @Override
   public void pause() {
   }

   @Override
   public void resume() {
   }
   
   public void Update() {
	   switch(m_State) {
		   case GAME:
			   m_GameIP.Update(m_Context);
			   m_Game.Update(m_Context);
			   
			   float yCor = Gdx.app.getGraphics().getHeight() - Gdx.input.getY();
			   if(Gdx.input.isTouched() && Gdx.input.getX() > m_ExitButtonPos.x && 
					   Gdx.input.getX() < m_ExitButtonPos.x + GameContext.TILE_WIDTH && yCor > m_ExitButtonPos.y && 
					   yCor < m_ExitButtonPos.y + GameContext.TILE_WIDTH) {
				   m_EndScreen.Start();
				   m_Game.stop();
				   m_State = ApplicationState.ENDSCREEN;
				   m_ButtonSound.play();
			   }
			   
			   break;
		   case BEGINSCREEN:
			   m_BeginScreen.Update(m_Context);
			   if(Gdx.input.isTouched()) {
				   m_BeginScreen.stop();
				   m_Game.Start();
				   m_State = ApplicationState.GAME;
			   }
			   break;
		   case ENDSCREEN:
			   m_EndScreen.Update(m_Context);
			   break;
	   }
	   m_Player.Update(m_Context);
   }
   
   public void Draw() {
	   switch(m_State) {
		   case GAME:
			   m_Game.Draw(m_Context);
			   m_GameIP.Draw(m_Context);
			   m_Context.Batch.draw(m_ExitButton,m_ExitButtonPos.x, m_ExitButtonPos.y , 
					   GameContext.TILE_WIDTH, GameContext.TILE_WIDTH, 0, 0, 32, 32, false, false);
			   break;
		   case BEGINSCREEN:
			   m_BeginScreen.Draw(m_Context);
			   break;
		   case ENDSCREEN:
			   m_EndScreen.Draw(m_Context);
			   break;
	   }
	   m_Player.Draw(m_Context);
   }
}
