package com.company;
import java.awt.*;
public class Sword {
    private int _width;
    private int _height;

    //    Top Left
    private int _x;
    private int _y;
    private int velX = 2;
    private int velY = 2;
    private String _direction;


//    Bottom Right
//    _xbr = _x+_width;
//    _ybr = _y+_height;

//    Bottom Left
//    _xbl = _x;
//    _ybl = _y+_height;

//    Top Right
//    _xtr = _x+_width;
//    _ytr = _y;

    public Sword(int x, int y, int width, int height, String direction) {
        _width = width;
        _height = height;
        _x = x;
        _y = y;
        _direction = direction;
    }

    public boolean contains(int x, int y) {
        return (x >= _x) && (x <= _x + _width) && (y >= _y) && (y <= _y + _height);
    }

//    public Player intersection(Enemy rect) {
//        Player newRect;
//        boolean firstEncounter = true;
//        boolean widthCheck = true;
//        int coordx = 0;
//        int coordy = 0;
//        int counter = 0;
//        int height = 0;
//        int width = 0;
//        for (int x = _x; x < _x + _width; x++) {
//            widthCheck = true;
//            for (int y = _y; y < _y + _width; y++) {
//                if (rect.contains(x, y)) {
//                    counter++;
//                    if (widthCheck) {
//                        widthCheck = false;
//                        width++;
//                    }
//                    if (firstEncounter) {
//                        coordx = x;
//                        coordy = y;
//                        firstEncounter = false;
//                    }
//                }
//            }
//        }
//        if (width != 0) height = counter / width;
//        return newRect = new Player(coordx, coordy, width, height);
//    }

    public void move() {
        if(_direction.equals("left")){
            _x+=velX;
        }
        if(_direction.equals("right")){
            _x-=velX;
        }
        if(_direction.equals("up")){
            _y-=velY;
        }
        if(_direction.equals("down")){
            _y+=velY;
        }
//         _x = posX;
//         _y = posY;
    }
    public void draw(Graphics pen){
        pen.setColor(Color.blue);
        pen.fillRect(_x,_y,_width,_height);

    }

    @Override
    public String toString() {
        return "Projectile[x=" + _x + ",y=" + _y + ",width=" + _width + ",height=" + _height + "]";
    }

    public void set_direction(String _direction) {
        this._direction = _direction;
    }

    public String get_direction() {
        return _direction;
    }

    public int get_height() {
        return _height;
    }

    public int get_width() {
        return _width;
    }

    public int get_x() {
        return _x;
    }

    public int get_y() {
        return _y;
    }
}
