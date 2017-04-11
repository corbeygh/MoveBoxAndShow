package com.corb.moveboxandshow.world;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;

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

    private static final int NULL = 0;
    private static final int BLOCK_ID = 1;

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
            case BLOCK_ID:
                this.entity = CreateEntity.createBlock(engine, this.POS_X, this.POS_Y);
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
