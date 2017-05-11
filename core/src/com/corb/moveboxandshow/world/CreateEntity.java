package com.corb.moveboxandshow.world;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.corb.moveboxandshow.components.AnimationComponent;
import com.corb.moveboxandshow.components.BlockComponent;
import com.corb.moveboxandshow.components.BoundsComponent;
import com.corb.moveboxandshow.components.MovementComponent;
import com.corb.moveboxandshow.components.RemovableComponent;
import com.corb.moveboxandshow.components.ShopComponent;
import com.corb.moveboxandshow.components.StateComponent;
import com.corb.moveboxandshow.components.TextureComponent;
import com.corb.moveboxandshow.components.TransformComponent;
import com.corb.moveboxandshow.components.UserInputComponent;

/**
 * Created by Calvin on 26/03/2017.
 *
 * List of all the Entitys that I can make inside the Game:
 *
 * As Time goes on these Entitys will get more specifc
 * Solid Block, has the Collision Hollow Does not
 *
 */

public class CreateEntity {


    public static Entity SolidBlock(PooledEngine engine, float posX, float posY, Animation animationBasedOnID) {
        Entity entity = engine.createEntity();

        AnimationComponent animation = engine.createComponent(AnimationComponent.class);
        BlockComponent block = engine.createComponent(BlockComponent.class);
        BoundsComponent bounds = engine.createComponent(BoundsComponent.class);
        TransformComponent position = engine.createComponent(TransformComponent.class);
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        StateComponent state = engine.createComponent(StateComponent.class);
        UserInputComponent input = engine.createComponent(UserInputComponent.class);
        RemovableComponent removable = engine.createComponent(RemovableComponent.class);

        animation.animations.put(StateComponent.ANIMATION_STATIONARY, animationBasedOnID);

        bounds.bounds.width = BlockComponent.WIDTH;
        bounds.bounds.height = BlockComponent.HEIGHT;

        position.pos.set(posX, posY, 0.0f);

        state.setAnimationState(state.getAnimationState());

        entity.add(animation);
        entity.add(block);
        entity.add(bounds);
        entity.add(input);
        entity.add(position);
        entity.add(state);
        entity.add(texture);
        entity.add(removable);

        engine.addEntity(entity);

        return entity;
    }

    public static Entity HollowBlock(PooledEngine engine, float posX, float posY, Animation animationBasedOnID) {
        Entity entity = engine.createEntity();

        AnimationComponent animation = engine.createComponent(AnimationComponent.class);
        BlockComponent block = engine.createComponent(BlockComponent.class);
        BoundsComponent bounds = engine.createComponent(BoundsComponent.class);
        MovementComponent movement = engine.createComponent(MovementComponent.class);
        TransformComponent position = engine.createComponent(TransformComponent.class);
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        StateComponent state = engine.createComponent(StateComponent.class);
        UserInputComponent input = engine.createComponent(UserInputComponent.class);
        RemovableComponent removable = engine.createComponent(RemovableComponent.class);

        animation.animations.put(StateComponent.ANIMATION_STATIONARY, animationBasedOnID);

        bounds.bounds.width = BlockComponent.WIDTH;
        bounds.bounds.height = BlockComponent.HEIGHT;

        position.pos.set(posX, posY, 0.0f);

        state.setAnimationState(state.getAnimationState());

        block.setSolid(false);

        entity.add(animation);
        entity.add(block);
        entity.add(bounds);
        entity.add(input);
        entity.add(movement);
        entity.add(position);
        entity.add(state);
        entity.add(texture);
        entity.add(removable);

        engine.addEntity(entity);

        return entity;
    }

    /**
     * First Trial version of a interactive block, used for detecting if a player is close to a shop
     * brings up a shop icon
     * then opens gui.
     *
     * 
     */
    public static Entity HollowOreShopBlock(PooledEngine engine, float posX, float posY, Animation animationBasedOnID) {
        Entity entity = engine.createEntity();

        AnimationComponent animation = engine.createComponent(AnimationComponent.class);
        BlockComponent block = engine.createComponent(BlockComponent.class);
        BoundsComponent bounds = engine.createComponent(BoundsComponent.class);
        TransformComponent position = engine.createComponent(TransformComponent.class);
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        StateComponent state = engine.createComponent(StateComponent.class);
        UserInputComponent input = engine.createComponent(UserInputComponent.class);
        RemovableComponent removable = engine.createComponent(RemovableComponent.class);
        ShopComponent shop = engine.createComponent(ShopComponent.class);

        animation.animations.put(StateComponent.ANIMATION_STATIONARY, animationBasedOnID);

        bounds.bounds.width = BlockComponent.WIDTH;
        bounds.bounds.height = BlockComponent.HEIGHT;

        position.pos.set(posX, posY, 0.0f);

        state.setAnimationState(state.getAnimationState());

        block.setSolid(false);

        shop.setShopID(ShopComponent.ORE_SHOP_ID);

        entity.add(animation);
        entity.add(block);
        entity.add(bounds);
        entity.add(input);
        entity.add(position);
        entity.add(state);
        entity.add(texture);
        entity.add(shop);
        entity.add(removable);

        engine.addEntity(entity);

        return entity;
    }


}
