package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;


public class MyGdxGame extends ApplicationAdapter {
	private Stage stage;

	@Override
	public void create () {
		stage = new Stage();
		stage.getViewport().setWorldSize(2048,1280);

		Image image = new Image(new Texture("badlogic.jpg"));
		image.setPosition(0,100);
		stage.addActor(image);

		WaterActor waterActor = new WaterActor(new Texture("badlogic.jpg"));
		waterActor.setPosition(300,100);
		stage.addActor(waterActor);

		BlurActor blurActor = new BlurActor(new Texture("badlogic.jpg"));
		blurActor.setPosition(600,100);
		stage.addActor(blurActor);

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
