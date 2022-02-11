package com.mygdx.game.sprites.playerutilities;

import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprites.PlayerTwo;

public class CenterMovement implements CPUMovement {
    private static final int MOVEMENT_PER_TIME = 200;
    private PlayerTwo cpu;
    private int bottomReached = 1;

    @Override
    public void setCPU(PlayerTwo playerTwo) {
        cpu = playerTwo;
    }

    @Override
    public void update(float dt) {
        cpu.setPosition(cpu.getPosition().add(0, bottomReached*MOVEMENT_PER_TIME*dt));
        if(cpu.getPosition().y < 1*MyGdxGame.HEIGHT/5|| cpu.getPosition().y > 4*MyGdxGame.HEIGHT/5-cpu.getTexture().getHeight()) {
            bottomReached = bottomReached*-1;
        }
    }
}
