package com.corb.moveboxandshow.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.corb.moveboxandshow.components.MovementComponent;
import com.corb.moveboxandshow.components.PlayerComponent;
import com.corb.moveboxandshow.components.StateComponent;
import com.corb.moveboxandshow.components.TransformComponent;

/**
 * Created by Calvin on 22/03/2017.
 */

public class MovementSystem extends IteratingSystem {

    private ComponentMapper<TransformComponent> tm;
    private ComponentMapper<MovementComponent> mm;
    private ComponentMapper<StateComponent> sm;

    public MovementSystem() {
        super(Family.all(
                TransformComponent.class,
                MovementComponent.class,
                StateComponent.class).get());

        tm = ComponentMapper.getFor(TransformComponent.class);
        mm = ComponentMapper.getFor(MovementComponent.class);
        sm = ComponentMapper.getFor(StateComponent.class);
    }


    //ProcessEntity is used to adjust Component Values to all the entity's
    @Override
    public void processEntity(Entity entity, float deltaTime) {
        TransformComponent position = tm.get(entity);
        MovementComponent mov = mm.get(entity);
        StateComponent state = sm.get(entity);

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

        setAnimationState(mov.velocity.x, mov.velocity.y, state); //set Animation state based on the direction of travel
    }

    private void setAnimationState(float x, float y, StateComponent state) {
        //GOLDEN RULE: DO NOT call setAnimationState IF its the same animationState
        if (state.getActionState() == state.ACTION_DEFAULT) {
            if (x == 0 && y == 0) {
                if (state.getAnimationState() != StateComponent.ANIMATION_STATIONARY)
                    state.setAnimationState(StateComponent.ANIMATION_STATIONARY);
            } else if (y == 0 && x > 0) {
                if (state.getAnimationState() != StateComponent.ANIMATION_WALK_RIGHT)
                    state.setAnimationState(StateComponent.ANIMATION_WALK_RIGHT);
            } else if (y == 0 && x < 0) {
                if (state.getAnimationState() != StateComponent.ANIMATION_WALK_LEFT)
                    state.setAnimationState(StateComponent.ANIMATION_WALK_LEFT);
            } else if (y > 0) {
                if (state.getAnimationState() != StateComponent.ANIMATION_JUMP)
                    state.setAnimationState(StateComponent.ANIMATION_JUMP);
            } else if (y < 0) {
                if (state.getAnimationState() != StateComponent.ANIMATION_FALL)
                    state.setAnimationState(StateComponent.ANIMATION_FALL);
            }
        }

    }


}