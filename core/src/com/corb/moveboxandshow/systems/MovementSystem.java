package com.corb.moveboxandshow.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.corb.moveboxandshow.components.MovementComponent;
import com.corb.moveboxandshow.components.TransformComponent;

/**
 * Created by Calvin on 22/03/2017.
 */

public class MovementSystem extends IteratingSystem {

    private ComponentMapper<TransformComponent> tm;
    private ComponentMapper<MovementComponent> mm;

    public MovementSystem() {
        super(Family.all(TransformComponent.class, MovementComponent.class).get());

        tm = ComponentMapper.getFor(TransformComponent.class);
        mm = ComponentMapper.getFor(MovementComponent.class);
    }


    //ProcessEntity is used to adjust Component Values to all the entity's
    @Override
    public void processEntity(Entity entity, float deltaTime) {
        TransformComponent position = tm.get(entity);
        MovementComponent mov = mm.get(entity);

        Vector2 vector = new Vector2();

        //calculate change in velocity using acceleration
        vector.set(mov.acceleration).scl(deltaTime); // scale by deltaTime

        //update current velocity variable
        mov.velocity.add(vector);

        //calculate change in position using velocity
        vector.set(mov.velocity).scl(deltaTime);

        //update current position variable
        position.lastPosition.set(position.position);
        position.position.add(vector.x, vector.y, 0.0f);
    }


}