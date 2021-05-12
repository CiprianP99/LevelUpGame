package com.myGame.main;

import java.awt.*;

public abstract class GameObject {
    protected float x, y;
    protected ID id;
    protected float velX, velY;

    public GameObject(float x, float y, ID id){
        this.x = x;
        this.y = y;
        this.id = id;
    }
    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();
    public void setX(int x){
        this.x = x;
    }
    public float getX(){
        return this.x;
    }
    public void setY(int y){
        this.y = y;
    }
    public float getY(){
        return this.y;
    }
    public void setId(ID id){
        this.id = id;
    }
    public ID getId(){
        return this.id;
    }
    public void setVelX(int velX){
        this.velX = velX;
    }
    public float getVelX(){
        return this.velX;
    }
    public void setVelY(int velY){
        this.velY = velY;
    }
    public float getVelY(){
        return this.velY;
    }


}
