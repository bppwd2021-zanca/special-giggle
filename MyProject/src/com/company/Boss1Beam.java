package com.company;

import java.awt.*;

public class Boss1Beam{
    private int x;
    private int y;
    private int width;
    private int height;
    public Boss1Beam(int x, int y, int width, int height){
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
    }public void draw(Graphics pen){
        pen.setColor(Color.yellow);
        pen.fillRect(x,y,width,height);
    }public void setHeight(int height){
        this.height=height;
    }public boolean collideUp(Player player){
        return (player.getY()+5<=(y+height) && (player.getY()>=y) && ((player.getX()+player.getWidth())>x) && (player.getX()<(x+width)));
    }public boolean collideLeft(Player player){
        return ((player.getX()+5)<=(x+width) && (player.getX()>=x) && (player.getY()<(y+height)) && ((player.getY()+player.getHeight())>y));
    }public boolean collideRight(Player player){
        return ((((player.getX()+player.getWidth())-5)>=x) && (player.getX()<=x) && (player.getY()<(y+height)) && ((player.getY()+player.getHeight())>y));
    }public boolean collideDown(Player player){
        return ((y+5)<=(player.getY()+player.getHeight()) && (player.getY()+player.getHeight()<=y+height) && ((player.getX()+player.getWidth())>x) && (player.getX()<(x+width)));
    }
}