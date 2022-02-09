package com.mygdx.game.states;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprites.Helicopter;

public class PlayState extends State{
    private static final int GLASS_SPACING = 325;
    private static final int GLASS_COUNT = 4;
    private static final int GROUND_Y_OFFSET = -85;

    private Helicopter heli;
    private Helicopter heli2;
    private Helicopter heli3;
    private Texture bg;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        heli = new Helicopter(140, 120);
        heli2 = new Helicopter(2,10);
        heli3 = new Helicopter(130,65);
        System.out.println(heli2.getTexture().getRegionHeight());
        System.out.println(heli2.getTexture().getRegionWidth());
        cam.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        bg = new Texture("bg.png");

    }


    @Override
    protected void handleInput() {
       /*
        if(Gdx.input.justTouched()) {
            heli.jump();
        }

        */
    }

    @Override
    public void update(float dt) {
        handleInput();
        heli.update(dt);
        heli2.update(dt);
        heli3.update(dt);
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

        cam.update();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, cam.position.x - (cam.viewportWidth/2), 0);
        sb.draw(heli.getTexture(), heli.getMovementHorizontal()>0 ? heli.getPosition().x + heli.getTexture().getRegionWidth() : heli.getPosition().x ,heli.getPosition().y, heli.getMovementHorizontal()>0 ? -heli.getTexture().getRegionWidth():heli.getTexture().getRegionWidth(), heli.getTexture().getRegionHeight());
        sb.draw(heli2.getTexture(), heli2.getMovementHorizontal()>0 ? heli2.getPosition().x + heli2.getTexture().getRegionWidth() : heli2.getPosition().x ,heli2.getPosition().y, heli2.getMovementHorizontal()>0 ? -heli2.getTexture().getRegionWidth():heli2.getTexture().getRegionWidth(), heli2.getTexture().getRegionHeight());
        sb.draw(heli3.getTexture(), heli3.getMovementHorizontal()>0 ? heli3.getPosition().x + heli3.getTexture().getRegionWidth() : heli3.getPosition().x ,heli3.getPosition().y, heli3.getMovementHorizontal()>0 ? -heli3.getTexture().getRegionWidth():heli3.getTexture().getRegionWidth(), heli3.getTexture().getRegionHeight());


        sb.end();

    }

    @Override
    public void dispose() {
        bg.dispose();
        heli.dispose();

        System.out.println("Play State Disposed");
    }


}