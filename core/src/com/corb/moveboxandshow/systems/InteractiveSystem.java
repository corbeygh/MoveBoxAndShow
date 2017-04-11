package com.corb.moveboxandshow.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.corb.moveboxandshow.components.BoundsComponent;
import com.corb.moveboxandshow.components.InteractiveComponent;
import com.corb.moveboxandshow.components.TransformComponent;

/**
 * Created by Calvin on 25/03/2017.
 */

public class InteractiveSystem extends IteratingSystem {

    private ComponentMapper<InteractiveComponent> im;
    private ComponentMapper<BoundsComponent> bm;
    private ComponentMapper<TransformComponent> tm;

    public InteractiveSystem() {
        super(Family.all(
                InteractiveComponent.class,
                BoundsComponent.class,
                TransformComponent.class).get());

        im = ComponentMapper.getFor(InteractiveComponent.class);
        bm = ComponentMapper.getFor(BoundsComponent.class);
        tm = ComponentMapper.getFor(TransformComponent.class);
    }


    @Override
    protected void processEntity(Entity entity, float deltaTime) {

    }

}