package com.corb.moveboxandshow;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.assets.AssetManager;
import com.corb.moveboxandshow.components.MovementComponent;
import com.corb.moveboxandshow.components.PlayerComponent;
import com.corb.moveboxandshow.components.StateComponent;
import com.corb.moveboxandshow.components.TransformComponent;
import com.corb.moveboxandshow.components.UserInputComponent;
import com.corb.moveboxandshow.components.rendering.AnimationComponent;
import com.corb.moveboxandshow.components.rendering.BoundsComponent;
import com.corb.moveboxandshow.components.rendering.CameraComponent;
import com.corb.moveboxandshow.components.rendering.TextureComponent;
import com.corb.moveboxandshow.systems.rendering.RenderingSystem;

/**
 * Created by Calvin on 22/03/2017.
 */

public class World {

    private PooledEngine engine;

    public World(PooledEngine engine){
        this.engine = engine;


    }

    public void create(){
        //TODO create boxes which the player can collide with
        Entity player = createRobo();
        createCamera(player);
    }

    private Entity createRobo() {
        Entity entity = engine.createEntity();

        AnimationComponent animation = engine.createComponent(AnimationComponent.class);
        PlayerComponent player = engine.createComponent(PlayerComponent.class);
        BoundsComponent bounds = engine.createComponent(BoundsComponent.class);
        MovementComponent movement = engine.createComponent(MovementComponent.class);
        TransformComponent position = engine.createComponent(TransformComponent.class);
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        StateComponent state = engine.createComponent(StateComponent.class);
        UserInputComponent input = engine.createComponent(UserInputComponent.class);

        animation.animations.put(PlayerComponent.STATE_STATIONARY, Assets.blackBox);

        bounds.bounds.width = PlayerComponent.WIDTH;
        bounds.bounds.height = PlayerComponent.HEIGHT;

        position.position.set(1.0f, 1.0f, 0.0f);

        state.set(PlayerComponent.STATE_STATIONARY);

        entity.add(animation);
        entity.add(player);
        entity.add(bounds);
        entity.add(input);
        entity.add(movement);
        entity.add(position);
        entity.add(state);
        entity.add(texture);

        engine.addEntity(entity);

        return entity;
    }

    private void createCamera(Entity target) {
        Entity entity = engine.createEntity();

        CameraComponent camera = new CameraComponent();
        camera.camera = engine.getSystem(RenderingSystem.class).getCamera();
        camera.target = target;

        entity.add(camera);

        engine.addEntity(entity);
    }

}
