package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class LightActor extends Actor {
    private ShaderProgram shaderProgram;
    private Texture texture;

    public LightActor(Texture texture){
        this.texture = texture;
        shaderProgram = new ShaderProgram(Gdx.files.internal("default.vert")
                ,Gdx.files.internal("light.frag"));


        if (shaderProgram.isCompiled() == false)
            throw new IllegalArgumentException("Error compiling shader: " + shaderProgram.getLog());

    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {

        ShaderProgram shader = batch.getShader();
        batch.setShader(shaderProgram);


        float x = getX();
        float y = getY();
        float width = texture.getWidth();
        float height = texture.getHeight();

        shaderProgram.setUniformf("u_lightPosition",new Vector2(0.5f,0.5f));

        shaderProgram.setUniformf("u_lightColor", 1.0f,1.0f,1.0f,1.0f);

        shaderProgram.setUniformf("u_lightRange",0.5f);

        batch.draw(texture,x,y,width,height);


        batch.setShader(shader);
    }

    @Override
    public boolean remove() {
        shaderProgram.dispose();
        return super.remove();

    }
}
