package com.mygdx.game.sprites.playerutilities;

import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprites.PlayerTwo;

public class BackAndForthMovement implements CPUMovement {

    private static final int MOVEMENT_PER_TIME = 180;
    private PlayerTwo cpu;
    private int bottomReached = 1;

    public void setCPU(PlayerTwo playerTwo) {
        cpu = playerTwo;
    }

    @Override
    public void update(float dt) {
        cpu.setPosition(cpu.getPosition().add(0, bottomReached*MOVEMENT_PER_TIME*dt));
        if(cpu.getPosition().y < 0 || cpu.getPosition().y > MyGdxGame.HEIGHT-cpu.getTexture().getHeight()) {
           bottomReached = bottomReached*-1;
        }

    }
}
