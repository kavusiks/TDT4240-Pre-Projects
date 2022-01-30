package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;

public class PauseState extends State {
    private Texture playBtn;
    private PlayState observingPlayState;


    public PauseState(GameStateManager gsm, PlayState ps) {
        super(gsm);
        playBtn = new Texture("playbtn.png");
        observingPlayState = ps;
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()) {
            gsm.pop();
            observingPlayState.fireGameResumed();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        //sb.draw(0,0, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        sb.draw(playBtn, (MyGdxGame.WIDTH/2)-(playBtn.getWidth()/2), (MyGdxGame.HEIGHT/4));
        sb.end();
    }

    @Override
    public void dispose() {
        playBtn.dispose();
        System.out.println("Pause State Disposed");
    }
}
