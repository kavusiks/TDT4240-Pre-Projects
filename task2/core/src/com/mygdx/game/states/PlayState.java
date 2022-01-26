package com.mygdx.game.states;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprites.Helicopter;

public class PlayState extends State{

    private Helicopter heli;
    private Texture bg;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        heli = new Helicopter(50,200);
        cam.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        bg = new Texture("bg.png");

    }


    @Override
    protected void handleInput() {

        if(Gdx.input.justTouched()) {
            Vector3 touchPoint = new Vector3();
            cam.unproject(touchPoint.set(Gdx.input.getX(),Gdx.input.getY(),0));
            heli.moveTo(((int)touchPoint.x-heli.getTexture().getWidth()/2) , ((int)touchPoint.y-heli.getTexture().getHeight()/2));
        }


    }

    @Override
    public void update(float dt) {
        handleInput();
        heli.update(dt);


        cam.update();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, cam.position.x - (cam.viewportWidth/2), 0);
        //sb.draw(heli.getTexture(), heli.getMovementHorizontal()>0 ? heli.getPosition().x + heli.getTexture().getWidth() : heli.getPosition().x ,heli.getPosition().y, heli.getMovementHorizontal()>0 ? -heli.getTexture().getWidth():heli.getTexture().getWidth(), heli.getTexture().getHeight());
        sb.draw(heli.getTexture(), heli.getPosition().x, heli.getPosition().y);
        sb.end();

    }

    @Override
    public void dispose() {
        bg.dispose();
        heli.dispose();

        System.out.println("Play State Disposed");
    }


}