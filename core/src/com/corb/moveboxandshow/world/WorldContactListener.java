package com.corb.moveboxandshow.world;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

/**
 * Created by Calvin on 3/05/2017.
 */

class WorldContactListener implements ContactListener {

    private Entity player;

    public WorldContactListener(Entity player) {
        this.player = player;
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        System.out.println("beginContact");

        if (fixA.getUserData() == "head" || fixB.getUserData() == "head") {
            Fixture head = fixA.getUserData() == "head" ? fixA : fixB;
            Fixture object = head == fixA ? fixB : fixA;

            //if object is a falling rock, kill the player
        }

        if (fixA.getUserData() == "left" || fixB.getUserData() == "left") {
            Fixture head = fixA.getUserData() == "left" ? fixA : fixB;
            Fixture object = head == fixA ? fixB : fixA;


        }

        if (fixA.getUserData() == "right" || fixB.getUserData() == "right") {
            Fixture head = fixA.getUserData() == "right" ? fixA : fixB;
            Fixture object = head == fixA ? fixB : fixA;


        }

        if (fixA.getUserData() == "bottom" || fixB.getUserData() == "bottom") {
            Fixture head = fixA.getUserData() == "bottom" ? fixA : fixB;
            Fixture object = head == fixA ? fixB : fixA;




            //TODO if player hits ground with alot of speed give them falling damage

            //if(player.getSpeed > DangerousFallingSpeed) health--;
        }
    }

    @Override
    public void endContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        System.out.println("endContact");

        if (fixA.getUserData() == "head" || fixB.getUserData() == "head") {
            Fixture head = fixA.getUserData() == "head" ? fixA : fixB;
            Fixture object = head == fixA ? fixB : fixA;

            //if object is a falling rock, kill the player.ddddd
        }

        if (fixA.getUserData() == "left" || fixB.getUserData() == "left") {
            Fixture head = fixA.getUserData() == "left" ? fixA : fixB;
            Fixture object = head == fixA ? fixB : fixA;

        }

        if (fixA.getUserData() == "right" || fixB.getUserData() == "right") {
            Fixture head = fixA.getUserData() == "right" ? fixA : fixB;
            Fixture object = head == fixA ? fixB : fixA;


        }

        if (fixA.getUserData() == "bottom" || fixB.getUserData() == "bottom") {
            Fixture head = fixA.getUserData() == "bottom" ? fixA : fixB;
            Fixture object = head == fixA ? fixB : fixA;

        }

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
