package com.mygdx.game.sprites;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprites.playerutilities.AbstractPlayer;
import com.mygdx.game.sprites.playerutilities.CPUMovement;

public class PlayerTwo extends AbstractPlayer {
    //Used to implement only one instance - Singleton pattern
    private static PlayerTwo INSTANCE;

    private static final int STARTING_POINT_X = com.mygdx.game.MyGdxGame.WIDTH-10;
    private static final int STARTING_POINT_Y = MyGdxGame.HEIGHT/2;

    private CPUMovement behaviour;

    private PlayerTwo() {
        super(STARTING_POINT_X, STARTING_POINT_Y);
    }

    //used to get the one and only implemented Helicopter instance - Singleton pattern
    public static PlayerTwo getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new PlayerTwo();
        }
        return INSTANCE;
    }

    public void setBehaviour(CPUMovement behaviour) {
        behaviour.setCPU(this);
        this.behaviour = behaviour;
    }

    public CPUMovement getBehaviour() {
        return this.behaviour;
    }

    public void update(float dt){
        if(behaviour != null) behaviour.update(dt);

    }




}
