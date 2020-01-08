package com.company;

import java.awt.*;

public class Enemy{
    private int x;
    private int y;
    private int width;
    private int height;
    public Enemy(int x1, int y1, int width1, int height1){
        x=x1;
        y=y1;
        width=width1;
        height=height1;
    }public void draw(Graphics pen, Color color){
        pen.setColor(color);
        pen.fillRect(x,y,width,height);
    }public int getX(){
        return x;
    }public int getY(){
        return y;
    }public int getWidth(){
        return width;
    }public int getHeight(){
        return height;
    }
    public void move(){}
    public void shoot(Graphics pen){}
}
//    public Enemy intersection(Bullet rect){
//
//
//        int top=rect.getX()+rect.getWidth();
//        int bottom=rect.getY()+rect.getHeight();
//        int endX=0;
//        int endY=0;
//        boolean crossed = false;
//        for(int k=0;k<rect.getWidth()+1;k++){
//            for(int l=0;l<rect.getHeight()+1;l++){
//                if(this.contains(rect.getX()+k,rect.getY()+l)){
//
//                    crossed=true;
//                    if(rect.getX()+k>endX){
//                        endX=rect.getX()+k;
//
//                    }
//                    if(rect.getY()+l>endY){
//                        endY=rect.getY()+l;
//
//                    }
//                    if(rect.getX()+k<top){
//                        top=rect.getX()+k;
//
//                    }
//                    if(rect.getY()+l<bottom){
//                        bottom=rect.getY()+l;
//
//                    }
//                }
//            }
//        }
//        Enemy newR;
//
//        newR=new Enemy(x,y,width,height);
//        if (crossed)
//            newR= new Enemy(top,bottom,endX-top,endY-bottom);
//        else{
//            newR= new Enemy(top,bottom,0,0);
//        }
//        return newR;
//    }
//










