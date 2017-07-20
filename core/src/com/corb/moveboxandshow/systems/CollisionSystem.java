package com.corb.moveboxandshow.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.corb.moveboxandshow.components.TilePositionComponent;
import com.corb.moveboxandshow.world.GameWorld;
import com.corb.moveboxandshow.components.BlockComponent;
import com.corb.moveboxandshow.components.PlayerComponent;
import com.corb.moveboxandshow.components.UserInputComponent;
import com.corb.moveboxandshow.components.MovementComponent;
import com.corb.moveboxandshow.components.StateComponent;
import com.corb.moveboxandshow.components.TransformComponent;
import com.corb.moveboxandshow.components.BoundsComponent;
import com.corb.moveboxandshow.world.GameWorldManager;
import com.corb.moveboxandshow.world.Tile;

import java.util.LinkedList;

/**
 * Created by Calvin on 23/03/2017.
 */

public class CollisionSystem extends EntitySystem {

    private ComponentMapper<BoundsComponent> bm;
    private ComponentMapper<MovementComponent> mm;
    private ComponentMapper<StateComponent> sm;
    private ComponentMapper<TransformComponent> tm;
    private ComponentMapper<BlockComponent> bc;
    private ComponentMapper<PlayerComponent> pc;

    private Engine engine;
    private GameWorld world;
    private ImmutableArray<Entity> players;

    public CollisionSystem(GameWorld world) {
        this.world = world;

        bm = ComponentMapper.getFor(BoundsComponent.class);
        mm = ComponentMapper.getFor(MovementComponent.class);
        sm = ComponentMapper.getFor(StateComponent.class);
        tm = ComponentMapper.getFor(TransformComponent.class);
        bc = ComponentMapper.getFor(BlockComponent.class);
        pc = ComponentMapper.getFor(PlayerComponent.class);
    }

