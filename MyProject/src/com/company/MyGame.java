package com.company;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.io.IOException;
public class    MyGame extends Game  {
    public static final String TITLE = "BPPWD Game";
    public static final int SCREEN_WIDTH = 641;
    public static final int SCREEN_HEIGHT = 664;
    public  int x;
    public  int y;
    private int playerVelocity = 5;
    private Player two ;
    private Boss1 boss1;
    private int health=7;
    private int intervalBoss1=0;
    private Room room = new Room();
    private ArrayList<Tile> tileSet = room.getTileSet();
    public MyGame() throws IOException{

        two = new Player(startPos()[0],startPos()[1],25,25);
        boss1 = new Boss1(400,25,100,100);
    }
    public int[] startPos(){
//        int sLocal = -1;
//        int startx = 0;
//        int starty = 0;
//        for (int i = 0; i < tileSet.size(); i++) {
//            if (tileSet.get(i) instanceof Start){
//                sLocal = i;
//                System.out.println("I found the start at location "+sLocal);
//                break;
//            }
//        }
//        if(sLocal != -1 && tileSet.get(sLocal) instanceof Start){
//            int[] sLocalList = ((Start) tileSet.get(sLocal)).getStartLocation();
//            startx = sLocalList[0];
//            starty = sLocalList[1];
//            System.out.println("I'm setting the start X:"+sLocalList[0]+"   Y:"+sLocalList[1]);
//            return new int[]{startx,starty};
//        }
//        return new int[]{startx,starty};
        int startX;
        int startY;
        // error, nothing inside arraylist
        for(int i=0;i<tileSet.size();i++){
            if(tileSet.get(i) instanceof Start){
                startX=((Start) tileSet.get(i)).getStartLocation()[0];
                startY=((Start) tileSet.get(i)).getStartLocation()[1];
                return new int[]{startX,startY};
            }
        }return new int[]{100,100};
    }
    public void update() throws IOException {
        two.update();
        tileSet=room.getTileSet();
        if(health<=0){
            System.exit(0);
        }
        for (int i = 0; i < tileSet.size(); i++) {
            if(tileSet.get(i) instanceof Wall || (tileSet.get(i) instanceof Door && !(two.getHasKey()))){
                if(tileSet.get(i).collideDown(two))
                    two.setY(two.getY()-playerVelocity);
                if(tileSet.get(i).collideUp(two))
                    two.setY(two.getY()+playerVelocity);
                if(tileSet.get(i).collideLeft(two))
                    two.setX(two.getX()+playerVelocity);
                if(tileSet.get(i).collideRight(two))
                    two.setX(two.getX()-playerVelocity);
            }else if(tileSet.get(i) instanceof Key){
                if(tileSet.get(i).collideLeft(two) || tileSet.get(i).collideRight(two) || tileSet.get(i).collideUp(two) || tileSet.get(i).collideDown(two)){
                    tileSet.add(i+1,new Air(tileSet.get(i).getX(),tileSet.get(i).getY(),25,25));
                    tileSet.remove(i);
                    two.setHasKey(true);
                }
            }else if(tileSet.get(i) instanceof Door){
                if(two.getHasKey() && (tileSet.get(i).collideLeft(two) || tileSet.get(i).collideRight(two) || tileSet.get(i).collideUp(two) || tileSet.get(i).collideDown(two))){
                    for(int door=0;door<tileSet.size();door++){
                        if(tileSet.get(door) instanceof Door){
                            tileSet.add(door+1,new Air(tileSet.get(door).getX(),tileSet.get(door).getY(),25,25));
                            tileSet.remove(door);
                        }
                    }
                }
            }else if(tileSet.get(i) instanceof Lava){
                if(tileSet.get(i).collideLeft(two) || tileSet.get(i).collideRight(two) || tileSet.get(i).collideUp(two) || tileSet.get(i).collideDown(two)){
                    room.clear();
                    room.fillMap();
                    two.setHasKey(false);
                    for(int start=0;start<tileSet.size();start++){
                        if(tileSet.get(start) instanceof Start){
                            two.setX(((Start) tileSet.get(start)).getStartLocation()[0]);
                            two.setY(((Start) tileSet.get(start)).getStartLocation()[1]);
                            if(room.getRoomNum()==5){
                                boss1.setX(400);
                                boss1.reset();
                            }
                        }
                    }
                }
            }
            else if(tileSet.get(i) instanceof Exit){
                if(tileSet.get(i).collideLeft(two) || tileSet.get(i).collideRight(two) || tileSet.get(i).collideUp(two) || tileSet.get(i).collideDown(two)){
                    tileSet.remove(i);
                    room.nextLevel();
                    room.clear();
                    room.fillMap();
                    two.setHasKey(false);
                    for(int start=0;start<tileSet.size();start++){
                        if(tileSet.get(start) instanceof Start){
                            two.setX(((Start) tileSet.get(start)).getStartLocation()[0]);
                            two.setY(((Start) tileSet.get(start)).getStartLocation()[1]);
                        }
                    }
                }
                else if(tileSet.get(i) instanceof Air){
                    two.doFall = true;
                }
            }
        }
    }
    public void draw(Graphics pen) {
        room.draw(pen);
        two.draw(pen);
        if(room.getRoomNum()==5){
            float[] hsbColors = new float[3];
            Color.RGBtoHSB(50, 0, 50, hsbColors);
            boss1.draw(pen,Color.getHSBColor(hsbColors[0], hsbColors[1], hsbColors[2]));
            boss1.move();
            if(boss1.shoot(pen,two)){
                // player gets shot
                two.setX(300);
                two.setY(500);
                boss1.setX(400);
                boss1.reset();
            }
        }
    }
    public void clear(){
    }
    public void keyTyped(KeyEvent ke) {}
    public void keyPressed(KeyEvent ke) {
//        if(ke.getKeyCode() == KeyEvent.VK_SPACE) {
//            two.Jump();
//        }
        if(ke.getKeyCode() == 10){
        }
        if(ke.getKeyCode() == KeyEvent.VK_UP){
            // jump method
            two.moveup(playerVelocity);

        }
        if(ke.getKeyCode() == KeyEvent.VK_DOWN) {
            two.movedown(playerVelocity);
        }
        if(ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            two.moveright(playerVelocity);
        }
        if(ke.getKeyCode() == KeyEvent.VK_LEFT) {
            two.moveleft(playerVelocity);
        }
        try {
            update();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void keyReleased(KeyEvent ke) {
//        if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
//        }
        if(ke.getKeyCode() == 10){
            two.setX(500);
            two.setY(550);
        }
    }
    public void mouseClicked(MouseEvent ke) { }
    public void mousePressed(MouseEvent me) {}
    public void mouseReleased(MouseEvent me) {}
    public void mouseEntered(MouseEvent me) {}
    public void mouseExited(MouseEvent me) {}
    //Launches the Game
    public static void main(String[] args) throws IOException { new MyGame().start(TITLE, SCREEN_WIDTH,SCREEN_HEIGHT);
    }
}
