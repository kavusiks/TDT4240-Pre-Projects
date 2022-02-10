package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Helicopter {
    private static final int MOVEMENT_SPEED = 150;
    private static final int STARTING_POINT_X = 50;
    private static final int STARTING_POINT_Y = 200;

    private Vector2 position;
    private Texture heli;
    private TextureRegion heliTextureRegion;
    private Rectangle screenBounds;
    private int movementVertical;
    private int movementHorizontal;

    private static Helicopter INSTANCE;

    private Helicopter() {
        movementVertical = MOVEMENT_SPEED;
        movementHorizontal = MOVEMENT_SPEED;
        position = new Vector2(STARTING_POINT_X, STARTING_POINT_Y);
        heli = new Texture("attackhelicopter.png");
        heliTextureRegion = new TextureRegion(heli,0,0, heli.getWidth(), heli.getHeight());
        screenBounds = new Rectangle(0, 0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight() );
    }

    public static Helicopter getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new Helicopter();
            System.out.println("INSTANCE VREATER");
        }
        return INSTANCE;
    }

    public void update(float dt) {
        if((position.x + heli.getWidth()) > (screenBounds.x + screenBounds.width) || position.x < 0) {
            movementHorizontal = movementHorizontal *-1;

        }

        if ((position.y + heli.getHeight()) > (screenBounds.y + screenBounds.height) || position.y < 0) {
            movementVertical = movementVertical *-1;

        }

        position.add(movementHorizontal *dt, movementVertical *dt);

    }

    public Vector2 getPosition() {
        return this.position;
    }

    public Texture getTexture() {

        return heliTextureRegion.getTexture();
    }

    public int getMovementHorizontal() {
        return this.movementHorizontal;
    }


    public void dispose() {
        heli.dispose();
    }

}
