package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;


public class MyGdxGame extends ApplicationAdapter {
	private Stage stage;

	@Override
	public void create () {


		stage = new Stage();
		stage.getViewport().setWorldSize(1280,720);

		WaterActor waterActor = new WaterActor(new Texture("badlogic.jpg"));
		waterActor.setPosition(100,100);
		stage.addActor(waterActor);

	}
	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act();
		stage.draw();


	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		stage.getViewport().update(width,height);
	}

	@Override
	public void dispose () {
		stage.dispose();
	}
}
