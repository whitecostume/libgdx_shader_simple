package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;


/**
 * 墨水Actor
 */
public class InkActor extends Actor {
    private ShaderProgram shaderProgram;
    private Texture texture;
    private float time = 0f;
    private float Max_Rad = 1f;

    public InkActor(Texture texture){
        this.texture = texture;
        shaderProgram = new ShaderProgram(Gdx.files.internal("default.vert")
                ,Gdx.files.internal("ink.frag"));


        if (shaderProgram.isCompiled() == false)
            throw new IllegalArgumentException("Error compiling shader: " + shaderProgram.getLog());

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        time += (delta/3f);
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {

        ShaderProgram shader = batch.getShader();
        batch.setShader(shaderProgram);


        float x = getX();
        float y = getY();
        float width = texture.getWidth();
        float height = texture.getHeight();
        if(time>=Max_Rad){
            time = 0;
        }
        shaderProgram.setUniformf("u_lightPosition",new Vector2(0.5f,0.5f));

        shaderProgram.setUniformf("u_lightColor", .0f,.0f,.0f,1.0f);

        shaderProgram.setUniformf("u_lightRange",time);

        batch.draw(texture,x,y,width,height);


        batch.setShader(shader);
    }

    @Override
    public boolean remove() {
        shaderProgram.dispose();
        return super.remove();

    }
}
