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
    private float Max_Rad = 0f;

    public InkActor(Texture texture){
        this.texture = texture;
        shaderProgram = new ShaderProgram(Gdx.files.internal("default.vert")
                ,Gdx.files.internal("ink.frag"));


        if (shaderProgram.isCompiled() == false)
            throw new IllegalArgumentException("Error compiling shader: " + shaderProgram.getLog());
        Max_Rad  = (float) Math.sqrt(Math.pow(texture.getWidth(),2)+Math.pow(texture.getHeight(),2))/2;

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


        float x = getX();
        float y = getY();
        float width = texture.getWidth();
        float height = texture.getHeight();
        float rad = time * 30;
        if(rad>=Max_Rad){
            time = 0;
            rad = 0;
        }
        shaderProgram.setUniformf("m_fSpotLightRadius",rad);
        shaderProgram.setUniformf("orginPos",new Vector2(width/2,height/2));

        shaderProgram.setUniformf("resolution", new Vector2(width,height));

        batch.draw(texture,x,y,width,height);


        batch.setShader(shader);
    }

    @Override
    public boolean remove() {
        shaderProgram.dispose();
        return super.remove();

    }
}
