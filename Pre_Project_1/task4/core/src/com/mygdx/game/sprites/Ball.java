package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Ball {
    private static final Color BALL_COLOR= Color.WHITE;
    public static final int BALL_RADIUS = 5;
    private ArrayList<Integer> randomDirectionVariables = new ArrayList<>(Arrays.asList(1,-1));
    private Vector2 position;
    private Texture ball;
    private int movementSpeed;
    private int movementVertical;
    private int movementHorizontal;


    public Ball(int x, int y) {
        //movementSpeed = 500* 1/(int)Math.floor(Math.random()*(10-1+1)+1);
        movementSpeed = 150 * getRandomDirectionMultiplier();
        movementVertical = movementSpeed * getRandomDirectionMultiplier();
        movementHorizontal = movementSpeed * getRandomDirectionMultiplier();
        ball = getTexture();
        position = new Vector2(x, y);
    }

    private int getRandomDirectionMultiplier() {
        return randomDirectionVariables.get(new Random().nextInt(randomDirectionVariables.size()));
    }

    private Pixmap getPixmapCircle(int radius){
        Pixmap pixmap=new Pixmap(radius*2+1, radius*2+1, Pixmap.Format.RGBA8888);
        pixmap.setColor(BALL_COLOR);
        pixmap.fillCircle(radius,radius,radius);
        //pixmap.fillRectangle(0,0, pixmap.getWidth(), pixmap.getHeight());
        return pixmap;
    }
    public Texture getTexture() {
        return new Texture(getPixmapCircle(BALL_RADIUS));
    }

    public void pauseBall() {
        position.x = MyGdxGame.WIDTH/2-Ball.BALL_RADIUS;
        position.y = MyGdxGame.HEIGHT/2-Ball.BALL_RADIUS;
        movementSpeed = 0;
        movementVertical = 0;
        movementHorizontal = 0;
    }

    public void restartBall() {
        movementSpeed = 150 * getRandomDirectionMultiplier();
        movementVertical = movementSpeed * getRandomDirectionMultiplier();
        movementHorizontal = movementSpeed * getRandomDirectionMultiplier();
    }

    public void update(float dt) {
        isHittingScreenBounds();
        position.add(movementHorizontal *dt, movementVertical *dt);
    }

    public void isHittingScreenBounds() {
        if(getPosition().y >= MyGdxGame.HEIGHT || getPosition().y <=0) {
            movementVertical = movementVertical * -1;
        }
    }

    public boolean detectPlayerLoss(AbstractPlayer player) {
        if (player.getPosition().x > MyGdxGame.WIDTH/2) {
            if(getPosition().x > player.getPosition().x){
                return true;
            }
        }

        if (player.getPosition().x < MyGdxGame.WIDTH/2) {
            if(getPosition().x < player.getPosition().x){
                return true;
            }
        }
        return false;
    }


    public boolean isBeingHitBy(AbstractPlayer player) {
        return player.getObjectBounds().overlaps(this.getObjectBounds());
    }

    public Rectangle getObjectBounds() {
        return new Rectangle(this.getPosition().x, this.getPosition().y, this.getTexture().getWidth(), this.getTexture().getHeight());
    }

    public void hitPlayer(){
        movementHorizontal=movementHorizontal*-1;
        movementVertical = movementVertical * getRandomDirectionMultiplier();
    }

    public Vector2 getPosition() {
        return this.position;
    }

    public void dispose() {
        ball.dispose();
    }
}
