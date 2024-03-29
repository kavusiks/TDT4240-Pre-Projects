package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Helicopter {
    private int movementSpeed;
    //private ArrayList<Integer> speedVariationSubtrahends = new ArrayList<>(Arrays.asList(10,20,30,40,50,60));
    private Vector2 position;
    private Texture heliAllFour;
    private Animation heliAnimation;
    private Rectangle screenBounds;
    private int speedFactor=0;
    private int movementVertical;
    private int movementHorizontal;

    public Helicopter(int x, int y) {
        //movementSpeed = MOVEMENT_SPEED_BASE - speedVariationSubtrahends.get(new Random().nextInt(speedVariationSubtrahends.size()));
        movementSpeed = 500* 1/(int)Math.floor(Math.random()*(10-1+1)+1);
        System.out.println(movementSpeed);
        position = new Vector2(x,y);
        heliAllFour = new Texture("animatedHeli.PNG");
        heliAnimation = new Animation(new TextureRegion(heliAllFour),4, 0.1f );
        //heliTextureRegion = new TextureRegion(heliAllFour,0,0, heliAllFour.getWidth(), heliAllFour.getHeight());
        screenBounds = new Rectangle(-5, -5,Gdx.graphics.getWidth()+5, Gdx.graphics.getHeight()+5);
        //System.out.println("nå: " + getTexture().getRegionHeight() + " " + getTexture().getRegionWidth());
        movementVertical = movementSpeed;
        movementHorizontal = movementSpeed;
    }

    public void update(float dt) {
        heliAnimation.update(dt);
        if((position.x + heliAnimation.getFrame().getRegionWidth()) > (screenBounds.x + screenBounds.width) || position.x < 0) {
            movementHorizontal = movementHorizontal *-1;

        }

        if ((position.y + heliAnimation.getFrame().getRegionHeight()) > (screenBounds.y + screenBounds.height) || position.y < 0) {
            movementVertical = movementVertical *-1;

        }
/*
        int randomSpeedFactor = getSpeedFactor();
        int multiplier = (int) (dt*(1/randomSpeedFactor));
        System.out.println(multiplier);

 */

        position.add(movementHorizontal *dt, movementVertical *dt);

    }
/*
    private int getSpeedFactor() {
        if (speedFactor==0) {
            speedFactor = (int)Math.floor(Math.random()*(10-1+1)+1);
        }
        return speedFactor;
    }

 */

    public Vector2 getPosition() {
        return this.position;
    }

    public TextureRegion getTexture() {

        return heliAnimation.getFrame();
    }

    public int getMovementHorizontal() {
        return this.movementHorizontal;
    }

    public Boolean isColliding(Helicopter otherHeli) {
        return otherHeli.getObjectBounds().overlaps(this.getObjectBounds());
    }

    public void hasCollided() {
        movementHorizontal = movementHorizontal*-1;
        movementVertical = movementVertical*-1;

    }

    public Rectangle getObjectBounds() {
        return new Rectangle(this.getPosition().x, this.getPosition().y, this.getTexture().getRegionWidth(), this.getTexture().getRegionHeight());
    }


    public void dispose() {
        heliAllFour.dispose();
    }

}
