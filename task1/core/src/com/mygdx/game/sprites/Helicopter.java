package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Helicopter {
    private static final int MOVEMENT_SPEED = 150;
    private Vector2 position;
    private Texture heli;
    private Vector2 velocity;
    private Sprite heliSprite;
    private Rectangle screenBounds;

    public Helicopter(int x, int y) {
        position = new Vector2(x,y);
        heli = new Texture("attackhelicopter.PNG");
        heliSprite  = new Sprite(heli);
        screenBounds = new Rectangle(0, 0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight() );
    }

    private boolean changeDirectionVertical = false;
    private boolean changeDirectionHorizonal = false;
    private int movementVertical = MOVEMENT_SPEED;
    private int movementHorizontal = MOVEMENT_SPEED;
    public void update(float dt) {
       /* velocity.add(MOVEMENT_SPEED, 0);
        velocity.scl(dt);
        position.add(velocity.x, 0);
        velocity.scl(1/dt);

        */
        if((position.x + heli.getWidth()) > (screenBounds.x + screenBounds.width) || position.x < 0) {
            movementVertical = movementVertical *-1;
            heliSprite.flip(true, false);

        }

        if ((position.y + heli.getHeight()) > (screenBounds.y + screenBounds.height) || position.y < 0) {
            movementHorizontal = movementHorizontal *-1;
        }

        //int a = 5 + (int)(Math.random() * ((100 - 50) + 1));
        //int b = 5 + (int)(Math.random() * ((100 - 50) + 1));


        position.add(movementVertical *dt, movementHorizontal *dt);



        //System.out.printf("heliX: " + position.x + " heliY: " + position.y);
        //System.out.printf(" screen edges: [" + screenBounds.x + " " + screenBounds.y + " " + (screenBounds.x + screenBounds.width) + " " + (screenBounds.y + screenBounds.height) + " direction: " + changedDirection + ". " );

    }

    public Vector2 getPosition() {
        return this.position;
    }

    public Texture getTexture() {

        return heliSprite.getTexture();
    }

    public void dispose() {
        heli.dispose();
    }

}