    @Override
    public void addedToEngine(Engine engine) {
        this.engine = engine;

        players = engine.getEntitiesFor(Family.all(PlayerComponent.class,
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


    /**
     * The Variable "Vector2 playerAccuratePos" shows three things.
     * The axis the player is on x/y
     * The whole number represents which tile the player is on.
     * The decimal represents the relative position to center of the tile (0.5 is the center, 0 being Top/Left, .99 being Bot/Right
     */

    private void playerAndBlocks() {
        for (int i = 0; i < players.size(); i++) {
            Entity player = players.get(i);

            //dependent on their state I can do different things
            //eg allow or deny them from passing through the object
            StateComponent playerState = sm.get(player);
            MovementComponent playerMov = mm.get(player);
            BoundsComponent playerBounds = bm.get(player);
            TransformComponent playerPos = tm.get(player);
            PlayerComponent playerCom = pc.get(player);

            LinkedList<LinkedList<Tile>> visibleTiles = world.getWorldManager().getVisibleTiles();

            Vector2 playerTilePos = getTileIndexBasedOnPosition(playerCom.getTilePos().x, playerCom.getTilePos().y - 1);


            Tile tilePlayerIsOn = null;
            int index_Row;
            int index_Col;
            for (index_Row = 0; index_Row < visibleTiles.size(); index_Row++) {
                for (index_Col = 0; index_Col < visibleTiles.get(index_Row).size(); index_Col++) {
                    Tile tile = visibleTiles.get(index_Row).get(index_Col);
                    Vector2 vector2 = getTileIndexBasedOnPosition(tile.getPOS_X(), tile.getPOS_Y());
                    if (vector2.x == playerTilePos.x && vector2.y == playerTilePos.y) {
                        tilePlayerIsOn = tile;
//                        System.out.println("---------------------------------------------------------------");
//                        System.out.println("Player Tile Pos: " + playerTilePos.x + " , " + playerTilePos.y);
//                        System.out.println("Player Pos " + playerCom.getTilePos().x + "  ,  " + playerCom.getTilePos().y);
//                        System.out.println("index: Row -> Col " + index_Row + "  ,  " + index_Col);
                        break;
                    }
                }
            }


            Vector2 playerAccuratePos = playerCom.getTilePos();

            if (tilePlayerIsOn == null || tilePlayerIsOn.getAllTilesIndexCol() == world.getWorldManager().getAllTilesMaxCol() - 1 ||
                    tilePlayerIsOn.getAllTilesIndexCol() == 0 ||
                    tilePlayerIsOn.getAllTilesIndexRow() == world.getWorldManager().getAllTilesMaxRow() - 1 ||
                    tilePlayerIsOn.getAllTilesIndexRow() == 0) {
                System.out.println("Outside Map");
            } else {
                Vector2 deltaPos = new Vector2((playerAccuratePos.x - (int) playerAccuratePos.x), playerAccuratePos.y - ((int) playerAccuratePos.y)); //Converts playerAccuratePos to range between 0-1

                Tile[][] allTiles = world.getWorldManager().getAllTiles();
                //------------------X Axis Collision Checking-----------------------------------------

                //------------------Check Collision To the Left---------------------------------------
//                System.out.println(deltaPos.y + "  :  " + PlayerComponent.HEIGHT / 2);
                if (deltaPos.x < PlayerComponent.WIDTH / 2) {
                    //Tiles to the Left of the player
                    Tile centerLeft = allTiles[tilePlayerIsOn.getAllTilesIndexRow()][tilePlayerIsOn.getAllTilesIndexCol() - 1]; //visibleTiles.get(((int) visibleTileIndexPlayerIsOn.x)).get(((int) visibleTileIndexPlayerIsOn.y)-1); //
                    Tile topLeft = allTiles[tilePlayerIsOn.getAllTilesIndexRow() - 1][tilePlayerIsOn.getAllTilesIndexCol() - 1]; //visibleTiles.get(((int) visibleTileIndexPlayerIsOn.x)-1).get(((int) visibleTileIndexPlayerIsOn.y)-1);
                    Tile botLeft = allTiles[tilePlayerIsOn.getAllTilesIndexRow() + 1][tilePlayerIsOn.getAllTilesIndexCol() - 1]; //visibleTiles.get(((int) visibleTileIndexPlayerIsOn.x)+1).get(((int) visibleTileIndexPlayerIsOn.y)-1);//
                    boolean collision = false;
                    //TopLeft
                    if (topLeft.getEntity() != null) {

                        if (topLeft.getEntity().getComponent(BlockComponent.class).isSolid()) { //(centerLeft.getID() == Tile.STONE_BLOCK)

                            if (deltaPos.y > PlayerComponent.HEIGHT / 2) {
                                collision = true;
                            }
                        }
                    }
                    //CenterLeft
                    if (centerLeft.getEntity() != null) {
                        if (centerLeft.getEntity().getComponent(BlockComponent.class) != null) {
                        }
                        if (centerLeft.getEntity().getComponent(BlockComponent.class).isSolid()) { //(centerLeft.getID() == Tile.STONE_BLOCK)
                            collision = true;
                        } else {
                        }
                    }
                    //BotLeft
                    if (botLeft.getEntity() != null) {
                        if (botLeft.getEntity().getComponent(BlockComponent.class).isSolid()) { //(centerLeft.getID() == Tile.STONE_BLOCK)
                            if (deltaPos.y < PlayerComponent.HEIGHT / 2) {
                                collision = true;
                            }
                        }
                    }

                    //On Event of collision handle outcome
                    if (collision) {
                        Vector2 newPosition = playerCom.getTilePos();
                        newPosition.x = ((int) newPosition.x);//Round position to whole number
                        newPosition.x += (PlayerComponent.WIDTH / 2) - 0.5f;
                        //Change Players Position to just outside the collision zone.
                        //The player will now be as close to they can get without actually colliding with the solid object
                        //The amount I will change the player's position be the same as in the condition.
                        playerPos.pos.x = newPosition.x;
                    }

                }

                //------------------Check Collision To the Right---------------------------------------
                if (deltaPos.x > 1 - (PlayerComponent.WIDTH / 2)) {
                    //Tiles to the Right of the player
                    Tile centerRight = allTiles[tilePlayerIsOn.getAllTilesIndexRow()][tilePlayerIsOn.getAllTilesIndexCol() + 1];
                    Tile topRight = allTiles[tilePlayerIsOn.getAllTilesIndexRow() - 1][tilePlayerIsOn.getAllTilesIndexCol() + 1];
                    Tile botRight = allTiles[tilePlayerIsOn.getAllTilesIndexRow() + 1][tilePlayerIsOn.getAllTilesIndexCol() + 1];

                    boolean collision = false;
                    //TopRight
                    if (topRight.getEntity() != null) {
                        if (topRight.getEntity().getComponent(BlockComponent.class).isSolid()) { //(centerLeft.getID() == Tile.STONE_BLOCK)
                            if (deltaPos.y > PlayerComponent.HEIGHT / 2) {
                                collision = true;
                            }
                        }
                    }
                    //CenterRight
                    if (centerRight.getEntity() != null) {
                        if (centerRight.getEntity().getComponent(BlockComponent.class).isSolid()) { //(centerLeft.getID() == Tile.STONE_BLOCK)
                            collision = true;
                        }
                    }
                    //BotRight
                    if (botRight.getEntity() != null) {
                        if (botRight.getEntity().getComponent(BlockComponent.class).isSolid()) { //(centerLeft.getID() == Tile.STONE_BLOCK)
                            if (deltaPos.y < PlayerComponent.HEIGHT / 2) {
                                collision = true;
                            }
                        }
                    }

                    //On Event of collision handle outcome
                    if (collision) {
                        Vector2 newPosition = playerCom.getTilePos();//Round position to whole number
                        newPosition.x = ((int) newPosition.x);
                        newPosition.x += (PlayerComponent.WIDTH / 2);
                        //Change Players Position to just outside the collision zone.
                        //The player will now be as close to they can get without actually colliding with the solid object
                        //The amount I will change the player's position be the same as in the condition.
                        playerPos.pos.x = newPosition.x;
                    }


                }

                //------------------COMPLETED X Axis Collision Checking -----------------------------------------


                //TODO Check Collision To the Top:


                //TODO Check Collision To the Bot:

            }
            //The Players Total width is 0.5 (giving us a radius of 0.25 on the x-axis.
            //This means if the Players position to the center of the tile is <0.25 or >0.75 the player is overlapping the neighbor square
            //In the event of this we must check if the neighboring square is solid or hollow.
            //If its hollow nothing needs to be done unless (its a shop/interactive hollow tile) - Adding in future
            //If its solid, simply set the players x-axis to 0.25 or 0.75.


            // (This can be optimised so only check for boxes which are active)
            //loop through all blocks checking for a collision between player and boxes
//            for (int j = 0; j < blocks.size(); j++) {
//                Entity block = blocks.get(j);
//
//                BoundsComponent blockBounds = bm.get(block);
//                TransformComponent blockPos = tm.get(block);
//                BlockComponent blockComponent = bc.get(block);
//
//                //test for collision
//                //Rectangle: Player and Block = their Bounds + Position.
//                Rectangle playerRect = new Rectangle(
//                        playerPos.pos.x,
//                        playerPos.pos.y,
//                        playerBounds.bounds.getWidth(),
//                        playerBounds.bounds.getHeight());
//
//
//                Rectangle blockRect = new Rectangle(
//                        blockPos.pos.x, //- blockBounds.bounds.getWidth()
//                        blockPos.pos.y,
//                        blockBounds.bounds.getWidth() - playerBounds.bounds.getWidth() / 4,
//                        blockBounds.bounds.getHeight());
//
//                //Rectangle: Player and Block = their Bounds + Position.
//
//                System.out.println("2");
//
//                if (playerRect.overlaps(blockRect)) {
//                    blockComponent.setRemove(true);
//                    System.out.println("1");
//
//                }
//            }

        }
    }

    private Vector2 getTileIndexBasedOnPosition(float posX, float posY) {
        int row = ((int) (posY - GameWorld.EDGE_NORTH) * -1);
        int col = ((int) (posX - GameWorld.EDGE_WEST));

        return new Vector2(col, row);
    }

}