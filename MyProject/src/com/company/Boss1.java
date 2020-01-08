package com.company;
public class Boss1 extends Enemy{
    private boolean moveLeft;
    private boolean moveRight;
    private int x;
    private int y;
    public Boss1(int x1, int y1, int width1, int height1){
        super(x1, y1, width1, height1);
        moveLeft=false;
        moveRight=true;
        x=super.getX();
        y=super.getY();
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
    }public void shoot(){

    }
}