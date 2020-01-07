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
    private int health=7;
    private Room room = new Room();
    private ArrayList<Tile> tileSet = room.getTileSet();
    private boolean redraw = false;
    public MyGame() throws IOException{

       int sLocal = -1;
        for (int i = 0; i < tileSet.size(); i++) {
            if (tileSet.get(i) instanceof Start){
                sLocal = i;
                System.out.println("I found the start at location "+sLocal);
                break;
            }
        }
        if(sLocal != -1 && tileSet.get(sLocal) instanceof Start){
            int[] sLocalList = ((Start) tileSet.get(sLocal)).getStartLocation();
            int startx = sLocalList[0];
            int starty = sLocalList[1];
            System.out.println("I'm setting the start X:"+sLocalList[0]+"   Y:"+sLocalList[1]);
            two = new Player(startx,starty,25,25,health);
        }
    }
    public void update() throws IOException {
        two.update();
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
                    tileSet.add(i+1,new Air(tileSet.get(i).getX(),tileSet.get(i).getY(),25,25));
                    tileSet.remove(i);

                }
            }else if(tileSet.get(i) instanceof Exit){
                if(tileSet.get(i).collideLeft(two) || tileSet.get(i).collideRight(two) || tileSet.get(i).collideUp(two) || tileSet.get(i).collideDown(two)){
                    tileSet.remove(i);
                    room.clear();
                    room.fillMap();
                    redraw = true;
                }
            }
        }
    }
    public void draw(Graphics pen) {
        if(redraw){
            room.draw(pen);
        }
        room.draw(pen);
        two.draw(pen);
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