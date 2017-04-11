package com.corb.moveboxandshow.world;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.corb.moveboxandshow.components.PlayerComponent;
import com.corb.moveboxandshow.components.RemovableComponent;
import com.corb.moveboxandshow.components.TransformComponent;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Top Left Corner is 0,0 on the map.
 * <p>
 * Ground level could be 12?
 */

public class WorldManager extends IteratingSystem {

    private Stack<Entity> removable;//entitiesScheduledForRemovable
    private Tile[][] allTiles;

    private LinkedList<LinkedList<Tile>> visibleTiles;
    private int drawDistanceRow = 8;
    private int drawDistanceCol = 8;

    private Entity player;
    private ComponentMapper<PlayerComponent> pm;
    private ComponentMapper<TransformComponent> tm;
    private ComponentMapper<RemovableComponent> rm;
    private PooledEngine engine;

    public WorldManager(Entity player, PooledEngine engine) {
        super(Family.all(
                PlayerComponent.class,
                TransformComponent.class).get());
        pm = ComponentMapper.getFor(PlayerComponent.class);
        tm = ComponentMapper.getFor(TransformComponent.class);
        this.player = player;
        this.engine = engine;
        removable = new Stack<Entity>();
        allTiles = new Tile[World.WORLD_HEIGHT][World.WORLD_WIDTH];
        initTileIDS();//ATM just test code :

        initVisibleTiles();        //Step 1 construct the linked list based on the players location ---DONE

    }

    private void initTileIDS() {
        //TODO Load from csv file, Each tile has a different id.
        //This way I can build maps using tiled. then export them to .csv files.

        //ATM just test code :
        for (int i = 0; i < allTiles.length; i++) {
            for (int j = 0; j < allTiles[i].length; j++) {
                float x = World.EDGE_WEST + j;
                float y = World.EDGE_NORTH - i;

                int tileID = (j % 2 == 0) ? 1 : 0;

                allTiles[i][j] = new Tile(tileID, x, y, i, j);
            }
        }
    }

    /**
     * You really have to be careful with the referencing to vectors and arrays....
     * Vectors.x can sometimes equal = rows
     * vectors.y can sometimes equal = cols
     * Don't get confused!
     */

    private void initVisibleTiles() {
        Vector3 player_Pos = tm.get(player).position;
        Vector2 drawDistanceStart = getTileIndexBasedOnPosition(player_Pos.x, player_Pos.y);
        System.out.println("Player pos: " + drawDistanceStart.x + " " + drawDistanceStart.y);

        //Center the player so he renders in the middle
        drawDistanceStart.x = (drawDistanceStart.x - drawDistanceCol/2)+2;
        drawDistanceStart.y = (drawDistanceStart.y - drawDistanceRow/2)+2;

        //Evaluate if starting tile position is valid:
        if (drawDistanceStart.x < 0) {
            drawDistanceStart.x = 0;
        } else if (drawDistanceStart.x + drawDistanceCol >= allTiles[0].length - 1) {
            drawDistanceStart.x = (allTiles[0].length - 1) - drawDistanceCol;
        }

        if (drawDistanceStart.y < 0) {
            drawDistanceStart.y = 0;
        } else if (drawDistanceStart.y + drawDistanceRow >= allTiles.length - 1) {
            drawDistanceStart.y = (allTiles.length - 1) - drawDistanceRow;
        }
        System.out.println("drawDistanceStart.x = " + drawDistanceStart.x + " " + "drawDistanceStart.y = " + drawDistanceStart.y);


        visibleTiles = new LinkedList<LinkedList<Tile>>();
        //Creates a Array of Linked Lists
        for (int i = 0; i < drawDistanceRow; i++) {
            visibleTiles.add(new LinkedList<Tile>());
            for (int j = 0; j < drawDistanceCol; j++) {
                int tileIndexRow = ((int) drawDistanceStart.y) + (i); // +i draws the row below
                int tileIndexCol = ((int) drawDistanceStart.x) + (j); // +j draws the tile next to the current tile.

                visibleTiles.get(i).add(allTiles[tileIndexRow][tileIndexCol]);
                visibleTiles.get(i).getLast().createEntity(engine); //Creates and adds Entity into the Pooled Engine
            }

        }
    }

