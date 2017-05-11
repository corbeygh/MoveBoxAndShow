package com.corb.moveboxandshow.world;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.corb.moveboxandshow.Assets;

/**
 * Created by Calvin on 26/03/2017.
 */

public class Tile {

    public final static float WIDTH = 1f;
    public final static float HEIGHT = 1f;

    private final int ID;
    private final float POS_X;
    private final float POS_Y;
    private Entity entity;

    private final int allTilesIndexRow;
    private final int allTilesIndexCol;

    //The IDS must match the order that there in the block.png
    public static final int NULL = -1;
    public static final int COOPER_BLOCK = 0;
    public static final int DIRT_BLOCK = 1;
    public static final int GRASS_BLOCK = 2;
    public static final int MINED_BLOCK = 3;

    public static final int SHOP_BLOCK_00 = 4;//Numbers represent row then col
    public static final int SHOP_BLOCK_01 = 5;
    public static final int SHOP_BLOCK_02 = 6;
    public static final int SHOP_BLOCK_03 = 7;
    public static final int SHOP_BLOCK_10 = 8;
    public static final int SHOP_BLOCK_11 = 9;
    public static final int SHOP_BLOCK_12 = 10;
    public static final int SHOP_BLOCK_13 = 11;

    public static final int SKY_BLOCK = 12;
    public static final int STONE_BLOCK = 13;
    public static final int TIN_BLOCK = 14;


    public Tile(int id, float x, float y, int row, int col) {
        this.ID = id;
        this.POS_X = x;
        this.POS_Y = y;
        this.allTilesIndexRow = row;
        this.allTilesIndexCol = col;
    }

    public void createEntity(PooledEngine engine) {
        switch (this.ID) {
            case NULL:
                this.entity = null;
                break;
            case STONE_BLOCK:
                this.entity = CreateEntity.SolidBlock(engine, this.POS_X, this.POS_Y, Assets.stoneBlock);
                break;
            case GRASS_BLOCK:
                this.entity = CreateEntity.SolidBlock(engine,this.POS_X,this.POS_Y, Assets.grassBlock);
                break;
            case SKY_BLOCK:
                this.entity = CreateEntity.HollowBlock(engine,this.POS_X,this.POS_Y, Assets.skyBlock);
                break;
            case MINED_BLOCK:
                this.entity = CreateEntity.HollowBlock(engine,this.POS_X,this.POS_Y, Assets.minedBlock);
                break;
            case DIRT_BLOCK:
                this.entity = CreateEntity.SolidBlock(engine,this.POS_X,this.POS_Y, Assets.dirtBlock);
                break;
            case COOPER_BLOCK:
                this.entity = CreateEntity.SolidBlock(engine,this.POS_X,this.POS_Y, Assets.cooperBlock);
                break;
            case TIN_BLOCK:
                this.entity = CreateEntity.SolidBlock(engine,this.POS_X,this.POS_Y, Assets.tinBlock);
                break;
            //-------------------------------------------------//
            //------------------SHOP BLOCKS--------------------//
            //-------------------------------------------------//
            case SHOP_BLOCK_00:
                this.entity = CreateEntity.HollowOreShopBlock(engine,this.POS_X,this.POS_Y, Assets.shopBlock_00);
                    break;
            case SHOP_BLOCK_01:
                this.entity = CreateEntity.HollowOreShopBlock(engine,this.POS_X,this.POS_Y, Assets.shopBlock_01);
                break;
            case SHOP_BLOCK_02:
                this.entity = CreateEntity.HollowOreShopBlock(engine,this.POS_X,this.POS_Y, Assets.shopBlock_02);
                break;
            case SHOP_BLOCK_03:
                this.entity = CreateEntity.HollowOreShopBlock(engine,this.POS_X,this.POS_Y, Assets.shopBlock_03);
                break;
            case SHOP_BLOCK_10:
                this.entity = CreateEntity.HollowOreShopBlock(engine,this.POS_X,this.POS_Y, Assets.shopBlock_10);
                break;
            case SHOP_BLOCK_11:
                this.entity = CreateEntity.HollowOreShopBlock(engine,this.POS_X,this.POS_Y, Assets.shopBlock_11);
                break;
            case SHOP_BLOCK_12:
                this.entity = CreateEntity.HollowOreShopBlock(engine,this.POS_X,this.POS_Y, Assets.shopBlock_12);
                break;
            case SHOP_BLOCK_13:
                this.entity = CreateEntity.HollowOreShopBlock(engine,this.POS_X,this.POS_Y, Assets.shopBlock_13);
                break;
        }
    }


    public int getID() {
        return ID;
    }

    public float getPOS_X() {
        return POS_X;
    }

    public float getPOS_Y() {
        return POS_Y;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntityToNull() {
        this.entity = null;
    }


    public int getAllTilesIndexRow() {
        return allTilesIndexRow;
    }

    public int getAllTilesIndexCol() {
        return allTilesIndexCol;
    }

}
