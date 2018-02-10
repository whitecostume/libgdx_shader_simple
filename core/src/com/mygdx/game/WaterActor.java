package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by white on 2018/2/10.
 */

public class WaterActor extends Actor {

    private ShaderProgram shaderProgram;
    private Texture texture;
    private float time = 0f;

    public WaterActor(Texture texture){
        this.texture = texture;
        shaderProgram = new ShaderProgram(Gdx.files.internal("water.vert")
                ,Gdx.files.internal("water.frag"));

        if (shaderProgram.isCompiled() == false)
            throw new IllegalArgumentException("Error compiling shader: " + shaderProgram.getLog());


    }

    @Override
    public void act(float delta) {
        super.act(delta);
        time += delta;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        ShaderProgram shader = batch.getShader();
        batch.setShader(shaderProgram);

        shaderProgram.setUniformf("time",time);

        float x = getX();
        float y = getY();

        shaderProgram.setUniformf("resolution",
                new Vector2(texture.getWidth(),(texture.getHeight())));
        batch.draw(texture,x,y,texture.getWidth(),texture.getHeight());


        batch.setShader(shader);
    }

    @Override
    public boolean remove() {
        shaderProgram.dispose();
        return super.remove();

    }
}