    private Vector2 getTileIndexBasedOnPosition(float posX, float posY) {
        int row = ((int) (posY - World.EDGE_NORTH) * -1);
        int col = ((int) (posX - World.EDGE_WEST));

        return new Vector2(col, row);
    }

    public void update() {
        Vector2 playerTilePosition = pm.get(player).getTilePos();
        PlayerComponent playerComponent = pm.get(player);
        Vector3 player_Pos = tm.get(player).position;
        Vector2 player_Tile_Pos = getTileIndexBasedOnPosition(player_Pos.x, player_Pos.y);

        if (hasPlayerMoved(player_Tile_Pos)) {
            System.out.println("Method");
            int direction = getDeltaDirection(player_Tile_Pos);
            System.out.println(direction);

            adjustVisibleTiles(direction, player_Tile_Pos);
        }

        oldPlayerTilePos.set(getTileIndexBasedOnPosition(player_Pos.x, player_Pos.y));//update player tile position for next tick

        removeEntitysInQueue();
    }

    /**
     * 3 Methods are responsible for calling methods which create and destroy entitys dependent on the direction the player travels in
     * This means the player is everything off the players screen is been destroyed in order to increase performance
     */

    private Vector2 oldPlayerTilePos = new Vector2(World.PLAYER_START_X, World.PLAYER_START_Y);

    private boolean hasPlayerMoved(Vector2 currentTilePos) {
        //compares current pos to old oldPlayerTilePos
        return (currentTilePos.x != oldPlayerTilePos.x || currentTilePos.y != oldPlayerTilePos.y);
    }

    private final int DIRECTION_NORTH = 1;
    private final int DIRECTION_SOUTH = 2;
    private final int DIRECTION_EAST = 3;
    private final int DIRECTION_WEST = 4;

    private final int DIRECTION_NORTH_EAST = 5;
    private final int DIRECTION_NORTH_WEST = 6;
    private final int DIRECTION_SOUTH_EAST = 7;
    private final int DIRECTION_SOUTH_WEST = 8;

    private int getDeltaDirection(Vector2 player_Tile_Pos) {

        if (player_Tile_Pos.y < oldPlayerTilePos.y)
            if (player_Tile_Pos.x > oldPlayerTilePos.x)
                return DIRECTION_NORTH_EAST;
            else if (player_Tile_Pos.x < oldPlayerTilePos.x)
                return DIRECTION_NORTH_WEST;
            else
                return DIRECTION_NORTH;


        else if (player_Tile_Pos.y > oldPlayerTilePos.y)
            if (player_Tile_Pos.x > oldPlayerTilePos.x)
                return DIRECTION_SOUTH_EAST;
            else if (player_Tile_Pos.x < oldPlayerTilePos.x)
                return DIRECTION_SOUTH_WEST;
            else
                return DIRECTION_SOUTH;


        else if ((player_Tile_Pos.x > oldPlayerTilePos.x))
            return DIRECTION_EAST;
        else if (player_Tile_Pos.x < oldPlayerTilePos.x)
            return DIRECTION_WEST;


        return -1;
    }

