package com.corb.moveboxandshow.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by Calvin on 23/03/2017.
 */

public class BlockComponent implements Component {

    //States
    public static final int STATE_STATIONARY = 0;
    public static final int STATE_DESTROYED = 1;
    public static final int STATE_c = 2;
    public static final int STATE_d = 3;
    public static final int STATE_e = 4;
    public static final int STATE_f = 5;
    public static final int STATE_BEING_MINED = 6;

    //Size
    public static final float WIDTH = 1f;
    public static final float HEIGHT = 1f;
}
