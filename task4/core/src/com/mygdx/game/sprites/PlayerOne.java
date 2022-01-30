package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;

public class PlayerOne extends AbstractPlayer {

    //private Rectangle screenBounds;

    public PlayerOne(int x, int y) {
        super(x,y);
        //screenBounds = new Rectangle(-5, -5, Gdx.graphics.getWidth()+5, Gdx.graphics.getHeight()+5);

    }



    public void update(float dt) {

    }


    public Rectangle getObjectBounds() {
        return new Rectangle(this.getPosition().x, this.getPosition().y, this.getTexture().getWidth(), this.getTexture().getHeight());
    }




}
