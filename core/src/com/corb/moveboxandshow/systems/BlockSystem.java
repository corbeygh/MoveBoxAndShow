package com.corb.moveboxandshow.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.corb.moveboxandshow.components.PlayerComponent;
import com.corb.moveboxandshow.components.UserInputComponent;
import com.corb.moveboxandshow.components.MovementComponent;
import com.corb.moveboxandshow.components.TransformComponent;
import com.corb.moveboxandshow.components.StateComponent;

/**
 * Created by Calvin on 23/03/2017.
 */

public class BlockSystem extends IteratingSystem {

    private static final Family family = Family.all(PlayerComponent.class,
            UserInputComponent.class,
            StateComponent.class,
            TransformComponent.class,
            MovementComponent.class).get();

    private ComponentMapper<PlayerComponent> pm;
    private ComponentMapper<UserInputComponent> ui;
    private ComponentMapper<StateComponent> sm;
    private ComponentMapper<TransformComponent> tm;
    private ComponentMapper<MovementComponent> mm;

    public BlockSystem() {
        super(family);

        pm = ComponentMapper.getFor(PlayerComponent.class);
        ui = ComponentMapper.getFor(UserInputComponent.class);
        sm = ComponentMapper.getFor(StateComponent.class);
        tm = ComponentMapper.getFor(TransformComponent.class);
        mm = ComponentMapper.getFor(MovementComponent.class);
    }



    @Override
    protected void processEntity(Entity entity, float deltaTime) {

    }
}
