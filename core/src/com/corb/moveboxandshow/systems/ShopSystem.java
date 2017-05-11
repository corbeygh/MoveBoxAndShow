package com.corb.moveboxandshow.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.corb.moveboxandshow.Assets;
import com.corb.moveboxandshow.Main;
import com.corb.moveboxandshow.components.BlockComponent;
import com.corb.moveboxandshow.components.BoundsComponent;
import com.corb.moveboxandshow.components.MovementComponent;
import com.corb.moveboxandshow.components.ShopComponent;
import com.corb.moveboxandshow.components.TransformComponent;
import com.corb.moveboxandshow.world.GameWorld;

/**
 * Created by Calvin on 25/03/2017.
 */

public class ShopSystem extends IteratingSystem {

    private ComponentMapper<BoundsComponent> bm;
    private ComponentMapper<BlockComponent> bc;
    private ComponentMapper<TransformComponent> tm;
    private ComponentMapper<ShopComponent> sc;
    private ComponentMapper<MovementComponent> mm;


    private GameWorld gameWorld;
    private Main game;
    private Entity player = null;

    public ShopSystem(GameWorld gameWorld, Main game) {
        super(Family.all(
                BoundsComponent.class,
                BlockComponent.class,
                TransformComponent.class,
                ShopComponent.class,
                MovementComponent.class).get());
        this.gameWorld = gameWorld;
        this.game = game;
        bm = ComponentMapper.getFor(BoundsComponent.class);
        bc = ComponentMapper.getFor(BlockComponent.class);
        tm = ComponentMapper.getFor(TransformComponent.class);
        sc = ComponentMapper.getFor(ShopComponent.class);
        mm = ComponentMapper.getFor(MovementComponent.class);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if ((player == null) && (gameWorld.getPlayer() != null)) {
            player = gameWorld.getPlayer();
        }

        if(currentStage!=null) draw();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        if (player == null){
            System.out.println("null :D");

            return;
        }

        if (bc.get(entity).isPlayerOverlaps()) {
            MovementComponent playerMov = mm.get(player);
            TransformComponent position = tm.get(player);
            System.out.println("Open Shop :D");

            if(position.pos.x == position.lastPosition.x && position.pos.y ==position.lastPosition.y){

//                switch (sc.get(entity).getShopID()){
//                    case ShopComponent.ORE_SHOP_ID:
//                        openMiningShop();
//                        break;
//                    case ShopComponent.PICKAXE_SHOP_ID:
////                        openPickaxeShop();
//                        break;
//                }
            }

        }

        //TODO Next Goal: Only make It say Open Shop if player isn't moving, auto close if player isn't moving

        //open shop gui
    }

    private OrthographicCamera camera;
    private Viewport viewPort;

    private boolean miningShopGUIOpen = false;
    private Stage miningShopStage;

    private Stage currentStage;


    private void openMiningShop(){
        camera = new OrthographicCamera();
        viewPort = new FitViewport(Assets.W_WIDTH, Assets.W_HEIGHT, camera);
        miningShopStage = new Stage(viewPort, game.batch);


        //TODO Create shop gui like so:
//        Table table = new Table();
//        table.left().bottom();
//
//
//        table.add();
//        table.add(upImg).size(upImg.getWidth(), upImg.getHeight());
//        table.row();
//        table.add();
//        table.row().pad(5, 5, 5, 5);
//        table.add(leftImg).size(leftImg.getWidth(), leftImg.getHeight());
//        table.add();
//        table.add(rightImg).size(rightImg.getWidth(), rightImg.getHeight());
//        table.row().padBottom(5);
//        table.add();
//        table.add(downImg).size(downImg.getWidth(), downImg.getHeight());
//        table.add();
//
//        stage.addActor(table);


    }

    private void draw() {
        currentStage.draw();
    }
}