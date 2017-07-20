package com.corb.moveboxandshow.world;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
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

    public static final int PPM = 100;

    public static final int WORLD_WIDTH = 64;
    public static final int WORLD_HEIGHT = 64;
    public static final int WORLD_GROUND_LEVEL = 16;
    public static final float PLAYER_START_X = 130f - 2f ;
    public static final float PLAYER_START_Y = 130 + 64f -2f;

    public static final float EDGE_NORTH = 128f + WORLD_HEIGHT; //TOP
    public static final float EDGE_SOUTH = 128f; //BOT

    public static final float EDGE_EAST = 128f + WORLD_WIDTH; //RIGHT
    public static final float EDGE_WEST = 128f; //LEFT

    private PooledEngine engine;
    private Entity player;
    private GameWorldManager worldManager;

    //Box2d variables
    private World box2DWorld;
    private Box2DDebugRenderer b2dr;

    public GameWorld(PooledEngine engine, World box2DWorld) {
        this.engine = engine;
        this.box2DWorld = box2DWorld;

    }

    public void create() {
        this.player = CreateEntity.createPlayer(engine,box2DWorld,PLAYER_START_X,PLAYER_START_Y);

        box2DWorld.setContactListener(new WorldContactListener(player));

        createCamera(this.player);
        worldManager = new GameWorldManager(this.player, this.engine);
    }

    public void update() {
        if (worldManager != null) {
            worldManager.update();
        }
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

    public GameWorldManager getWorldManager() {
        return worldManager;
    }
}
