package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;

public class Helicopter {
    private static final int MOVEMENT_SPEED = 150;
    private Vector2 position;
    private Texture heli;
    private Texture flippedHeli;
    private TextureRegion heliTextureRegion;
    private TextureRegion flippedHeliTextureRegion;
    private Vector2 velocity;
    //private Sprite heliSprite;
    private Rectangle screenBounds;
    private Sprite sprite;

    public Helicopter(int x, int y) {
        position = new Vector2(x,y);
        heli = new Texture("attackhelicopter.PNG");
        //flippedHeli = new Texture("flippedattackhelicopter.png");
        heliTextureRegion = new TextureRegion(heli,0,0, heli.getWidth(), heli.getHeight());
        //flippedHeliTextureRegion = new TextureRegion(flippedHeli, 0,0, heli.getWidth(), heli.getHeight());
        sprite = new Sprite(heli, -heli.getWidth(), heli.getHeight());
        screenBounds = new Rectangle(0, 0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight() );
    }

    private int movementVertical = MOVEMENT_SPEED;
    private int movementHorizontal = MOVEMENT_SPEED;
    public void update(float dt) {
       /* if((position.x + heli.getWidth()) > (screenBounds.x + screenBounds.width) || position.x < 0) {

            movementHorizontal = movementHorizontal *-1;

        }

        if ((position.y + heli.getHeight()) > (screenBounds.y + screenBounds.height) || position.y < 0) {
            movementVertical = movementVertical *-1;

        }

        position.add(movementHorizontal *dt, movementVertical *dt);

        */
        if(position.x< screenBounds.x) {
            position.x=0;
        }
        if(position.x> screenBounds.x + heli.getWidth()) {
            position.x=screenBounds.x+ MyGdxGame.WIDTH -heli.getWidth();
        }
        if(position.y< screenBounds.y) {
            position.y=0;
        }
        if(position.y> screenBounds.y+MyGdxGame.HEIGHT - heli.getHeight()) {
            position.y=screenBounds.y+ MyGdxGame.HEIGHT -heli.getHeight();
        }

    }
    public void moveTo(int x, int y) {
        position.x = x;
        position.y = y;
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
