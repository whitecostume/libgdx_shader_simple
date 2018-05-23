package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;


public class MyGdxGame extends ApplicationAdapter {
	private Stage stage;

	@Override
	public void create () {
		stage = new Stage(new ScalingViewport(Scaling.stretch, 2048,1280));

		Image image = new Image(new Texture("badlogic.jpg"));
		image.setPosition(10,400);
		stage.addActor(image);

		WaterActor waterActor = new WaterActor(new Texture("badlogic.jpg"));
		waterActor.setPosition(300,400);
		stage.addActor(waterActor);

		BlurActor blurActor = new BlurActor(new Texture("badlogic.jpg"));
		blurActor.setPosition(600,400);
		stage.addActor(blurActor);

		GrayActor grayActor = new GrayActor(new Texture("badlogic.jpg"));
		grayActor.setPosition(900,400);
		stage.addActor(grayActor);

		EmbossActor embossActor = new EmbossActor(new Texture("badlogic.jpg"));
		embossActor.setPosition(1200,400);
		stage.addActor(embossActor);

		GlassActor glassActor = new GlassActor(new Texture("badlogic.jpg"));
		glassActor.setPosition(10,100);
		stage.addActor(glassActor);

		InkActor inkActor = new InkActor(new Texture("badlogic.jpg"));
		inkActor.setPosition(300,100);
		stage.addActor(inkActor);

		LightActor lightActor = new LightActor(new Texture("badlogic.jpg"));
		lightActor.setPosition(600,100);
		stage.addActor(lightActor);

		OutLineActor outLineActor = new OutLineActor(new Texture("Idle.png"));
		outLineActor.setPosition(10,700);
		stage.addActor(outLineActor);

		ShadowActor shadowActor = new ShadowActor(new Texture("Idle.png"));
		shadowActor.setPosition(300,700);
		stage.addActor(shadowActor);

		DissolveActor dissolveActor = new DissolveActor(new Texture("badlogic.jpg")
				,new Texture("noisetexture.png"));
		dissolveActor.setPosition(600,700);
		stage.addActor(dissolveActor);

	}
	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 1, 1);
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
