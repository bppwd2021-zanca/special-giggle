package com.company;

import java.awt.*;

public class Tile {
    private int _x;
    private int _y;
    private int _width;
    private int _height;
    private Tile tempTile;

    public Tile() {
        _x = 0;
        _y = 0;
        _width = 0;
        _height = 0;
    }

    public Tile(int x, int y, int width, int height) {
        _x = x;
        _y = y;
        _width = width;
        _height = height;
    }

    public boolean contains(int x, int y) {
        return (x >= _x) && (x <= _x + _width) && (y >= _y) && (y <= _y + _height);
    }

    public Tile intersection(Tile rect) {
        boolean firstEncounter = true;
        boolean widthCheck = true;
        int coordx = 0;
        int coordy = 0;
        int counter = 0;
        int height = 0;
        int width = 0;
        for (int x = _x; x < _x + _width; x++) {
            widthCheck = true;
            for (int y = _y; y < _y + _width; y++) {
                if (rect.contains(x, y)) {
                    counter++;
                    if (widthCheck) {
                        widthCheck = false;
                        width++;
                    }
                    if (firstEncounter) {
                        coordx = x;
                        coordy = y;
                        firstEncounter = false;
                    }
                }
            }
        }
        if (width != 0) height = counter / width;
        return tempTile = new Tile(coordx, coordy, width, height);
    }
    public void draw(Graphics pen){
        pen.fillRect(_x,_y,_width,_height);
    }public void setColor(Graphics pen, Color color){
        pen.setColor(color);
    }public boolean collideDown(Player player){
        return ((_y+5)<=(player.getY()+player.getHeight()) && (player.getY()+player.getHeight()<=_y+_height) && ((player.getX()+player.getWidth())>_x) && (player.getX()<(_x+_width)));
    }public boolean collideUp(Player player){
        return (player.getY()+5<=(_y+_height) && (player.getY()>=_y) && ((player.getX()+player.getWidth())>_x) && (player.getX()<(_x+_width)));
    }public boolean collideLeft(Player player){
        return ((player.getX()+5)<=(_x+_width) && (player.getX()>=_x) && (player.getY()<(_y+_height)) && ((player.getY()+player.getHeight())>_y));
    }public boolean collideRight(Player player){
        return ((((player.getX()+player.getWidth())-5)>=_x) && (player.getX()<=_x) && (player.getY()<(_y+_height)) && ((player.getY()+player.getHeight())>_y));
    }

    public int getWidth() {
        return _width;
    }

    public int getX() {
        return _x;
    }

    public int getY() {
        return _y;
    }

    public int getHeight() {
        return _height;
    }

}