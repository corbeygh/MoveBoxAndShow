package com.corb.moveboxandshow.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.corb.moveboxandshow.components.MovementComponent;
import com.corb.moveboxandshow.components.PlayerComponent;
import com.corb.moveboxandshow.components.StateComponent;
import com.corb.moveboxandshow.components.TilePositionComponent;
import com.corb.moveboxandshow.components.TransformComponent;
import com.corb.moveboxandshow.components.UserInputComponent;

/**
 * Created by Calvin on 25/03/2017.
 */

public class TilePositionSystem extends IteratingSystem {

    private ComponentMapper<TilePositionComponent> tpm;
    private ComponentMapper<TransformComponent> tm;

    public TilePositionSystem() {
        super(Family.all(
                TilePositionComponent.class,
                TransformComponent.class).get());

        tpm = ComponentMapper.getFor(TilePositionComponent.class);
        tm = ComponentMapper.getFor(TransformComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        Vector2 tilePos = tpm.get(entity).getTilePos();

        Vector3 pos = tm.get(entity).position;

        int tileSize = tpm.get(entity).getTILE_SIZE();

        tilePos.set((pos.x+0.5f)/tileSize,(pos.y+0.5f)/tileSize);//Adding 0.5f Might fix the round issue... not sure (this needs testing)

    }
}