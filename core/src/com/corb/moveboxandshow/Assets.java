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

    public static Animation playerStationary;
    public static Animation playerStationaryLeft; //Player is stationary but facing left.
    public static Animation playerStationaryRight;     //Player is stationary but facing right.
    public static Animation playerWalkLeft;
    public static Animation playerWalkRight;
    public static Animation playerJump;
    public static Animation playerFall;


    public static void load() {
        TextureAtlas atlas = new TextureAtlas("data/textures/blocks/blocks.pack"); //To increase Performance you should only have one atlas.
        blackBox = new Animation(0.2f, new TextureRegion(atlas.findRegion("orginal")));

        blackBox.setPlayMode(Animation.PlayMode.LOOP);

        //Player

        atlas.dispose(); //I should only have 1 atlas but this I've using 2 for making testing easier
        atlas = new TextureAtlas("data/textureAtlas/player/MinerMan/minerAnimations.pack");

        playerStationary = new Animation(0.2f, new TextureRegion(atlas.findRegion("Miner_Stationary")));
        playerStationaryLeft = new Animation(0.2f, new TextureRegion(atlas.findRegion("Miner_Stationary_Left")));
        playerStationaryRight = new Animation(0.2f, new TextureRegion(atlas.findRegion("Miner_Stationary_Right")));


        playerWalkLeft = new Animation(0.2f,
                new TextureRegion(atlas.findRegion("Miner_Walking_left00")),
                new TextureRegion(atlas.findRegion("Miner_Walking_left01")),
                new TextureRegion(atlas.findRegion("Miner_Walking_left02")),
                new TextureRegion(atlas.findRegion("Miner_Walking_left03")),
                new TextureRegion(atlas.findRegion("Miner_Walking_left04")),
                new TextureRegion(atlas.findRegion("Miner_Walking_left05")),
                new TextureRegion(atlas.findRegion("Miner_Walking_left06")),
                new TextureRegion(atlas.findRegion("Miner_Walking_left07")),
                new TextureRegion(atlas.findRegion("Miner_Walking_left08")),
                new TextureRegion(atlas.findRegion("Miner_Walking_left09")),
                new TextureRegion(atlas.findRegion("Miner_Walking_left10")),
                new TextureRegion(atlas.findRegion("Miner_Walking_left11"))
        );

        playerWalkRight = new Animation(0.2f,
                new TextureRegion(atlas.findRegion("Miner_Walking_Right00")),
                new TextureRegion(atlas.findRegion("Miner_Walking_Right01")),
                new TextureRegion(atlas.findRegion("Miner_Walking_Right02")),
                new TextureRegion(atlas.findRegion("Miner_Walking_Right03")),
                new TextureRegion(atlas.findRegion("Miner_Walking_Right04")),
                new TextureRegion(atlas.findRegion("Miner_Walking_Right05")),
                new TextureRegion(atlas.findRegion("Miner_Walking_Right06")),
                new TextureRegion(atlas.findRegion("Miner_Walking_Right07")),
                new TextureRegion(atlas.findRegion("Miner_Walking_Right08")),
                new TextureRegion(atlas.findRegion("Miner_Walking_Right09")),
                new TextureRegion(atlas.findRegion("Miner_Walking_Right10")),
                new TextureRegion(atlas.findRegion("Miner_Walking_Right11"))
        );

        playerJump = new Animation(0.2f, new TextureRegion(atlas.findRegion("Miner_Stationary")));
        playerFall = new Animation(0.2f, new TextureRegion(atlas.findRegion("Miner_Stationary")));

        playerStationary.setPlayMode(Animation.PlayMode.LOOP);
        playerStationaryLeft.setPlayMode(Animation.PlayMode.LOOP);
        playerStationaryRight.setPlayMode(Animation.PlayMode.LOOP);

        playerWalkLeft.setPlayMode(Animation.PlayMode.LOOP);
        playerWalkRight.setPlayMode(Animation.PlayMode.LOOP);
        playerJump.setPlayMode(Animation.PlayMode.LOOP);
        playerFall.setPlayMode(Animation.PlayMode.LOOP);

    }
}
