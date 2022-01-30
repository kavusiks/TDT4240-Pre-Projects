package com.mygdx.game.states;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprites.PlayerOne;
import com.mygdx.game.sprites.PlayerTwo;

public class PlayState extends State{
    private static final int GLASS_SPACING = 325;
    private static final int GLASS_COUNT = 4;
    private static final int GROUND_Y_OFFSET = -85;

    private PlayerOne player1;
    private PlayerTwo player2;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        player1 = new PlayerOne(10, MyGdxGame.HEIGHT/2);
        player2 = new PlayerTwo(MyGdxGame.WIDTH-10, MyGdxGame.HEIGHT/2);
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
        handleInput();
        //player.update(dt);
       /*
        heli2.update(dt);
        if(heli.isColliding(heli2)) {
            System.out.println("collided: Heli1: " + heli.getPosition() + " Heli2: " + heli2.getPosition());
            heli.hasCollided();
            heli2.hasCollided();
        }
        if(heli.isColliding(heli3)) {
            System.out.println("collided: Heli1: " + heli.getPosition() + " Heli3: " + heli3.getPosition());
            heli.hasCollided();
            heli3.hasCollided();
        }
        if(heli2.isColliding(heli3)) {
            System.out.println("collided: Heli1: " + heli2.getPosition() + " Heli3: " + heli3.getPosition());
            heli2.hasCollided();
            heli3.hasCollided();
        }

        */

        cam.update();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        //sb.draw(bg, cam.position.x - (cam.viewportWidth/2), 0);
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        sb.draw(player1.getTexture(), player1.getPosition().x, player1.getPosition().y);
        sb.draw(player2.getTexture(), player2.getPosition().x, player2.getPosition().y);
        //sb.draw(heli.getTexture(), heli.getMovementHorizontal()>0 ? heli.getPosition().x + heli.getTexture().getRegionWidth() : heli.getPosition().x ,heli.getPosition().y, heli.getMovementHorizontal()>0 ? -heli.getTexture().getRegionWidth():heli.getTexture().getRegionWidth(), heli.getTexture().getRegionHeight());
        //sb.draw(heli2.getTexture(), heli2.getMovementHorizontal()>0 ? heli2.getPosition().x + heli2.getTexture().getRegionWidth() : heli2.getPosition().x ,heli2.getPosition().y, heli2.getMovementHorizontal()>0 ? -heli2.getTexture().getRegionWidth():heli2.getTexture().getRegionWidth(), heli2.getTexture().getRegionHeight());
        //sb.draw(heli3.getTexture(), heli3.getMovementHorizontal()>0 ? heli3.getPosition().x + heli3.getTexture().getRegionWidth() : heli3.getPosition().x ,heli3.getPosition().y, heli3.getMovementHorizontal()>0 ? -heli3.getTexture().getRegionWidth():heli3.getTexture().getRegionWidth(), heli3.getTexture().getRegionHeight());


        sb.end();

    }

    @Override
    public void dispose() {
        player1.dispose();
        player2.dispose();

        System.out.println("Play State Disposed");
    }


}