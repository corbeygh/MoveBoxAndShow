package com.corb.moveboxandshow.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Rectangle;
import com.corb.moveboxandshow.world.GameWorld;
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
    private ComponentMapper<BlockComponent> bc;

    private Engine engine;
    private GameWorld world;
    private ImmutableArray<Entity> players;
    private ImmutableArray<Entity> blocks;

    public CollisionSystem(GameWorld world) {
        this.world = world;

        bm = ComponentMapper.getFor(BoundsComponent.class);
        mm = ComponentMapper.getFor(MovementComponent.class);
        sm = ComponentMapper.getFor(StateComponent.class);
        tm = ComponentMapper.getFor(TransformComponent.class);
        bc = ComponentMapper.getFor(BlockComponent.class);
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
                BlockComponent blockComponent = bc.get(block);

                //test for collision
                //Rectangle: Player and Block = their Bounds + Position.
                Rectangle playerRect = new Rectangle(
                        playerPos.pos.x,
                        playerPos.pos.y,
                        playerBounds.bounds.getWidth(),
                        playerBounds.bounds.getHeight());


                Rectangle blockRect = new Rectangle(
                        blockPos.pos.x, //- blockBounds.bounds.getWidth()
                        blockPos.pos.y,
                        blockBounds.bounds.getWidth() - playerBounds.bounds.getWidth() / 4,
                        blockBounds.bounds.getHeight());

                //Rectangle: Player and Block = their Bounds + Position.


                if (playerRect.overlaps(blockRect)) {
                    //TODO TEST and set velocity to zero for player
//                    //Moves Player to what ever side the player is closest to on intersection
//
//                    //Which side of the block should we teleport the player to: (Which ever side their the closest too)
//
//                    Point leftSideRect = new Point(((int) blockRect.getX()), ((int) (blockRect.getY() / 2)));
//                    Point rightSideRect = new Point(((int) (blockRect.getX() + blockRect.getWidth())), ((int) (blockRect.getY() / 2)));
//                    Point topSideRect = new Point(((int) (blockRect.getX() + (blockRect.getWidth() / 2))), ((int) blockRect.getY()));
//                    Point botSideRect = new Point(((int) (blockRect.getX() + (blockRect.getWidth() / 2))), ((int) (blockRect.getY() + blockRect.getHeight())));
//
//                    //Pythagorean theorem - Sees how close the player is to each of the points
//                    //Each point represents the center point on each of the 4 sides of the rects
//                    double x1 = leftSideRect.distance(playerPos.pos.x, playerPos.pos.y); //distFromLeftSide
//                    double x2 = rightSideRect.distance(playerPos.pos.x, playerPos.pos.y); //distFromRightSide
//                    double x3 = topSideRect.distance(playerPos.pos.x, playerPos.pos.y);//distFromTopSide
//                    double x4 = botSideRect.distance(playerPos.pos.x, playerPos.pos.y);//distFromBotSide
//
//
//                    //When teleporting a player only adjust their x-axis or their y-axis
//
//                    float buffer = 0.1f;
//
//
//                    if (x1 < x2 && x1 < x3 && x1 < x4) { //leftSide = true;
////                      //Teleport player to LeftSide
//                        playerPos.pos.set(blockRect.getX() - buffer, playerPos.pos.y, playerPos.pos.z);
//                        System.out.println("left");
//
//
//                    } else if (x2 < x1 && x2 < x3 && x2 < x4) { //rightSide = true;
//                        //Teleport player to RightSide
//                        playerPos.pos.set(blockRect.getX()+blockRect.getWidth() + buffer, playerPos.pos.y, playerPos.pos.z);
//                        System.out.println("right");
//
//
//                    } else if (x3 < x1 && x3 < x2 && x3 < x4) { //topSide = true;
//                        //Teleport player to TopSide
//                        playerPos.pos.set(playerPos.pos.x, blockRect.getY() - buffer, playerPos.pos.z);
//                        System.out.println("top");
//
//                    } else if (x4 < x1 && x4 < x2 && x4 < x3) { //botSide = true;
//                        //Teleport player to BotSide
//                        playerPos.pos.set(playerPos.pos.x, blockRect.getY()+blockRect.getHeight() + buffer, playerPos.pos.z);
//                        System.out.println("bot");
//
//                    }

                    if(blockComponent.isSolid()) {
                        playerPos.pos.set(playerPos.lastPosition); //TODO Uncomment to enable collision between blocks
                    } else{
                        blockComponent.setPlayerOverlaps(true);
                        System.out.println("True");
                    }
                } else {
                    blockComponent.setPlayerOverlaps(false);
                }
            }

        }
    }

}