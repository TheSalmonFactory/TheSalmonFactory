package com.me.thesalmonfactory;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

//import com.me.thesalmonfactory.input.*;

public class TheSalmonFactory implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture HelloTexture;
	private Vector2[] TapPositions;
	private int CurrentTapPosition;
	
	@Override
	public void create() {		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera(1, h/w);
		batch = new SpriteBatch();
		
		HelloTexture = new Texture(Gdx.files.internal("droplet.png"));
		TapPositions = new Vector2[50];
		for (int i = 0 ; i < 50 ; i++) {
			TapPositions[i] = new Vector2(0,0);
		}
		CurrentTapPosition = 0;
		
		//add MainInputProcessor (dirty & quick)
		//Gdx.input.setInputProcessor(new MainInputProcessor(this));
	}

	@Override
	public void dispose() {
		batch.dispose();
		HelloTexture.dispose();
	}

	@Override
	public void render() {		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		for(int i=0; i<CurrentTapPosition; i++){
            batch.draw(HelloTexture, TapPositions[i].x, TapPositions[i].y);
		}
        batch.draw(HelloTexture, 50, 50);
		batch.end();
		
		if(Gdx.input.isTouched()) {
		      AddCurrentTapPos(Gdx.input.getX(), Gdx.input.getY());
		   }
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
	
	public void AddCurrentTapPos(float x, float y) {
		if(CurrentTapPosition < 50) {
			TapPositions[CurrentTapPosition].x = x;
			TapPositions[CurrentTapPosition].y = y;
			++CurrentTapPosition;
		}
	}
}
