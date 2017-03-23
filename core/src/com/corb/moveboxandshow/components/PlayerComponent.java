package com.corb.moveboxandshow.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by Calvin on 22/03/2017.
 */

public class PlayerComponent implements Component {

    public static final float JUMP_VELOCITY = 1;
    public static final float MOVE_VELOCITY = 5;
    public static final float STATIONARY_VELOCITY = 0;

    //Width and Height should only be changed if the Sprite is smaller that 32 pix.
    public static final float WIDTH = 0.8f; //Changing these values will effect the collision box
    public static final float HEIGHT = 1f;




}
