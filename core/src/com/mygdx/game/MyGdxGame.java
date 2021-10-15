package com.mygdx.game;

import states.GameStateManager;
import states.menu;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
	
    public static final int HEIGHT = 800;
    public static final int WIDTH = 480;
    public static final String TITLE = "Car Game";
    private SpriteBatch batch;
    private GameStateManager gsm;
    
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		Gdx.gl.glClearColor(0, 250, 250, 1);
		gsm.push(new menu(gsm));
	}

	@Override
	public void render () {		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);	
		gsm.Update(Gdx.graphics.getDeltaTime());
		gsm.Render(batch);
	}
	
	@Override
	public void dispose () {
		batch.dispose();		
	}
}
