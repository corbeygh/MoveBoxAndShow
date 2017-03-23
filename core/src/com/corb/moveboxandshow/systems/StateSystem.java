package com.corb.moveboxandshow.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.corb.moveboxandshow.components.StateComponent;

/**
 * Created by Calvin on 9/03/2017.
 */

public class StateSystem extends IteratingSystem {
    private ComponentMapper<StateComponent> sm;

    public StateSystem() {
        super(Family.all(StateComponent.class).get());

        sm = ComponentMapper.getFor(StateComponent.class);
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        sm.get(entity).time += deltaTime;
    }
}
