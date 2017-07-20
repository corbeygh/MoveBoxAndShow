package com.corb.moveboxandshow;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.corb.moveboxandshow.systems.CollisionSystem;
import com.corb.moveboxandshow.systems.InputSystem;
import com.corb.moveboxandshow.systems.MovementSystem;
import com.corb.moveboxandshow.systems.PlayerSystem;
import com.corb.moveboxandshow.systems.RemovableSystem;
import com.corb.moveboxandshow.systems.ShopSystem;
import com.corb.moveboxandshow.systems.StateSystem;
import com.corb.moveboxandshow.systems.AnimationSystem;
import com.corb.moveboxandshow.systems.CameraSystem;
import com.corb.moveboxandshow.systems.RenderingSystem;
import com.corb.moveboxandshow.systems.BlockSystem;
import com.corb.moveboxandshow.systems.TilePositionSystem;
import com.corb.moveboxandshow.world.GameWorld;


public class GameScreen extends ScreenAdapter {
    private static final int GAME_READY = 0;
    private static final int GAME_RUNNING = 1;
    private static final int GAME_PAUSED = 2;
    private static final int GAME_OVER = 4;
    //Game State
    private int state;

    //DEBUGGING
    private FPSLogger myFPS;

    private final Main game;

    private GameWorld gameWorld;
    private OrthographicCamera guiCamera;
    //Input
    private Controller controller;
    //Game Engine
    private PooledEngine engine;

    //Box2D
    private World box2DWorld;
    private Box2DDebugRenderer b2dr;

    public GameScreen(Main game) {
        this.game = game;
        controller = new Controller(game);
        myFPS = new FPSLogger();

        guiCamera = new OrthographicCamera(Assets.W_WIDTH, Assets.W_HEIGHT);
        guiCamera.position.set(Assets.W_WIDTH / 2, Assets.W_HEIGHT / 2, 0);

        engine = new PooledEngine();

        b2dr = new Box2DDebugRenderer();
        box2DWorld = new World(new Vector2(0, -9.8f), true);
        gameWorld = new GameWorld(engine, box2DWorld);

        //Add Systems:

        engine.addSystem(new PlayerSystem(gameWorld));
        engine.addSystem(new BlockSystem());
        engine.addSystem(new MovementSystem());
        engine.addSystem(new TilePositionSystem());
        engine.addSystem(new CollisionSystem(gameWorld));

        engine.addSystem(new ShopSystem(gameWorld, game));

        engine.addSystem(new InputSystem(controller));
        engine.addSystem(new StateSystem());
        engine.addSystem(new AnimationSystem());
        engine.addSystem(new CameraSystem());
        engine.addSystem(new RenderingSystem(game.batch));

        engine.addSystem(new RemovableSystem(this.engine));//Very important that this is the last System. You don't want to remove an Entity then reference it!

        gameWorld.create();

        state = GAME_READY;
        pauseSystems();
    }

    @Override
    public void render(float delta) {
        update(delta);
        myFPS.log();
        drawUI();

    }

    private void update(float deltaTime) {
        if (deltaTime > 0.1f) deltaTime = 0.1f;

        //Handles updating all the Active Systems
        controller.update(deltaTime);
        engine.update(deltaTime);
        gameWorld.update();
        //A Switch state is used to adjust which Systems are active based on the Game State
        //Eg a pause would set all Player movement systems to false... ect
        switch (state) {
            case GAME_READY:
                updateReady();
                break;
            case GAME_RUNNING:
                updateRunning(deltaTime);
                break;
        }
    }

    private void updateReady() {
        if (Gdx.input.justTouched()) {
            state = GAME_RUNNING;
            resumeSystems();
        }
    }

    private void updateRunning(float deltaTime) {
        //if user clicks pause button, pause systems.
    }

    private void drawUI() {
        //Dependent on the device Render:
        if (Gdx.app.getType() == Application.ApplicationType.Android) {
            controller.draw();
        } else {
            if (box2DWorld == null) {
                System.out.println("null world");

            } else {
                b2dr.render(box2DWorld, guiCamera.combined);//render our Box2DDebugLines - THIS IS VERY EXPENSIVE on an Android device however on the PC its fine.

            }


        }
        guiCamera.update();
    }

    private void pauseSystems() {
        //gets all engine systems and sets them to false
        //eg : engine.getSystem(CollisionSystem.class).setProcessing(false);
        engine.getSystem(PlayerSystem.class).setProcessing(false);
        engine.getSystem(MovementSystem.class).setProcessing(false);
    }

    private void resumeSystems() {
        //gets all engine systems and sets them to true
        //eg : engine.getSystem(CollisionSystem.class).setProcessing(true);
        engine.getSystem(PlayerSystem.class).setProcessing(true);
        engine.getSystem(MovementSystem.class).setProcessing(true);
    }

    @Override
    public void dispose() {
        game.dispose();
    }
}

