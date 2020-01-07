package com.company;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
public class Player {
    private int x;
    private int y;
    private int width = 25;
    private int height = 25;
    Sword[] sword;
    private int q;
    private int health;
    private boolean hasKey;
    public Player(int x1, int y1, int width1, int height1,int health){
        x=x1;
        y=y1;
        height=height1;
        width=width1;
        sword=new Sword[1];
        health= health;
    }
    public void draw(Graphics pen){
        pen.setColor(Color.blue);
        pen.fillRect(x, y, width, height);
    }
    public void update() {
        if (x >= 600) {
            x = 600;

        } else if (x <= 0) {
            x = 1;
        }
        if (y >= 663) {
            y = 663;
        } else if (y <= 0) {
            y = 1;
        }
        //y += 10;
    }
    public void move(int mx, int my) {
        this.x = mx;
        this.y = my;
    }
    public void Jump(){
        y-=100;
        System.out.println("Jumping: " + y + " And X is: " + x);
    }
    public void released(){
        System.out.println("Falling: " + y + " And X is: " + x);
    }
    public int getHeight(){
        return height;
    }
    public int getWidth(){
        return width;
    }
    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
    public int movedown(int x1){
        return y+=x1;
    }
    public int moveup(int x1){
        return y-=x1;
    }
    public int moveright(int x1){
        return x+=x1;
    }
    public int moveleft(int x1){
        return x-=x1;
    }
    @Override
    public String toString(){
        return ("Rectangle X is : " + x+" The Y is : "+ y+" The Width is : "+ width+" The height is : "+height);
    }
    public boolean contains(int x1, int y1){
        if (x1>=this.x&& x1<=this.x+this.width && y1>=this.y && y1<=this.y+this.height){

            return true;
        }
        return(false);
    }
    public Enemy intersection(Enemy rect) {
        int top = rect.getX() + rect.getWidth();
        int bottom = rect.getY() + rect.getHeight();
        int endX = 0;
        int endY = 0;
        boolean crossed = false;
        for (int k = 0; k < rect.getWidth() + 1; k++) {
            for (int l = 0; l < rect.getHeight() + 1; l++) {
                if (this.contains(rect.getX() + k, rect.getY() + l)) {

                    crossed = true;
                    if (rect.getX() + k > endX) {
                        endX = rect.getX() + k;
                    }
                    if (rect.getY() + l > endY) {
                        endY = rect.getY() + l;
                    }
                    if (rect.getX() + k < top) {
                        top = rect.getX() + k;
                    }
                    if (rect.getY() + l < bottom) {
                        bottom = rect.getY() + l;
                    }
                }
            }
        }
        Enemy newR;
        newR = new Enemy(x, y, width, height, health);
        if (crossed){
            newR = new Enemy(top, bottom, endX - top, endY - bottom, health);
    }
        else{
            newR= new Enemy(top,bottom,0,0,health);
        }
        return newR;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setHasKey(boolean hasKey) {
        this.hasKey = hasKey;
    }

    public boolean getHasKey() {
        return hasKey;
    }

    public void setY(int y) {
        this.y = y;
    }

}