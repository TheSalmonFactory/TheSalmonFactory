package com.me.thesalmonfactory.input;

import com.me.thesalmonfactory.*;
import com.badlogic.gdx.InputProcessor;

public class MainInputProcessor implements InputProcessor {
	   private TheSalmonFactory TheApp;
	
	   public MainInputProcessor(TheSalmonFactory application) {
		   TheApp = application;
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
	      return false;
	   }

	   @Override
	   public boolean touchUp (int x, int y, int pointer, int button) {
		  TheApp.AddCurrentTapPos((float)x, (float)y);
	      return false;
	   }

	   @Override
	   public boolean touchDragged (int x, int y, int pointer) {
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