package com.corb.moveboxandshow.world;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.math.Vector2;
import com.corb.moveboxandshow.Assets;
import com.corb.moveboxandshow.components.PlayerComponent;
import com.corb.moveboxandshow.components.TilePositionComponent;
import com.corb.moveboxandshow.components.UserInputComponent;
import com.corb.moveboxandshow.components.MovementComponent;
import com.corb.moveboxandshow.components.StateComponent;
import com.corb.moveboxandshow.components.TransformComponent;
import com.corb.moveboxandshow.components.AnimationComponent;
import com.corb.moveboxandshow.components.BoundsComponent;
import com.corb.moveboxandshow.components.CameraComponent;
import com.corb.moveboxandshow.components.TextureComponent;
import com.corb.moveboxandshow.systems.RenderingSystem;

/**
 * Responsible for creating Entities
 */

public class GameWorld {


    public static final int WORLD_WIDTH = 64;
    public static final int WORLD_HEIGHT = 64;
    public static final int WORLD_GROUND_LEVEL = 16;
    public static final float PLAYER_START_X = 130f;
    public static final float PLAYER_START_Y = 130 + 64f;

    public static final float EDGE_NORTH = 128f + WORLD_HEIGHT; //TOP
    public static final float EDGE_SOUTH = 128f; //BOT

    public static final float EDGE_EAST = 128f + WORLD_WIDTH; //RIGHT
    public static final float EDGE_WEST = 128f; //LEFT

    private PooledEngine engine;
    private Entity player;
    private GameWorldManager worldManager;



    public GameWorld(PooledEngine engine) {
        this.engine = engine;
    }

    public void create() {
        this.player = createPlayer();

        createCamera(this.player);
        worldManager = new GameWorldManager(this.player, this.engine);
    }

    public void update() {
        if (worldManager != null) {
            worldManager.update();
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
        TilePositionComponent tilePos = engine.createComponent(TilePositionComponent.class);

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

        position.pos.set(PLAYER_START_X, PLAYER_START_Y, -1.0f);        //Starting cord

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
        entity.add(tilePos);

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

    public Entity getPlayer() {
        return player;
    }
}
