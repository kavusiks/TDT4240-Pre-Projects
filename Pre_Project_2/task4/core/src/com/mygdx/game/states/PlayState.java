package com.mygdx.game.states;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprites.Ball;
import com.mygdx.game.sprites.PlayerOne;
import com.mygdx.game.sprites.PlayerTwo;
import com.mygdx.game.sprites.playerutilities.BackAndForthMovement;
import com.mygdx.game.sprites.playerutilities.CPUMovement;
import com.mygdx.game.sprites.playerutilities.CenterMovement;
import com.mygdx.game.sprites.playerutilities.RandomMovement;
import com.mygdx.game.sprites.playerutilities.RandomSpeedOnlyMovement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class PlayState extends State{

    private PlayerOne player1;
    private PlayerTwo player2;
    private Ball ball;
    private String winText;
    private ArrayList<CPUMovement> cpuMovements;


    protected PlayState(GameStateManager gsm) {
        super(gsm);
        cpuMovements = new ArrayList<>(Arrays.asList(new BackAndForthMovement(), new CenterMovement(), new RandomMovement(), new RandomSpeedOnlyMovement()));
        player1 = PlayerOne.getInstance();
        player2 = PlayerTwo.getInstance();
        //setting initial behaviour for the CPU
        player2.setBehaviour(cpuMovements.iterator().next());
        ball = Ball.getInstance();
        cam.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);


    }


    @Override
    protected void handleInput() {

        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            player1.moveDownWards();
        }

        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            player1.moveUpWards();
        }



    }



    @Override

    public void update(float dt) {
        player2.update(dt);
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
        int nextIndex = cpuMovements.indexOf(player2.getBehaviour())< cpuMovements.size()-1 ? cpuMovements.indexOf(player2.getBehaviour()) +1 : 0;
        //changing behaviour of the CPU when game is resumed
        player2.setBehaviour(cpuMovements.get(nextIndex));

    }

    @Override
    public void render(SpriteBatch sb) {
        BitmapFont font = new BitmapFont();
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        sb.draw(player1.getTexture(), player1.getPosition().x, player1.getPosition().y);
        sb.draw(player2.getTexture(), player2.getPosition().x, player2.getPosition().y);
        sb.draw(ball.getTexture(), ball.getPosition().x, ball.getPosition().y);

        sb.setProjectionMatrix(cam.combined);



        font.setColor(Color.YELLOW);
        font.draw(sb, "Points: " + player1.getWinPoints(), player1.getPosition().x, MyGdxGame.HEIGHT-50);
        font.draw(sb, "Points: " + player2.getWinPoints(), player2.getPosition().x-60, MyGdxGame.HEIGHT-50);
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