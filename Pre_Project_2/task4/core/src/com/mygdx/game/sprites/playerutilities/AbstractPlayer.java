package com.mygdx.game.sprites.playerutilities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;

public abstract class AbstractPlayer {
    private static final Color PLAYER_COLOR= Color.WHITE;
    private static final int PLAYER_HEIGHT = 100;
    private static final int PLAYER_WIDTH = 5;
    private Vector2 position;
    private Texture player;
    private int winPoints;

    protected AbstractPlayer(int x, int y) {
        player = getTexture();
        position = new Vector2(x,y-PLAYER_HEIGHT/2);
        winPoints = 0;
    }

    private Pixmap getPixmapRectangle(int width, int height){
        Pixmap pixmap=new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(PLAYER_COLOR);
        pixmap.fillRectangle(0,0, pixmap.getWidth(), pixmap.getHeight());
        return pixmap;
    }

    public int getWinPoints(){
        return this.winPoints;
    }

    public void addWinPoints() {
        this.winPoints +=1;
    }

    public Texture getTexture() {
        return new Texture(getPixmapRectangle(PLAYER_WIDTH, PLAYER_HEIGHT));
    }

    public Vector2 getPosition() {
        return this.position;
    }


    public void setPosition(Vector2 position) {
        this.position=position;
    }

    public void moveUpWards() {
        if(position.y< MyGdxGame.HEIGHT-getTexture().getHeight()) {
            position.y += 5;
        }
    }

    public Rectangle getObjectBounds() {
        return new Rectangle(this.getPosition().x, this.getPosition().y, this.getTexture().getWidth(), this.getTexture().getHeight());
    }

    public void moveDownWards() {
        if(position.y>0) {
            position.y -= 5;
        }
    }

    public void dispose() {

        player.dispose();
    }

}
