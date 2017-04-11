package com.corb.moveboxandshow.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.systems.IteratingSystem;
import com.corb.moveboxandshow.components.RemovableComponent;

import java.util.Stack;

/**
 * Created by Calvin on 25/03/2017.
 */

public class RemovableSystem extends IteratingSystem {

    private ComponentMapper<RemovableComponent> rm;

    private PooledEngine engine;

    public RemovableSystem(PooledEngine engine) {
        super(Family.all(RemovableComponent.class).get());
        this.engine = engine;
        rm = ComponentMapper.getFor(RemovableComponent.class);
    }


    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        //Tests if the entitiesToRemove stack is empty
        if(rm.get(entity).entitiesToRemove.empty()) return;

        //Remove all entities from the engine which are in the stack

        RemovableComponent removeEntities = rm.get(entity);
        Stack<Entity> entitiesToRemove = removeEntities.entitiesToRemove;

        if( !(entitiesToRemove.empty() ) ){
               while (!(entitiesToRemove.empty())){
                   engine.removeEntity(entitiesToRemove.pop());

               }
        }
    }
}