    private void adjustVisibleTiles(int direction, Vector2 player_Tile_Pos) {
        //TODO Validation to stop program from crashing
        switch (direction) {
            case DIRECTION_NORTH:
                if(visibleTiles.getFirst().getFirst().getAllTilesIndexRow()!=0 && (player_Tile_Pos.y+(drawDistanceRow/2) < (allTiles.length-1) )) {
                    removeSouthernVisibleTiles();
                    addNorthernVisibleTiles();
                }
                break;

            case DIRECTION_SOUTH:

                if(visibleTiles.getLast().getFirst().getAllTilesIndexRow()!= allTiles.length-1 && (player_Tile_Pos.y-(drawDistanceRow/2)+1 > 0) ) {
                    removeNorthernVisibleTiles();
                    addSouthernVisibleTiles();
                }
                break;

            case DIRECTION_EAST:
                System.out.println("1: "+ (player_Tile_Pos.y));
                System.out.println("1: "+ visibleTiles.getFirst().getLast().getAllTilesIndexCol());

                if(visibleTiles.getFirst().getLast().getAllTilesIndexCol()!= allTiles[0].length-1 && player_Tile_Pos.x -(drawDistanceCol/2)+1 > 0) {
                    removeWesternVisibleTiles();
                    addEasternVisibleTiles();
                }
                break;

            case DIRECTION_WEST:
                if(visibleTiles.getFirst().getFirst().getAllTilesIndexCol()!= 0 && player_Tile_Pos.x + (drawDistanceCol/2) < (allTiles[0].length-1) ) { //&&
                    removeEasternVisibleTiles();
                    addWesternVisibleTiles();
                }
                break;

            case DIRECTION_NORTH_EAST:
                if(visibleTiles.getFirst().getFirst().getAllTilesIndexRow()!=0 && (player_Tile_Pos.y+(drawDistanceRow/2) < (allTiles.length-1) )) {
                    removeSouthernVisibleTiles();
                    addNorthernVisibleTiles();
                }
                if(visibleTiles.getFirst().getLast().getAllTilesIndexCol()!= allTiles[0].length-1 && player_Tile_Pos.x -(drawDistanceCol/2)+1 > 0) {
                    removeWesternVisibleTiles();
                    addEasternVisibleTiles();
                }
                break;

            case DIRECTION_NORTH_WEST:
                if(visibleTiles.getFirst().getFirst().getAllTilesIndexRow()!=0 && (player_Tile_Pos.y+(drawDistanceRow/2) < (allTiles.length-1) )) {
                    removeSouthernVisibleTiles();
                    addNorthernVisibleTiles();
                }
                if(visibleTiles.getFirst().getFirst().getAllTilesIndexCol()!= 0 && player_Tile_Pos.x + (drawDistanceCol/2) < (allTiles[0].length-1) ) { //&&
                    removeEasternVisibleTiles();
                    addWesternVisibleTiles();
                }
                break;

            case DIRECTION_SOUTH_EAST:
                if(visibleTiles.getLast().getFirst().getAllTilesIndexRow()!= allTiles.length-1 && (player_Tile_Pos.y-(drawDistanceRow/2)+1 > 0) ) {
                    removeNorthernVisibleTiles();
                    addSouthernVisibleTiles();
                }
                if(visibleTiles.getFirst().getLast().getAllTilesIndexCol()!= allTiles[0].length-1 && player_Tile_Pos.x -(drawDistanceCol/2)+1 > 0) {
                    removeWesternVisibleTiles();
                    addEasternVisibleTiles();
                }
                break;

            case DIRECTION_SOUTH_WEST:
                if(visibleTiles.getLast().getFirst().getAllTilesIndexRow()!= allTiles.length-1 && (player_Tile_Pos.y-(drawDistanceRow/2)+1 > 0) ) {
                    removeNorthernVisibleTiles();
                    addSouthernVisibleTiles();
                }
                if(visibleTiles.getFirst().getFirst().getAllTilesIndexCol()!= 0 && player_Tile_Pos.x + (drawDistanceCol/2) < (allTiles[0].length-1) ) { //&&
                    removeEasternVisibleTiles();
                    addWesternVisibleTiles();
                }

                break;

        }
    }


    /**
     * 4 Methods, (N,E,S,W) Used to add entitys to the removable queue
     * Since we're removing Tiles from the LinkedLists, we MUST use an iterator when removing elements (Its the safe way, ya got be extra safe ;))
     * On the down side an Iterator makes the code less readable :(
     * <p>
     * Removing the North Col and South Col, have different methods from Remove East and West Row
     */
    private void removeNorthernVisibleTiles() {
        LinkedList<Tile> topLayer = visibleTiles.pollFirst();
        removeTilesInIterator(topLayer.iterator());
    }

    private void removeSouthernVisibleTiles() {
        LinkedList<Tile> botLayer = visibleTiles.pollLast();
        removeTilesInIterator(botLayer.iterator());
    }

    private void removeTilesInIterator(Iterator<Tile> iterator) {
        while (iterator.hasNext()) {
            Tile tile = iterator.next();
            addToEntityRemovalQueue(tile);
            tile.setEntityToNull();
        }
    }


    private void removeEasternVisibleTiles() {
        //Removes Most Eastern Row Of Tiles
        Tile[] tilesToRemove = new Tile[visibleTiles.get(0).size()];

        for (int i = 0; i < tilesToRemove.length; i++) {
            LinkedList<Tile> row = visibleTiles.get(i);
            tilesToRemove[i] = row.pollLast();
        }
        removeTilesInArray(tilesToRemove);
    }

