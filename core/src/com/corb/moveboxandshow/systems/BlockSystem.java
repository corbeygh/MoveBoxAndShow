package com.corb.moveboxandshow.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.corb.moveboxandshow.components.BlockComponent;
import com.corb.moveboxandshow.components.RemovableComponent;
import com.corb.moveboxandshow.components.UserInputComponent;
import com.corb.moveboxandshow.components.MovementComponent;
import com.corb.moveboxandshow.components.TransformComponent;
import com.corb.moveboxandshow.components.StateComponent;

/**
 * Created by Calvin on 23/03/2017.
 */

public class BlockSystem extends IteratingSystem {

    private static final Family family = Family.all(BlockComponent.class,
            RemovableComponent.class).get();

    private ComponentMapper<BlockComponent> bm;
    private ComponentMapper<RemovableComponent> rm;

    public BlockSystem() {
        super(family);
        bm = ComponentMapper.getFor(BlockComponent.class);
        rm = ComponentMapper.getFor(RemovableComponent.class);
    }



    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        if(bm.get(entity).isRemove()){
            rm.get(entity).entitiesToRemove.add(entity);
        }

    }

}
