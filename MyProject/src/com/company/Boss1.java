package com.company;

import java.awt.*;

public class Boss1 extends Enemy{
    private boolean moveLeft;
    private boolean moveRight;
    private int x;
    private int y;
    private int width;
    private int height;
    private int interval;
    private Boss1Beam beam;
    public Boss1(int x1, int y1, int width1, int height1){
        super(x1, y1, width1, height1);
        moveLeft=false;
        moveRight=true;
        x=super.getX();
        y=super.getY();
        width=super.getWidth();
        height=super.getHeight();
    }@Override
    public void move(){
        if(moveLeft)
            x--;
        else if(moveRight)
            x++;
        if(x<=25){
            moveRight=true;
            moveLeft=false;
        }if(x>=500){
            moveLeft=true;
            moveRight=false;
        }
    }@Override
    public void draw(Graphics pen,Color color){
        pen.setColor(color);
        pen.fillRect(x,y,width,height);
    }public boolean shoot(Graphics pen,Player player){
        if(interval%300<=30){
            beam=new Boss1Beam(x+40,y+height,20,450);
            if(beamCollision(player))
                return true;
            if((x+60)<=100 || ((x+40)>=200 && (x+60)<=275) || ((x+40)>=350 && (x+60)<=425) || (x+40)>=525)
                beam.setHeight(450);
            else if(((x+60)>=100 && (x+40)<=200) || ((x+60)>=425 && (x+40)<=525))
                beam.setHeight(350);
            else if(((x+60)>=275) && ((x+40)<=350))
                beam.setHeight(400);
            beam.draw(pen);
        }interval++;
        return false;
    }public boolean beamCollision(Player player){
        return (beam.collideLeft(player) || beam.collideRight(player) || beam.collideUp(player) || beam.collideDown(player));
    }public void reset(){
        interval=0;
        moveLeft=false;
        moveRight=true;
    }public void setX(int x){
        this.x=x;
    }
}