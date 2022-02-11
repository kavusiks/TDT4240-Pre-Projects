package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprites.playerutilities.AbstractPlayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Ball {
    private static final Color BALL_COLOR= Color.WHITE;
    public static final int BALL_RADIUS = 5;
    private static final int STARTING_POINT_X = MyGdxGame.WIDTH/2-Ball.BALL_RADIUS;
    private static final int STARTING_POINT_Y = MyGdxGame.HEIGHT/2-Ball.BALL_RADIUS;

    private ArrayList<Integer> randomDirectionVariables = new ArrayList<>(Arrays.asList(1,-1));
    private Vector2 position;
    private Texture ball;
    private int movementSpeed;
    private int movementVertical;
    private int movementHorizontal;

    //Used to implement only one instance - Singleton pattern
    private static Ball INSTANCE;


    private Ball() {
        movementSpeed = 150 * getRandomDirectionMultiplier();
        movementVertical = movementSpeed * getRandomDirectionMultiplier();
        movementHorizontal = movementSpeed * getRandomDirectionMultiplier();
        ball = getTexture();
        position = new Vector2(STARTING_POINT_X, STARTING_POINT_Y);
    }

    //used to get the one and only implemented Helicopter instance - Singelton pattern
    public static Ball getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new Ball();
        }
        return INSTANCE;
    }

    private int getRandomDirectionMultiplier() {
        return randomDirectionVariables.get(new Random().nextInt(randomDirectionVariables.size()));
    }

    private Pixmap getPixmapCircle(int radius){
        Pixmap pixmap=new Pixmap(radius*2+1, radius*2+1, Pixmap.Format.RGBA8888);
        pixmap.setColor(BALL_COLOR);
        pixmap.fillCircle(radius,radius,radius);
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
