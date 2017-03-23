package com.corb.moveboxandshow;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Calvin on 22/03/2017.
 */

public class Assets {

    public static final int W_WIDTH = 480;
    public static final int W_HEIGHT = 320;
    public static final int PPM = 50;

    public static Animation blackBox;

    public static void load(){
        TextureAtlas atlas = new TextureAtlas("data/textures/blocks/blocks.pack");
        blackBox = new Animation(0.2f, new TextureRegion( atlas.findRegion("orginal")));

        blackBox.setPlayMode(Animation.PlayMode.LOOP);

    }
}
