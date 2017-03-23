package com.corb.moveboxandshow.components.rendering;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.IntMap;

/**
 * Created by Calvin on 9/03/2017.
 */

public class AnimationComponent implements Component {
    public IntMap<Animation> animations = new IntMap<Animation>();
}
