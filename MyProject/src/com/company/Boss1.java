package com.company;

import java.awt.*;

public class Boss1 extends Enemy{
    private boolean moveLeft;
    private boolean moveRight;
    private int x;
    private int y;
    private int width;
    private int height;
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
        else if(moveRight){
            x++;
            System.out.println(x);
        }if(x<=25){
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
    }@Override
    public void shoot(Graphics pen){
        pen.setColor(Color.yellow);
        pen.fillRect(x+25,y+height,50,800);
    }
}