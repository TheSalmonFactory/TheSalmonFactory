package com.me.thesalmonfactory.input;

//import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.me.thesalmonfactory.Game;

//Catch all the InputEvents related to GameInformation 
public class InputProcessorGame implements InputProcessor {
	private Game m_Game;
	
	public InputProcessorGame(Game game) {
		m_Game = game;
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
      return false;
   }

   @Override
   public boolean touchUp (int x, int y, int pointer, int button) {
	  m_Game.ProcessTouchUp(x, y, pointer);
	  //Gdx.app.log("TouchUp", "x == " + x + " && y == " + y + " && pointer == " + pointer + " && button == " + button);
      return false;
   }

   @Override
   public boolean touchDragged (int x, int y, int pointer) {
	  m_Game.ProcessTouchDown(x, y, pointer);
	  //Gdx.app.log("TouchDrag", "x == " + x + " && y == " + y + " && pointer == " + pointer);
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