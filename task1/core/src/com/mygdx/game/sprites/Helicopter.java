package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.Vector;

public class Helicopter {
    private Vector2 position;
    private Texture heli;

    public Helicopter(int x, int y) {
        position = new Vector2(x,y);
        heli = new Texture("attackhelicopter.PNG");
    }

    public void update(float dt) {

    }

    public Vector2 getPosition() {
        return this.position;
    }

    public Texture getTexture() {
        return this.heli;
    }

    public void dispose() {
        heli.dispose();
    }

}
