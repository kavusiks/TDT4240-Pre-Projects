package com.mygdx.game.states;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprites.AbstractPlayer;
import com.mygdx.game.sprites.Ball;
import com.mygdx.game.sprites.PlayerOne;
import com.mygdx.game.sprites.PlayerTwo;

import javax.swing.AbstractButton;
import javax.swing.text.View;

public class PlayState extends State{
    private static final int PLAY_BUTTON_X = 325;
    private static final int GLASS_COUNT = 4;
    private static final int GROUND_Y_OFFSET = -85;

    private PlayerOne player1;
    private PlayerTwo player2;
    private Ball ball;
    private String winText;


    protected PlayState(GameStateManager gsm) {
        super(gsm);
        player1 = new PlayerOne(10, MyGdxGame.HEIGHT/2);
        player2 = new PlayerTwo(MyGdxGame.WIDTH-10, MyGdxGame.HEIGHT/2);
        ball = new Ball(MyGdxGame.WIDTH/2-Ball.BALL_RADIUS, MyGdxGame.HEIGHT/2-Ball.BALL_RADIUS);
        cam.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);

    }


    @Override
    protected void handleInput() {

        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            player2.moveDownWards();
        }

        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            // your actions
            player2.moveUpWards();
        }

        if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            player1.moveDownWards();
        }

        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            // your actions
            player1.moveUpWards();
        }


    }



    @Override

    public void update(float dt) {
        ball.update(dt);
        handleInput();

        if(ball.isBeingHitBy(player1) || ball.isBeingHitBy(player2)) {
            ball.hitPlayer();
        }

        if(ball.detectPlayerLoss(player2)) {
            player1.addWinPoints();
            ball.pauseBall();
            if(player1.getWinPoints()!=21)  gsm.push(new PauseState(gsm, this));
        }

        if(ball.detectPlayerLoss(player1)) {
            player2.addWinPoints();
            ball.pauseBall();
            if(player2.getWinPoints()!=21) gsm.push(new PauseState(gsm, this));
        }
        if (player2.getWinPoints() == 21 || player1.getWinPoints() ==21) {
            ball.pauseBall();
            winText = player1.getWinPoints() == 21 ? "Player 1" : "Player 2" + " won the game. Restart to play again.";
        }


        cam.update();

    }

    public void fireGameResumed() {
        ball.restartBall();
    }

    @Override
    public void render(SpriteBatch sb) {
        BitmapFont font = new BitmapFont();
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        //sb.draw(bg, cam.position.x - (cam.viewportWidth/2), 0);
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        sb.draw(player1.getTexture(), player1.getPosition().x, player1.getPosition().y);
        sb.draw(player2.getTexture(), player2.getPosition().x, player2.getPosition().y);
        sb.draw(ball.getTexture(), ball.getPosition().x, ball.getPosition().y);
        //sb.draw(heli.getTexture(), heli.getMovementHorizontal()>0 ? heli.getPosition().x + heli.getTexture().getRegionWidth() : heli.getPosition().x ,heli.getPosition().y, heli.getMovementHorizontal()>0 ? -heli.getTexture().getRegionWidth():heli.getTexture().getRegionWidth(), heli.getTexture().getRegionHeight());
        //sb.draw(heli2.getTexture(), heli2.getMovementHorizontal()>0 ? heli2.getPosition().x + heli2.getTexture().getRegionWidth() : heli2.getPosition().x ,heli2.getPosition().y, heli2.getMovementHorizontal()>0 ? -heli2.getTexture().getRegionWidth():heli2.getTexture().getRegionWidth(), heli2.getTexture().getRegionHeight());
        //sb.draw(heli3.getTexture(), heli3.getMovementHorizontal()>0 ? heli3.getPosition().x + heli3.getTexture().getRegionWidth() : heli3.getPosition().x ,heli3.getPosition().y, heli3.getMovementHorizontal()>0 ? -heli3.getTexture().getRegionWidth():heli3.getTexture().getRegionWidth(), heli3.getTexture().getRegionHeight());

        sb.setProjectionMatrix(cam.combined); //or your matrix to draw GAME WORLD, not UI



        font.setColor(Color.YELLOW);
        font.draw(sb, "Points: " + String.valueOf(player1.getWinPoints()), player1.getPosition().x, MyGdxGame.HEIGHT-50);
        font.draw(sb, "Points: " + String.valueOf(player2.getWinPoints()), player2.getPosition().x-60, MyGdxGame.HEIGHT-50);
        if(winText != null) font.draw(sb, winText,MyGdxGame.WIDTH/2 - 150, MyGdxGame.HEIGHT/2+ 20);


        sb.end();

    }


    @Override
    public void dispose() {
        player1.dispose();
        player2.dispose();
        ball.dispose();

        System.out.println("Play State Disposed");
    }


}