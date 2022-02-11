package com.mygdx.game.sprites;

import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprites.playerutilities.AbstractPlayer;

public class PlayerOne extends AbstractPlayer {
    //Used to implement only one instance - Singleton pattern
    private static PlayerOne INSTANCE;

    private static final int STARTING_POINT_X = 10;
    private static final int STARTING_POINT_Y = MyGdxGame.HEIGHT/2;

    private PlayerOne() {
        super(STARTING_POINT_X, STARTING_POINT_Y);
    }

    //used to get the one and only implemented Helicopter instance - Singleton pattern
    public static PlayerOne getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new PlayerOne();
        }
        return INSTANCE;
    }








}
