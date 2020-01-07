package com.company;

public class Entity {
    private int _x;
    private int _y;
    private int _width;
    private int _height;
    private Entity tempEntity;
    public Entity(int x,int y,int width,int height){
        _x = x;
        _y = y;
        _width = width;
        _height = height;
    }
    public Entity(){
        _x = 0;
        _y = 0;
        _width = 0;
        _height = 0;
    }
    
    public boolean contains(int x, int y) {
        return (x >= _x) && (x <= _x + _width) && (y >= _y) && (y <= _y + _height);
    }

    public Entity intersection(Entity rect) {
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
        return tempEntity = new Entity(coordx, coordy, width, height);
    }
}
