package com.corb.moveboxandshow;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.corb.moveboxandshow.components.PlayerComponent;
import com.corb.moveboxandshow.components.UserInputComponent;
import com.corb.moveboxandshow.components.MovementComponent;
import com.corb.moveboxandshow.components.BlockComponent;
import com.corb.moveboxandshow.components.StateComponent;
import com.corb.moveboxandshow.components.TransformComponent;
import com.corb.moveboxandshow.components.AnimationComponent;
import com.corb.moveboxandshow.components.BoundsComponent;
import com.corb.moveboxandshow.components.CameraComponent;
import com.corb.moveboxandshow.components.TextureComponent;
import com.corb.moveboxandshow.systems.RenderingSystem;

/**
 * Created by Calvin on 22/03/2017.
 */

public class World {

    public static final float WORLD_WIDTH = RenderingSystem.RENDERING_DISTANCE_WIDTH;
    public static final float WORLD_HEIGHT = RenderingSystem.RENDERING_DISTANCE_HEIGHT * 20;

    private PooledEngine engine;

    public World(PooledEngine engine){
        this.engine = engine;


    }

    public void create(){
        Entity player = createPlayer();
        createCamera(player);

        int amountOfBlocks = 5;
        for(int i = 0; i <amountOfBlocks; i++){
            createBlock(1+i*2.5f,3);
        }
    }

    private Entity createPlayer() {
        Entity entity = engine.createEntity();

        AnimationComponent animation = engine.createComponent(AnimationComponent.class);
        PlayerComponent player = engine.createComponent(PlayerComponent.class);
        BoundsComponent bounds = engine.createComponent(BoundsComponent.class);
        MovementComponent movement = engine.createComponent(MovementComponent.class);
        TransformComponent position = engine.createComponent(TransformComponent.class);
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        StateComponent state = engine.createComponent(StateComponent.class);
        UserInputComponent input = engine.createComponent(UserInputComponent.class);

        //Animations - Mapping state numbers to Assets
        animation.animations.put(StateComponent.ANIMATION_STATIONARY, Assets.playerStationary);
        animation.animations.put(StateComponent.ANIMATION_STATIONARY_WALK_LEFT, Assets.playerStationaryLeft);
        animation.animations.put(StateComponent.ANIMATION_STATIONARY_WALK_RIGHT, Assets.playerStationaryRight);

        animation.animations.put(StateComponent.ANIMATION_WALK_LEFT, Assets.playerWalkLeft);
        animation.animations.put(StateComponent.ANIMATION_WALK_RIGHT, Assets.playerWalkRight);
        animation.animations.put(StateComponent.ANIMATION_JUMP, Assets.playerStationary);
        animation.animations.put(StateComponent.ANIMATION_FALL, Assets.playerStationary);

        bounds.bounds.width = PlayerComponent.WIDTH;
        bounds.bounds.height = PlayerComponent.HEIGHT;

        position.position.set(1.0f, 1.0f, 0.0f);        //Starting cord

        //default starting animation
        state.setAnimationState(state.getAnimationState());

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

    private void createBlock(float posX, float posY){
        Entity entity = engine.createEntity();

        AnimationComponent animation = engine.createComponent(AnimationComponent.class);
        BlockComponent block = engine.createComponent(BlockComponent.class);
        BoundsComponent bounds = engine.createComponent(BoundsComponent.class);
        MovementComponent movement = engine.createComponent(MovementComponent.class);
        TransformComponent position = engine.createComponent(TransformComponent.class);
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        StateComponent state = engine.createComponent(StateComponent.class);
        UserInputComponent input = engine.createComponent(UserInputComponent.class);

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

        engine.addEntity(entity);

    }

}
