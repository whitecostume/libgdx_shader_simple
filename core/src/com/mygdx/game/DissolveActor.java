package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * 溶解Actor
 */

public class DissolveActor extends Actor {
    private ShaderProgram shaderProgram;
    private Texture texture,noiseTex;
    private float time = 0f;
    private float Max_Rad = 1.5f;

    public DissolveActor(Texture texture,Texture noiseTex){
        this.texture = texture;

//        噪声图
        this.noiseTex = noiseTex;
        shaderProgram = new ShaderProgram(Gdx.files.internal("default.vert")
                ,Gdx.files.internal("dissolve.frag"));


        if (shaderProgram.isCompiled() == false)
            throw new IllegalArgumentException("Error compiling shader: " + shaderProgram.getLog());


    }

    @Override
    public void act(float delta) {
        super.act(delta);
        time += (0.008);
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

        texture.bind(0);
        noiseTex.bind(1);

        shaderProgram.setUniformi("u_texture",0);
        shaderProgram.setUniformi("u_noise",1);
        shaderProgram.setUniformf("time",time);
        batch.draw(texture,x,y,width,height);


        texture.bind(0);






        batch.setShader(shader);
    }

    @Override
    public boolean remove() {
        shaderProgram.dispose();
        return super.remove();

    }
}
