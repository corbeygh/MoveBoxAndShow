package com.corb.moveboxandshow.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.corb.moveboxandshow.components.PlayerComponent;
import com.corb.moveboxandshow.components.TilePositionComponent;
import com.corb.moveboxandshow.components.UserInputComponent;
import com.corb.moveboxandshow.components.MovementComponent;
import com.corb.moveboxandshow.components.StateComponent;
import com.corb.moveboxandshow.components.TransformComponent;
import com.corb.moveboxandshow.world.World;

/**
 * Created by Calvin on 22/03/2017.
 */

public class PlayerSystem extends IteratingSystem {

    private static final Family family = Family.all(PlayerComponent.class,
            UserInputComponent.class,
            StateComponent.class,
            TransformComponent.class,
            MovementComponent.class,
            TilePositionComponent.class).get();

    private ComponentMapper<PlayerComponent> pm;
    private ComponentMapper<UserInputComponent> ui;
    private ComponentMapper<StateComponent> sm;
    private ComponentMapper<TransformComponent> trm;
    private ComponentMapper<MovementComponent> mm;
    private ComponentMapper<TilePositionComponent> tpm;

    private World world;

    public PlayerSystem(World world) {
        super(family);
        this.world = world;
        pm = ComponentMapper.getFor(PlayerComponent.class);
        ui = ComponentMapper.getFor(UserInputComponent.class);
        sm = ComponentMapper.getFor(StateComponent.class);
        trm = ComponentMapper.getFor(TransformComponent.class);
        mm = ComponentMapper.getFor(MovementComponent.class);
        tpm = ComponentMapper.getFor(TilePositionComponent.class);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PlayerComponent player = pm.get(entity);
        UserInputComponent userInput = ui.get(entity);
        StateComponent state = sm.get(entity);
        TransformComponent pos = trm.get(entity);
        MovementComponent mov = mm.get(entity);
        TilePositionComponent tilePos = tpm.get(entity);
//        System.out.println("x "+pos.position.x+ " y "+pos.position.y);

        //Based on what state the player is in process their input:
        if (userInput.moveLeft) {
            mov.velocity.x = -1 * PlayerComponent.MOVE_VELOCITY;
        } else if (userInput.moveRight) {
            mov.velocity.x = PlayerComponent.MOVE_VELOCITY;
        } else {
            mov.velocity.x = 0;
        }

        if (userInput.moveUp) {
            mov.velocity.y = PlayerComponent.MOVE_VELOCITY;
        } else if (userInput.moveDown) {
            mov.velocity.y = -1 * PlayerComponent.MOVE_VELOCITY;
        } else {
            mov.velocity.y = 0;
        }
        player.setTilePos(tilePos.getTilePos());

    }


//    private void updateIndex(PlayerComponent player) {
//        int playerX = (int) player.getTilePos().x;
//        int playerY = (int) player.getTilePos().y;
//
//        int indexX = world.getIndexCol();
//        int indexY = world.getIndexRow();
//        MapChunk mapChunk = world.getMapChunk();
//
//        if (playerX < mapChunk.getTileStartX()) {
//            if (indexX != 0) {
//                System.out.println("1");
//                world.setOldIndexCol(world.getIndexCol());
//                world.setIndexCol(world.getIndexCol() - 1);
//                world.setIndexChanged(true);
//            }
//
//        } else if (playerX > mapChunk.getTileEndX()) {
//            if (indexX != World.getNumberOfChunks() - 1) {
//                System.out.println("2");
//                world.setOldIndexCol(world.getIndexCol());
//                world.setIndexCol(world.getIndexCol() + 1);
//                world.setIndexChanged(true);
//            }
//
//        } else if (playerY < mapChunk.getTileStartY()) {
//            if (indexY != 0) {
//                System.out.println("3");
//                world.setOldIndexRow(world.getIndexRow());
//                world.setIndexRow(world.getIndexRow() - 1);
//                world.setIndexChanged(true);
//            }
//
//        } else if (playerY > mapChunk.getTileEndY()) {
//            if (indexY != World.getNumberOfChunks() - 1) {
//                System.out.println("4");
//                world.setOldIndexRow(world.getIndexRow());
//                world.setIndexRow(world.getIndexRow() + 1);
//                world.setIndexChanged(true);
//            }
//        }
//    }
}
