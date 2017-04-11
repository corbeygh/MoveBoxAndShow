package com.corb.moveboxandshow.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Calvin on 25/03/2017.
 */

public class TilePositionComponent implements Component {

    private Vector2 tilePos = new Vector2(0,0);
    private final int TILE_SIZE = 1;


    public Vector2 getTilePos() {
        return tilePos;
    }

    public void setTilePos(Vector2 tilePos) {
        this.tilePos = tilePos;
    }

    public int getTILE_SIZE() {
        return TILE_SIZE;
    }
}
