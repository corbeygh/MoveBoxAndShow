package com.corb.moveboxandshow.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Rectangle;
import com.corb.moveboxandshow.world.World;
import com.corb.moveboxandshow.components.BlockComponent;
import com.corb.moveboxandshow.components.PlayerComponent;
import com.corb.moveboxandshow.components.UserInputComponent;
import com.corb.moveboxandshow.components.MovementComponent;
import com.corb.moveboxandshow.components.StateComponent;
import com.corb.moveboxandshow.components.TransformComponent;
import com.corb.moveboxandshow.components.BoundsComponent;

/**
 * Created by Calvin on 23/03/2017.
 */

public class CollisionSystem extends EntitySystem {

    private ComponentMapper<BoundsComponent> bm;
    private ComponentMapper<MovementComponent> mm;
    private ComponentMapper<StateComponent> sm;
    private ComponentMapper<TransformComponent> tm;

    private Engine engine;
    private World world;
    private ImmutableArray<Entity> players;
    private ImmutableArray<Entity> blocks;

    public CollisionSystem(World world) {
        this.world = world;

        bm = ComponentMapper.getFor(BoundsComponent.class);
        mm = ComponentMapper.getFor(MovementComponent.class);
        sm = ComponentMapper.getFor(StateComponent.class);
        tm = ComponentMapper.getFor(TransformComponent.class);
    }

    @Override
    public void addedToEngine(Engine engine) {
        this.engine = engine;

        players = engine.getEntitiesFor(Family.all(PlayerComponent.class,
                UserInputComponent.class,
                StateComponent.class,
                TransformComponent.class,
                MovementComponent.class).get());

        blocks = engine.getEntitiesFor(Family.all(BlockComponent.class,
                UserInputComponent.class,
                StateComponent.class,
                TransformComponent.class,
                MovementComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        PlayerSystem playerSystem = engine.getSystem(PlayerSystem.class);
        BlockSystem blockSystem = engine.getSystem(BlockSystem.class);

        //Test for collisions between:
        playerAndBlocks();

    }

    private void playerAndBlocks() {
        for (int i = 0; i < players.size(); i++) {
            Entity player = players.get(i);

            //dependent on their state I can do different things
            //eg allow or deny them from passing through the object
            StateComponent playerState = sm.get(player);
            MovementComponent playerMov = mm.get(player);
            BoundsComponent playerBounds = bm.get(player);
            TransformComponent playerPos = tm.get(player);

            // (This can be optimised so only check for boxes which are active)
            //loop through all blocks checking for a collision between player and boxes
            for (int j = 0; j < blocks.size(); j++) {
                Entity block = blocks.get(j);

                BoundsComponent blockBounds = bm.get(block);
                TransformComponent blockPos = tm.get(block);

                //test for collision
                //Rectangle: Player and Block = their Bounds + Position.
                Rectangle playerRect = new Rectangle(
                        playerPos.position.x  ,
                        playerPos.position.y ,
                        playerBounds.bounds.getWidth(),
                        playerBounds.bounds.getHeight());



                Rectangle blockRect = new Rectangle(
                        blockPos.position.x  , //- blockBounds.bounds.getWidth()
                        blockPos.position.y  ,
                        blockBounds.bounds.getWidth() -  playerBounds.bounds.getWidth()/4,
                        blockBounds.bounds.getHeight());

                //Rectangle: Player and Block = their Bounds + Position.


                if (playerRect.overlaps(blockRect)  ) {
                    //TODO TEST and set velocity to zero for player
                    //Moves Player back to last position (before the collision)
//                    playerPos.position.set(playerPos.lastPosition); //TODO Uncomment to enable collision between blocks

                }
            }

        }
    }

}