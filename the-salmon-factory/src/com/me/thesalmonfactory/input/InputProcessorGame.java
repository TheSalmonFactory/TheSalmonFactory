package com.me.thesalmonfactory.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.me.thesalmonfactory.Game;
import com.me.thesalmonfactory.objects.User;

//Catch all the InputEvents related to GameInformation 
public class InputProcessorGame implements InputProcessor {
	private Game m_Game;
	private User[] m_Users;
	
	private static final int MAX_USERS = 10;
	
	public InputProcessorGame(Game game) {
		m_Game = game;
		m_Users = new User[MAX_USERS];
		for(int i = 0; i < MAX_USERS ; ++i) {
			m_Users[i] = new User(0,0,i);
			m_Users[i].Initialize();
		}
	}
	
   @Override
   public boolean keyDown (int keycode) {
      return false;
   }

   @Override
   public boolean keyUp (int keycode) {
      return false;
   }

   @Override
   public boolean keyTyped (char character) {
      return false;
   }

   @Override
   public boolean touchDown (int x, int y, int pointer, int button) {
	  m_Game.ProcessTouchDown(x, y, pointer);
	  //Gdx.app.log("TouchDown", "x == " + x + " && y == " + y + " && pointer == " + pointer + " && button == " + button);
	  if(pointer < MAX_USERS && pointer > -1) {
		  m_Users[pointer].StartRecord(x, y);
	  }
      return false;
   }

   @Override
   public boolean touchUp (int x, int y, int pointer, int button) {
	  m_Game.ProcessTouchUp(x, y, pointer);
	  //Gdx.app.log("TouchUp", "x == " + x + " && y == " + y + " && pointer == " + pointer + " && button == " + button);
	  if(pointer < MAX_USERS && pointer > -1) {
		  switch(m_Users[pointer].StopRecord(x, y)) {
			  case LINE:
				  m_Game.ProcessActionLine(x, y);
				  Gdx.app.log("Line Recorded @ ", "x == " + x + " && y == " + y + " && pointer == " + pointer + " && button == " + button);
				  break;
			  case MULTI_LINES:
				  m_Game.ProcessActionMultiLines(x, y);
				  break;
			  case CIRCLE:
				  m_Game.ProcessActionCircle(x, y);
				  break;
			  default:
				  // else ... DO NOTHING!
				  break;
		  }
	  }
      return false;
   }

   @Override
   public boolean touchDragged (int x, int y, int pointer) {
	  m_Game.ProcessTouchDown(x, y, pointer);
	  //Gdx.app.log("TouchDrag", "x == " + x + " && y == " + y + " && pointer == " + pointer);
	  if(pointer < MAX_USERS && pointer > -1) {
		  m_Users[pointer].Record(x, y);
	  }
      return false;
   }

   public boolean touchMoved (int x, int y) {
      return false;
   }

   @Override
   public boolean scrolled (int amount) {
      return false;
   }

   @Override
   public boolean mouseMoved(int screenX, int screenY) {
	   // TODO Auto-generated method stub
	   return false;
   }
}