package com.corb.moveboxandshow;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Calvin on 22/03/2017.
 */

public class Main extends Game {
    //Used by all screens
    public SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();
        Assets.load();
        setScreen(new GameScreen(this));
    }

    @Override
    public void render() {
        GL20 gl = Gdx.gl;
        gl.glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
