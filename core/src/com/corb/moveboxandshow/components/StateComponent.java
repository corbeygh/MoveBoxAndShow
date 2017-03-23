package com.corb.moveboxandshow.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by Calvin on 3/03/2017.
 */

public class StateComponent implements Component {

    //Animation States (0-99)
    public static final int ANIMATION_JUMP = 0;
    public static final int ANIMATION_FALL = 1;
    public static final int ANIMATION_WALK_LEFT = 2;
    public static final int ANIMATION_WALK_RIGHT = 3;
    public static final int ANIMATION_STATIONARY = 4;
    public static final int ANIMATION_STATIONARY_WALK_LEFT = 5;
    public static final int ANIMATION_STATIONARY_WALK_RIGHT = 6;
    public static final int ANIMATION_MINING = 7;
    public static final int ANIMATION_HIT = 8;

    private int animationState = ANIMATION_STATIONARY;
    public float animationTime = 0.0f;

    //Actions States (100-199)

    public static final int ACTION_DEFAULT = 100; //If the object is not doing anything or is moving.
    public static final int ACTION_MINING = 101;

    private int actionState = ACTION_DEFAULT;
    public float actionTime = 0.0f;

    private int state = 0;
    public float time = 0.0f;


    public int getAnimationState() {
        return animationState;
    }

    public void setAnimationState(int animationState) {
        this.animationState = animationState;
        this.animationTime = 0.0f;
    }

    public int getActionState() {
        return actionState;
    }

    public void setActionState(int actionState) {
        this.actionState = actionState;
        this.actionTime = 0.0f;
    }

    public int get() {
        return state;
    }

    public void set(int newState) {
        this.state = newState;
        this.time = 0.0f;
    }
}