package com.corb.moveboxandshow.world;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.corb.moveboxandshow.Assets;
import com.corb.moveboxandshow.components.AnimationComponent;
import com.corb.moveboxandshow.components.BlockComponent;
import com.corb.moveboxandshow.components.BoundsComponent;
import com.corb.moveboxandshow.components.Box2DComponent;
import com.corb.moveboxandshow.components.CollisionComponent;
import com.corb.moveboxandshow.components.MovementComponent;
import com.corb.moveboxandshow.components.PlayerComponent;
import com.corb.moveboxandshow.components.RemovableComponent;
import com.corb.moveboxandshow.components.ShopComponent;
import com.corb.moveboxandshow.components.StateComponent;
import com.corb.moveboxandshow.components.TextureComponent;
import com.corb.moveboxandshow.components.TilePositionComponent;
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

        block.setSolid(true);

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


    /**
     * Player Entity:
     * Also define the players Box2D
     *
     *
     */

    public static Entity createPlayer(PooledEngine engine, World b2World, float startX, float startY) {
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
        Box2DComponent box2D = engine.createComponent(Box2DComponent.class);


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

        position.pos.set(startX, startY, -1.0f);        //Starting cord

        definePlayerBox2D(box2D, b2World, position);

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
        entity.add(box2D);

        engine.addEntity(entity);

        return entity;
    }

    private static void definePlayerBox2D(Box2DComponent box2D, World box2DWorld, TransformComponent position) {
        BodyDef bDef = new BodyDef();
        World b2World = box2DWorld;
        Body b2body;


        //Starting Player Position:
        float playerSize = (32 / GameWorld.PPM);
        float startX = position.pos.x;
        float startY = position.pos.y;
        bDef.position.set(startX * (32 / GameWorld.PPM) - playerSize / 2, startY * (32 / GameWorld.PPM) - playerSize / 2);
        bDef.type = BodyDef.BodyType.DynamicBody;
        //once defined we can add it to our world
        b2body = b2World.createBody(bDef);

        FixtureDef fdef = new FixtureDef();

        CircleShape shape = new CircleShape();
        shape.setRadius(15 / GameWorld.PPM);

        fdef.filter.categoryBits = CollisionComponent.PLAYER_BIT;
        fdef.filter.maskBits = CollisionComponent.SHOP_BIT | CollisionComponent.GROUND_BIT;

        //New Fixture: Head
        fdef.shape = shape;
        b2body.createFixture(fdef);

        EdgeShape head = new EdgeShape();
        head.set(new Vector2(-4 / GameWorld.PPM, 18 / GameWorld.PPM), new Vector2(4 / GameWorld.PPM, 18 / GameWorld.PPM));
        fdef.shape = head;
        fdef.isSensor = true;
        b2body.createFixture(fdef).setUserData("head");


        //New Fixture: For Left Side
        fdef.shape = new CircleShape();
        b2body.createFixture(fdef);
        head = new EdgeShape();
        head.set(new Vector2(-18 / GameWorld.PPM, -4 / GameWorld.PPM), new Vector2(-18 / GameWorld.PPM, 4 / GameWorld.PPM));
        fdef.shape = head;
        fdef.isSensor = true;
        b2body.createFixture(fdef).setUserData("left");

        //New Fixture: For Right Side
        fdef.shape = new CircleShape();
        b2body.createFixture(fdef);
        head = new EdgeShape();
        head.set(new Vector2(18 / GameWorld.PPM, -4 / GameWorld.PPM), new Vector2(18 / GameWorld.PPM, 4 / GameWorld.PPM));
        fdef.shape = head;
        fdef.isSensor = true;
        b2body.createFixture(fdef).setUserData("right");


        //New Fixture: For Bottom
        fdef.shape = new CircleShape();
        b2body.createFixture(fdef);
        head = new EdgeShape();
        head.set(new Vector2(-4 / GameWorld.PPM, -18 / GameWorld.PPM), new Vector2(4 / GameWorld.PPM, -18 / GameWorld.PPM));
        fdef.shape = head;
        fdef.isSensor = true;
        b2body.createFixture(fdef).setUserData("bottom");

        box2D.setB2body(b2body);
    }

}
