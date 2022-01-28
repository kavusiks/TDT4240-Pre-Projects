package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Helicopter {
    private static final int MOVEMENT_SPEED = 150;
    private Vector2 position;
    private Texture heliAllFour;
    private Animation heliAnimation;
    private Rectangle screenBounds;

    public Helicopter(int x, int y) {
        position = new Vector2(x,y);
        heliAllFour = new Texture("animatedHeli.PNG");
        heliAnimation = new Animation(new TextureRegion(heliAllFour),4, 0.1f );
        //heliTextureRegion = new TextureRegion(heliAllFour,0,0, heliAllFour.getWidth(), heliAllFour.getHeight());
        screenBounds = new Rectangle(0, 0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    private int movementVertical = MOVEMENT_SPEED;
    private int movementHorizontal = MOVEMENT_SPEED;
    public void update(float dt) {
        heliAnimation.update(dt);
        if((position.x + heliAnimation.getFrame().getRegionWidth()) > (screenBounds.x + screenBounds.width) || position.x < 0) {
            movementHorizontal = movementHorizontal *-1;

        }

        if ((position.y + heliAnimation.getFrame().getRegionHeight()) > (screenBounds.y + screenBounds.height) || position.y < 0) {
            movementVertical = movementVertical *-1;

        }

        position.add(movementHorizontal *dt, movementVertical *dt);

    }

    public Vector2 getPosition() {
        return this.position;
    }

    public TextureRegion getTexture() {

        return heliAnimation.getFrame();
    }

    public int getMovementHorizontal() {
        return this.movementHorizontal;
    }


    public void dispose() {
        heliAllFour.dispose();
    }

}
