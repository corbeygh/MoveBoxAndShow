package com.corb.moveboxandshow;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Calvin on 22/03/2017.
 */

public class Assets {

    public static final int W_WIDTH = 480;
    public static final int W_HEIGHT = 320;
    public static final int PPM = 50;

//    public static Animation blackBox;
    public static Animation stoneBlock;
    public static Animation skyBlock;
    public static Animation minedBlock;
    public static Animation grassBlock;
    public static Animation dirtBlock;
    public static Animation cooperBlock;
    public static Animation tinBlock;

    public static Animation playerStationary;
    public static Animation playerStationaryLeft; //Player is stationary but facing left.
    public static Animation playerStationaryRight;     //Player is stationary but facing right.
    public static Animation playerWalkLeft;
    public static Animation playerWalkRight;
    public static Animation playerJump;
    public static Animation playerFall;
    public static Animation playerMiningLeft;
    public static Animation playerMiningRight;

    //-------------------------------------------------//
    //------------------SHOP BLOCKS--------------------//
    //-------------------------------------------------//
    public static Animation shopBlock_00; //Numbers represent Row then Col index
    public static Animation shopBlock_01;
    public static Animation shopBlock_02;
    public static Animation shopBlock_03;
    public static Animation shopBlock_10;
    public static Animation shopBlock_11;
    public static Animation shopBlock_12;
    public static Animation shopBlock_13;


    public static void load() {
        TextureAtlas atlas = new TextureAtlas("devAssets/output/Blocks.pack"); //To increase Performance you should only have one atlas.
        stoneBlock = new Animation(0.2f, new TextureRegion(atlas.findRegion("StoneBlock")));
        skyBlock = new Animation(0.2f, new TextureRegion(atlas.findRegion("SkyBlock")));
        minedBlock = new Animation(0.2f, new TextureRegion(atlas.findRegion("MinedBlock")));
        grassBlock = new Animation(0.2f, new TextureRegion(atlas.findRegion("GrassBlock")));
        dirtBlock = new Animation(0.2f, new TextureRegion(atlas.findRegion("DirtBlock")));
        cooperBlock = new Animation(0.2f, new TextureRegion(atlas.findRegion("CooperBlock")));
        tinBlock = new Animation(0.2f, new TextureRegion(atlas.findRegion("TinBlock")));

        loadShopBlocks(atlas);



        stoneBlock.setPlayMode(Animation.PlayMode.LOOP);
        skyBlock.setPlayMode(Animation.PlayMode.LOOP);
        minedBlock.setPlayMode(Animation.PlayMode.LOOP);
        grassBlock.setPlayMode(Animation.PlayMode.LOOP);
        dirtBlock.setPlayMode(Animation.PlayMode.LOOP);
        cooperBlock.setPlayMode(Animation.PlayMode.LOOP);
        tinBlock.setPlayMode(Animation.PlayMode.LOOP);
        //Player

//        atlas.dispose(); //I should only have 1 atlas but this I've using 2 for making testing easier
        atlas = new TextureAtlas("data/textureAtlas/player/MinerMan/minerAnimations.pack");
        loadPlayer(atlas);


    }

    private static void loadShopBlocks(TextureAtlas atlas){





        shopBlock_00 = new Animation(0.2f, new TextureRegion(atlas.findRegion("Shop00")));
        shopBlock_01 = new Animation(0.2f, new TextureRegion(atlas.findRegion("Shop01")));
        shopBlock_02 = new Animation(0.2f, new TextureRegion(atlas.findRegion("Shop02")));
        shopBlock_03 = new Animation(0.2f, new TextureRegion(atlas.findRegion("Shop03")));
        shopBlock_10 = new Animation(0.2f, new TextureRegion(atlas.findRegion("Shop04")));
        shopBlock_11 = new Animation(0.2f, new TextureRegion(atlas.findRegion("Shop05")));
        shopBlock_12 = new Animation(0.2f, new TextureRegion(atlas.findRegion("Shop06")));
        shopBlock_13 = new Animation(0.2f, new TextureRegion(atlas.findRegion("Shop07")));

        shopBlock_00.setPlayMode(Animation.PlayMode.LOOP);
        shopBlock_01.setPlayMode(Animation.PlayMode.LOOP);
        shopBlock_02.setPlayMode(Animation.PlayMode.LOOP);
        shopBlock_03.setPlayMode(Animation.PlayMode.LOOP);
        shopBlock_10.setPlayMode(Animation.PlayMode.LOOP);
        shopBlock_11.setPlayMode(Animation.PlayMode.LOOP);
        shopBlock_12.setPlayMode(Animation.PlayMode.LOOP);
        shopBlock_13.setPlayMode(Animation.PlayMode.LOOP);
    }

    private static void loadPlayer(TextureAtlas atlas){
        playerStationary = new Animation(0.2f, new TextureRegion(atlas.findRegion("Miner_Stationary")));
        playerStationaryLeft = new Animation(0.2f, new TextureRegion(atlas.findRegion("Miner_Stationary_Left")));
        playerStationaryRight = new Animation(0.2f, new TextureRegion(atlas.findRegion("Miner_Stationary_Right")));


        playerWalkLeft = new Animation(0.2f,
                new TextureRegion(atlas.findRegion("Miner_Walking_Left00")),
                new TextureRegion(atlas.findRegion("Miner_Walking_Left01")),
                new TextureRegion(atlas.findRegion("Miner_Walking_Left02")),
                new TextureRegion(atlas.findRegion("Miner_Walking_Left03")),
                new TextureRegion(atlas.findRegion("Miner_Walking_Left04")),
                new TextureRegion(atlas.findRegion("Miner_Walking_Left05")),
                new TextureRegion(atlas.findRegion("Miner_Walking_Left06")),
                new TextureRegion(atlas.findRegion("Miner_Walking_Left07")),
                new TextureRegion(atlas.findRegion("Miner_Walking_Left08")),
                new TextureRegion(atlas.findRegion("Miner_Walking_Left09")),
                new TextureRegion(atlas.findRegion("Miner_Walking_Left10")),
                new TextureRegion(atlas.findRegion("Miner_Walking_Left11"))
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

        playerMiningLeft = new Animation(0.2f,
                new TextureRegion(atlas.findRegion("Miner_Mining_Left0")),
                new TextureRegion(atlas.findRegion("Miner_Mining_Left1")),
                new TextureRegion(atlas.findRegion("Miner_Mining_Left2")),
                new TextureRegion(atlas.findRegion("Miner_Mining_Left3"))

        );

        playerMiningRight = new Animation(0.2f,
                new TextureRegion(atlas.findRegion("Miner_Mining_Right0")),
                new TextureRegion(atlas.findRegion("Miner_Mining_Right1")),
                new TextureRegion(atlas.findRegion("Miner_Mining_Right2")),
                new TextureRegion(atlas.findRegion("Miner_Mining_Right3"))

        );

        playerStationary.setPlayMode(Animation.PlayMode.LOOP);
        playerStationaryLeft.setPlayMode(Animation.PlayMode.LOOP);
        playerStationaryRight.setPlayMode(Animation.PlayMode.LOOP);

        playerWalkLeft.setPlayMode(Animation.PlayMode.LOOP);
        playerWalkRight.setPlayMode(Animation.PlayMode.LOOP);
        playerJump.setPlayMode(Animation.PlayMode.LOOP);
        playerFall.setPlayMode(Animation.PlayMode.LOOP);

        playerMiningLeft.setPlayMode(Animation.PlayMode.LOOP);
        playerMiningRight.setPlayMode(Animation.PlayMode.LOOP);
    }
}