    private void removeWesternVisibleTiles() {
        //Removes Most Western Row Of Tiles
        Tile[] tilesToRemove = new Tile[visibleTiles.get(0).size()];

        for (int i = 0; i < tilesToRemove.length; i++) {
            LinkedList<Tile> row = visibleTiles.get(i);
            tilesToRemove[i] = row.pollFirst();
        }
        removeTilesInArray(tilesToRemove);
    }

    private void removeTilesInArray(Tile[] array) {
        for (Tile tile : array) {
            addToEntityRemovalQueue(tile);
            tile.setEntityToNull();
        }
    }

    /**
     * 4 Methods, (N,E,S,W) Used to add entitys to the Add Entitys/Tiles to the visibleTiles List
     */

    private void addNorthernVisibleTiles() {
        Tile tile = visibleTiles.getFirst().getFirst();
        int startingTileIndexRow = tile.getAllTilesIndexRow();
        int startingTileIndexCol = tile.getAllTilesIndexCol();

        visibleTiles.offerFirst(new LinkedList<Tile>()); //Create New linked list above previously held top row

        for (int j = 0; j < drawDistanceCol; j++) {
            int tileIndexRow = startingTileIndexRow - 1; // +i draws the row below
            int tileIndexCol = startingTileIndexCol + j; // +j draws the tile next to the current tile.

            visibleTiles.getFirst().add(allTiles[tileIndexRow][tileIndexCol]);
            visibleTiles.getFirst().getLast().createEntity(engine); //Creates and adds Entity into the Pooled Engine
        }
    }

    private void addSouthernVisibleTiles() {
        Tile tile = visibleTiles.getLast().getFirst();
        int startingTileIndexRow = tile.getAllTilesIndexRow();
        int startingTileIndexCol = tile.getAllTilesIndexCol();

        visibleTiles.offerLast(new LinkedList<Tile>()); //Create New linked list above previously held top row

        for (int j = 0; j < drawDistanceCol; j++) {
            int tileIndexRow = startingTileIndexRow + 1; // +i draws the row below
            int tileIndexCol = startingTileIndexCol + j; // +j draws the tile next to the current tile.

            visibleTiles.getLast().add(allTiles[tileIndexRow][tileIndexCol]);
            visibleTiles.getLast().getLast().createEntity(engine); //Creates and adds Entity into the Pooled Engine
        }
    }

    private void addEasternVisibleTiles() {
        int startingTileIndexRow = visibleTiles.getFirst().getLast().getAllTilesIndexRow();
        int startingTileIndexCol = visibleTiles.getFirst().getLast().getAllTilesIndexCol();

        for (int i = 0; i < drawDistanceRow; i++) {
            int tileIndexRow = startingTileIndexRow + i; // +i draws the row below
            int tileIndexCol = startingTileIndexCol + 1; // +j draws the tile next to the current tile.
            Tile newTile = allTiles[tileIndexRow][tileIndexCol];

            LinkedList<Tile> row = visibleTiles.get(i);
            row.offerLast(newTile);
            newTile.createEntity(engine);
        }

    }

    private void addWesternVisibleTiles() {
        int startingTileIndexRow = visibleTiles.getFirst().getFirst().getAllTilesIndexRow();
        int startingTileIndexCol = visibleTiles.getFirst().getFirst().getAllTilesIndexCol();

        for (int i = 0; i < drawDistanceRow; i++) {
            int tileIndexRow = startingTileIndexRow + i; // +i draws the row below
            int tileIndexCol = startingTileIndexCol - 1; // +j draws the tile next to the current tile.
            Tile newTile = allTiles[tileIndexRow][tileIndexCol];

            LinkedList<Tile> row = visibleTiles.get(i);
            row.offerFirst(newTile);
            newTile.createEntity(engine);
        }
    }

    //REMOVAL OF ENTITYS:
    //--------------------------------------------------
    private void addToEntityRemovalQueue(Tile tileToDelete) {
        if (tileToDelete.getEntity() != null) {
            removable.add(tileToDelete.getEntity());
        }
    }

    private void removeEntitysInQueue() {
        if (!(removable.empty())) {
            while (!(removable.empty())) {
                engine.removeEntity(removable.pop());
            }
        }
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

    }
}
