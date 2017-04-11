package com.corb.moveboxandshow.world;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.corb.moveboxandshow.Assets;
import com.corb.moveboxandshow.components.AnimationComponent;
import com.corb.moveboxandshow.components.BlockComponent;
import com.corb.moveboxandshow.components.BoundsComponent;
import com.corb.moveboxandshow.components.MovementComponent;
import com.corb.moveboxandshow.components.RemovableComponent;
import com.corb.moveboxandshow.components.StateComponent;
import com.corb.moveboxandshow.components.TextureComponent;
import com.corb.moveboxandshow.components.TransformComponent;
import com.corb.moveboxandshow.components.UserInputComponent;

/**
 * Created by Calvin on 26/03/2017.
 */

public class CreateEntity {


    public static Entity createBlock(PooledEngine engine, float posX, float posY) {
        Entity entity = engine.createEntity();

        AnimationComponent animation = engine.createComponent(AnimationComponent.class);
        BlockComponent block = engine.createComponent(BlockComponent.class);
        BoundsComponent bounds = engine.createComponent(BoundsComponent.class);
        MovementComponent movement = engine.createComponent(MovementComponent.class);
        TransformComponent position = engine.createComponent(TransformComponent.class);
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        StateComponent state = engine.createComponent(StateComponent.class);
        UserInputComponent input = engine.createComponent(UserInputComponent.class);
        RemovableComponent removable = engine.createComponent(RemovableComponent.class);

        animation.animations.put(StateComponent.ANIMATION_STATIONARY, Assets.blackBox);

        bounds.bounds.width = BlockComponent.WIDTH;
        bounds.bounds.height = BlockComponent.HEIGHT;

        position.position.set(posX, posY, 0.0f);

        state.setAnimationState(state.getAnimationState());

        entity.add(animation);
        entity.add(block);
        entity.add(bounds);
        entity.add(input);
        entity.add(movement);
        entity.add(position);
        entity.add(state);
        entity.add(texture);
        entity.add(removable);

        engine.addEntity(entity);

        return entity;
    }


}
