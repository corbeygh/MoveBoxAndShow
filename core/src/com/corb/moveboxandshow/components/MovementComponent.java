package com.corb.moveboxandshow.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Calvin on 22/03/2017.
 */

public class MovementComponent implements Component{
    public final Vector2 velocity = new Vector2();
    public final Vector2 acceleration = new Vector2();
}
