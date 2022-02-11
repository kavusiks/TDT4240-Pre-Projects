package com.mygdx.game.sprites.playerutilities;

import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprites.PlayerTwo;

import java.util.ArrayList;
import java.util.Random;

public class RandomSpeedOnlyMovement implements CPUMovement{
    private static final int MOVEMENT_PER_TIME_MAXIMUM_SPEED = 400;
    private static final int MOVEMENT_PER_TIME_MINIMUM_SPEED = 50;
    private int current_speed;
    private PlayerTwo cpu;
    private int bottomReached = 1;


    private int getRandomNumberUsingNextInt(int min, int max, int interval) {
        ArrayList<Integer> allPossibleSpeeds = new ArrayList<>();
        int differance = (max-min)/interval;
        int base= MOVEMENT_PER_TIME_MINIMUM_SPEED;
        for (int i=0;i<interval;i++) {
            allPossibleSpeeds.add(base + differance);
            base+= differance;
        }
        Random random = new Random();
        int j = random.nextInt(interval);
        return allPossibleSpeeds.get(j);
    }

    public void setCPU(PlayerTwo playerTwo) {
        cpu = playerTwo;
        changeSpeed();
    }

    private void changeSpeed() {
        current_speed = getRandomNumberUsingNextInt(MOVEMENT_PER_TIME_MINIMUM_SPEED, MOVEMENT_PER_TIME_MAXIMUM_SPEED, 4);
    }

    @Override
    public void update(float dt) {
        cpu.setPosition(cpu.getPosition().add(0, bottomReached*current_speed*dt));
        if(cpu.getPosition().y < 0 || cpu.getPosition().y > MyGdxGame.HEIGHT-cpu.getTexture().getHeight()) {
            bottomReached = bottomReached*-1;
            changeSpeed();
        }
    }
}
