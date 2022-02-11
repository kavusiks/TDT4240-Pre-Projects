package com.mygdx.game.sprites.playerutilities;

import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprites.PlayerTwo;

import java.util.ArrayList;


public class RandomMovement implements CPUMovement{
    private static final int MOVEMENT_PER_TIME_SPEED = 180;
    private int upper_bound=MyGdxGame.HEIGHT;
    private int lower_bound=0;
    private PlayerTwo cpu;
    private int bottomReached = 1;
    private int lastRandom=0;


    private int getRandomNumberUsingNextInt(int min, int max, int interval) {
        ArrayList<Integer> allPossibleSpeeds = new ArrayList<>();
        int differance = (max-min)/interval;
        int base= min;
        allPossibleSpeeds.add(base);
        for (int i=0;i<interval-2;i++) {
            allPossibleSpeeds.add(base + differance);
            base+= differance;
        }
        System.out.println(allPossibleSpeeds);
        int j = (int) (Math.random() *(interval-2));
        if(j == lastRandom) {
            j=lastRandom+1;
            if(j> interval-2) {
                j=0;
            }
            lastRandom = j;
        }
        return allPossibleSpeeds.get(j);
    }

    public void setCPU(PlayerTwo playerTwo) {
        cpu = playerTwo;
        upper_bound = upper_bound -cpu.getTexture().getHeight();
    }


    private void changeLowerBounds() {
        lower_bound= getRandomNumberUsingNextInt(0, MyGdxGame.HEIGHT/2-cpu.getTexture().getHeight()/2, 10);
    }

    private void changeUpperBounds(){
        upper_bound = getRandomNumberUsingNextInt(MyGdxGame.HEIGHT/2 + cpu.getTexture().getHeight()/2,MyGdxGame.HEIGHT- cpu.getTexture().getHeight(), 10);
    }

    @Override
    public void update(float dt) {
        cpu.setPosition(cpu.getPosition().add(0, bottomReached*MOVEMENT_PER_TIME_SPEED*dt));
        if(cpu.getPosition().y < lower_bound) {
            bottomReached = 1;
            changeLowerBounds();
        }

        if(cpu.getPosition().y > upper_bound) {
            bottomReached = -1;
            changeUpperBounds();
        }
    }
}
