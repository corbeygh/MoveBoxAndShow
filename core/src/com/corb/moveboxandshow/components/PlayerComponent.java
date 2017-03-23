package com.corb.moveboxandshow.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Calvin on 22/03/2017.
 */

public class PlayerComponent implements Component {

    public static final float JUMP_VELOCITY = 1;
    public static final float MOVE_VELOCITY = 1;
    public static final float STATIONARY_VELOCITY = 0;

    public static final float WIDTH = 0.8f;
    public static final float HEIGHT = 0.8f;

    //States
    public static final int STATE_JUMP = 0;
    public static final int STATE_FALL = 1;
    public static final int STATE_HIT = 2;
    public static final int STATE_WALK_LEFT = 3;
    public static final int STATE_WALK_RIGHT = 4;
    public static final int STATE_STATIONARY = 5;
    public static final int STATE_MINING = 6;

}