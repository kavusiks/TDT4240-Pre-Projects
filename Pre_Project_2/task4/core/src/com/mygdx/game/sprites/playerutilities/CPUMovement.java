package com.mygdx.game.sprites.playerutilities;

import com.mygdx.game.sprites.PlayerTwo;

//the state interface - State pattern
public interface CPUMovement {

    //the method with a reference to the PlayerTwo object that can get its behaviour changed.
    void setCPU( PlayerTwo playerTwo);
    //the only method that will affect the behaviour of the CPU player
    void update(float dt);

}
