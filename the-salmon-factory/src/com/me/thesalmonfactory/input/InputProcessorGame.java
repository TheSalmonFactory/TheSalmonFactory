package com.me.thesalmonfactory.input;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.me.thesalmonfactory.Game;
import com.me.thesalmonfactory.helpers.CircleFeedback;
import com.me.thesalmonfactory.helpers.GameContext;
import com.me.thesalmonfactory.objects.User;

//Catch all the InputEvents related to GameInformation 
public class InputProcessorGame implements InputProcessor {
	private Game m_Game;
	private User[] m_Users;
	private List<CircleFeedback> m_Feedbacks;
	
	private static final int MAX_USERS = 10;
	
	public InputProcessorGame(Game game) {
		m_Game = game;
		m_Users = new User[MAX_USERS];
		for(int i = 0; i < MAX_USERS ; ++i) {
			m_Users[i] = new User(0,0,i);
			m_Users[i].Initialize();
		}
		m_Feedbacks = new ArrayList<CircleFeedback>();
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
	  y = Gdx.app.getGraphics().getHeight() - y;
	  m_Game.ProcessTouchDown(x, y, pointer);
	  //Gdx.app.log("TouchDown", "x == " + x + " && y == " + y + " && pointer == " + pointer + " && button == " + button);
	  if(pointer < MAX_USERS && pointer > -1) {
		  m_Users[pointer].StartRecord(x, y);
	  }
      return false;
   }

   @Override
   public boolean touchUp (int x, int y, int pointer, int button) {
	  y = Gdx.app.getGraphics().getHeight() - y;
	  m_Game.ProcessTouchUp(x, y, pointer);
	  //Gdx.app.log("TouchUp", "x == " + x + " && y == " + y + " && pointer == " + pointer + " && button == " + button);
	  if(pointer < MAX_USERS && pointer > -1) {
		  CheckForAction(pointer, m_Users[pointer].StopRecord(x, y));
	  }
      return false;
   }

   @Override
   public boolean touchDragged (int x, int y, int pointer) {
	  y = Gdx.app.getGraphics().getHeight() - y;
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
   
   public void Update(GameContext context) {
	   for( User user : m_Users) {
		   CheckForAction(user.m_ID, user.UpdateCheck(context));
	   }
	   for( CircleFeedback feedback : m_Feedbacks) { 
		   if(feedback.Update(context)) {
			   m_Feedbacks.remove(feedback);
			   break;
		   }
	   }
   }
   
   public void Draw(GameContext context) {
	   for( User user : m_Users) {
		   user.Draw(context);
	   }
	   for( CircleFeedback feedback : m_Feedbacks) { 
		   feedback.Draw(context);
	   }
   }
   
   public void CheckForAction(int id, User.InputAction action) {
	   switch(action) {
		  case LINE:
			  m_Game.ProcessActionLine((int)m_Users[id].m_AveragePosition.x, (int)m_Users[id].m_AveragePosition.y, id);
			  Gdx.app.log("Record", "Line Recorded!");
			  break;
		  case MULTI_LINES:
			  m_Game.ProcessActionMultiLines((int)m_Users[id].m_AveragePosition.x, (int)m_Users[id].m_AveragePosition.y, id);
			  Gdx.app.log("Record", "MultiLines Recorded!");
			  break;
		  case CIRCLE:
			  m_Game.ProcessActionCircle((int)m_Users[id].m_AveragePosition.x, (int)m_Users[id].m_AveragePosition.y, id);
			  Gdx.app.log("Record", "Circle Recorded!");
			  break;
		  default:
			  // else ... DO NOTHING!
			  break;
	  }
   }
   
   public void UpdateFeedbacks(int x, int y, int id) {
	   for(CircleFeedback feedback : m_Feedbacks) { 
		   if(feedback.Feed(id)) { 
			   return;
		   }
	   }
	   m_Feedbacks.add(new CircleFeedback(x,y,5.0f,id));
   }
}