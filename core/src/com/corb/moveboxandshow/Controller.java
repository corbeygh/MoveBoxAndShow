package com.corb.moveboxandshow;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Calvin on 4/03/2017.
 */

public class Controller {

    private Main game;
    private Viewport viewPort;
    private Stage stage;
    private boolean upPressed, downPressed, leftPressed, rightPressed, inventoryPressed;
    private OrthographicCamera camera;

    public static boolean upHeldDown, downHeldDown, leftHeldDown, rightHeldDown,inventoryHeldDown;
    private float upHeldDownTimer, downHeldDownTimer, leftHeldDownTimer, rightHeldDownTimer, inventoryHeldDownTimer;
    private float heldDownConstant = 0.25f;//This is the amount a key needs to be pressed to be considered "HeldDown"


    public Controller(Main game) {
        this.game = game;
        camera = new OrthographicCamera();
        viewPort = new FitViewport(Assets.W_WIDTH, Assets.W_HEIGHT, camera);
        stage = new Stage(viewPort, game.batch);
        Gdx.input.setInputProcessor(stage);
        stage.addListener(new InputListener() {


            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                if (x < (Assets.W_WIDTH/8) *3){
                    rightPressed = false;
                    leftPressed = true;

                } else if( x > (Assets.W_WIDTH/8) *5){
                    rightPressed = true;
                    leftPressed = false;

                }

                if( y < (Assets.W_HEIGHT/8) *2){
                    upPressed = false;
                    downPressed = true;

                } else if( y > (Assets.W_HEIGHT/8) *6){
                    upPressed = true;
                    downPressed = false;
                }

                return true;
            }



            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                rightPressed = false;
                leftPressed = false;
                upPressed = false;
                downPressed = false;
            }



            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                switch (keycode) {
                    case Input.Keys.UP:
                        upPressed = true;
                        break;
                    case Input.Keys.DOWN:
                        downPressed = true;
                        break;
                    case Input.Keys.LEFT:
                        leftPressed = true;
                        break;
                    case Input.Keys.RIGHT:
                        rightPressed = true;
                        break;
                    case Input.Keys.W:
                        upPressed = true;
                        break;
                    case Input.Keys.S:
                        downPressed = true;
                        break;
                    case Input.Keys.A:
                        leftPressed = true;
                        break;
                    case Input.Keys.D:
                        rightPressed = true;
                        break;
                    case Input.Keys.G:
                        inventoryPressed = true;
                        break;
                }
                return true;
            }

            @Override
            public boolean keyUp(InputEvent event, int keycode) {
                switch (keycode) {
                    case Input.Keys.UP:
                        upPressed = false;
                        break;
                    case Input.Keys.DOWN:
                        downPressed = false;
                        break;
                    case Input.Keys.LEFT:
                        leftPressed = false;
                        break;
                    case Input.Keys.RIGHT:
                        rightPressed = false;
                        break;
                    case Input.Keys.W:
                        upPressed = false;
                        break;
                    case Input.Keys.S:
                        downPressed = false;
                        break;
                    case Input.Keys.A:
                        leftPressed = false;
                        break;
                    case Input.Keys.D:
                        rightPressed = false;
                        break;
                    case Input.Keys.G:
                        inventoryPressed = false;
                        break;
                }
                return true;
            }
        });






////        Table table = new Table();
////        table.left().bottom();
////
////
////        table.add();
////        table.add(upImg).size(upImg.getWidth(), upImg.getHeight());
////        table.row();
////        table.add();
////        table.row().pad(5, 5, 5, 5);
////        table.add(leftImg).size(leftImg.getWidth(), leftImg.getHeight());
////        table.add();
////        table.add(rightImg).size(rightImg.getWidth(), rightImg.getHeight());
////        table.row().padBottom(5);
////        table.add();
////        table.add(downImg).size(downImg.getWidth(), downImg.getHeight());
////        table.add();
//
//        stage.addActor(table);
        Actor actor = new Actor();
        stage.addActor(actor);
    }

    public void update(float delta) {
        updateTimers(delta);
        upHeldDown = upHeldDownTimer >= heldDownConstant;
        downHeldDown = downHeldDownTimer >= heldDownConstant;
        leftHeldDown = leftHeldDownTimer >= heldDownConstant;
        rightHeldDown = rightHeldDownTimer >= heldDownConstant;
        inventoryHeldDown = inventoryHeldDownTimer >= heldDownConstant;
    }

    private void updateTimers(float delta) {
        if (upPressed) {
            upHeldDownTimer += delta;
        } else {
            upHeldDownTimer = 0;
        }
        if (downPressed) {
            downHeldDownTimer += delta;
        } else {
            downHeldDownTimer = 0;
        }
        if (leftPressed) {
            leftHeldDownTimer += delta;
        } else {
            leftHeldDownTimer = 0;
        }
        if (rightPressed) {
            rightHeldDownTimer += delta;
        } else {
            rightHeldDownTimer = 0;
        }
        if(inventoryPressed){
            inventoryHeldDownTimer += delta;
        } else  {
            inventoryHeldDownTimer = 0;
        }
    }

    public void draw() {
        stage.draw();
    }



    public void resize(int width, int height) {
        viewPort.update(width, height);
    }

    public boolean isUpPressed() {
        return upPressed;
    }

    public boolean isDownPressed() {
        return downPressed;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public boolean isUpHeldDown() {
        return upHeldDown;
    }

    public boolean isDownHeldDown() {
        return downHeldDown;
    }

    public boolean isLeftHeldDown() {
        return leftHeldDown;
    }

    public boolean isRightHeldDown() {
        return rightHeldDown;
    }

    public boolean isInventoryHeldDown() {
        return inventoryHeldDown;
    }

    public void setInventoryHeldDownTimer(float inventoryHeldDownTimer) {
        this.inventoryHeldDownTimer = inventoryHeldDownTimer;
    }

    public void setInventoryHeldDown(boolean inventoryHeldDown) {
        inventoryHeldDown = inventoryHeldDown;
    }
}
