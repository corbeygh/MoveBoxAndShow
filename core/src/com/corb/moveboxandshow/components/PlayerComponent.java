package com.corb.moveboxandshow.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.corb.moveboxandshow.world.GameWorld;

/**
 * Created by Calvin on 22/03/2017.
 */

public class PlayerComponent implements Component {

    public static final float JUMP_VELOCITY = 1;
    public static final float MOVE_VELOCITY = 15;
    public static final float STATIONARY_VELOCITY = 0;

    //Width and Height should only be changed if the Sprite is smaller that 32 pix.
    public static final float WIDTH = 0.78f; //Changing these values will effect the collision box
    public static final float HEIGHT = 0.78f;

    private Vector2 tilePos = new Vector2(GameWorld.PLAYER_START_X, GameWorld.PLAYER_START_Y);

    public Vector2 getTilePos() {
        return tilePos;
    }

    public void setTilePos(Vector2 tilePos) {
        this.tilePos = tilePos;
    }
}
