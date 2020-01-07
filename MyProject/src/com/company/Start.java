package com.company;
public class Start extends Tile{
    int x,y;
    public Start(int x, int y, int width, int height){
        super(x,y,width,height);
        this.x = x;
        this.y = y;
    }
    public int[] getStartLocation(){
        return new int[]{this.x, this.y};
    }
}