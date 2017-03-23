package com.corb.moveboxandshow.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.corb.moveboxandshow.Controller;
import com.corb.moveboxandshow.components.UserInputComponent;

/**
 * Created by Calvin on 22/03/2017.
 */

public class InputSystem extends IteratingSystem {

    private static final Family family = Family.all(UserInputComponent.class).get();

    private ComponentMapper<UserInputComponent> ui;

    private Controller controller;

    public InputSystem(Controller controller) {
        super(family);
        this.controller = controller;

        ui = ComponentMapper.getFor(UserInputComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        UserInputComponent inputComponent = ui.get(entity);

        //conflicting situation as you can't  move left and right at the same time.
        if (controller.isLeftHeldDown() && controller.isRightHeldDown()) {
            //So I set both to false.
            inputComponent.moveLeft = false;
            inputComponent.moveRight = false;

        } else {
            inputComponent.moveLeft = controller.isLeftPressed();
            inputComponent.moveRight = controller.isRightPressed();

        }

        //Another Conflicting situation as you can't move up and down at the same time.
        if (controller.isUpPressed() && controller.isDownPressed()) {
            //So I set both to false.
            inputComponent.moveUp = false;
            inputComponent.moveDown = false;

        } else {
            inputComponent.moveUp = controller.isUpPressed();
            inputComponent.moveDown = controller.isDownPressed();

        }

    }

}
