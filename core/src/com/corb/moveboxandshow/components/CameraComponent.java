package com.corb.moveboxandshow.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * Created by Calvin on 9/03/2017.
 */

public class CameraComponent implements Component {
    public Entity target;
    public OrthographicCamera camera;
}
