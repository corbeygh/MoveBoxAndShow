package com.corb.moveboxandshow.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by Calvin on 12/05/2017.
 */

public class CollisionComponent implements Component {

    public static final short DEFAULT_BIT = 1;
    public static final short PLAYER_BIT = 2;
    public static final short GROUND_BIT = 4;
    public static final short SKY_BIT = 8;
    public static final short SHOP_BIT = 16;
    public static final short DESTROYED_BIT = 32;


    private boolean playerOverlaps = false;

    public boolean isPlayerOverlaps() {
        return playerOverlaps;
    }

    public void setPlayerOverlaps(boolean playerOverlaps) {
        this.playerOverlaps = playerOverlaps;
    }
}
