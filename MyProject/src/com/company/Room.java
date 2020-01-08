package com.company;

import java.awt.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Room {
    private String[][] roomData;
    private int count = 0;
    private int roomNum = 1;
    private File csvFile = new File("../room" + roomNum + ".csv");
    private BufferedReader csvReader;
    private ArrayList<Tile> tileSet = new ArrayList<>();

    {
        try {
            csvReader = new BufferedReader(new FileReader(csvFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String line;

    public Room() throws IOException {
        roomData=new String[25][25];
        fillMap();
    }

    public void fillMap() throws IOException {
        count = 0;
        roomData=new String[25][25];
        tileSet = new ArrayList<>();
        csvReader = new BufferedReader(new FileReader(csvFile));
        while ((line = csvReader.readLine()) != null && roomData != null) {
            if (count < line.length())
                for (int i = 0; i < roomData[count].length; i++)
                    roomData[count] = line.split(",");
            count++;
        }
        if(roomData != null && tileSet != null) {
            for (int x = 0; x < roomData.length; x++) {
                for (int y = 0; y < roomData[x].length; y++) {
                    if (roomData[x][y].equals("w"))
                        tileSet.add(new Wall(y * 25, x * 25, 25, 25));
                    else if (roomData[x][y].equals("s"))
                        tileSet.add(new Start(y * 25, x * 25, 25, 25));
                    else if (roomData[x][y].equals("e"))
                        tileSet.add(new Exit(y * 25, x * 25, 25, 25));
                    else if (roomData[x][y].equals("k"))
                        tileSet.add(new Key(y * 25, x * 25, 25, 25));
                    else if (roomData[x][y].equals("d"))
                        tileSet.add(new Door(y * 25, x * 25, 25, 25));
                    else if (roomData[x][y].equals("a"))
                        tileSet.add(new Air(y * 25, x * 25, 25, 25));
                    else if (roomData[x][y].equals("l"))
                        tileSet.add(new Lava(y * 25, x * 25, 25, 25));
                }
            }
        }
    }

    public void draw(Graphics pen) {
        for (int i = 0; i < tileSet.size(); i++) {
            if (tileSet.get(i) instanceof Wall) {
                tileSet.get(i).setColor(pen, Color.gray);
            }
            if (tileSet.get(i) instanceof Door) {
                float[] hsbColors = new float[3];
                Color.RGBtoHSB(98, 52, 0, hsbColors);
                tileSet.get(i).setColor(pen, Color.getHSBColor(hsbColors[0], hsbColors[1], hsbColors[2]));
            }
            if (tileSet.get(i) instanceof Key)
                tileSet.get(i).setColor(pen, Color.yellow);
            if (tileSet.get(i) instanceof Start)
                tileSet.get(i).setColor(pen, Color.green);
            if (tileSet.get(i) instanceof Exit)
                tileSet.get(i).setColor(pen, Color.red);
            if(tileSet.get(i) instanceof Air)
                tileSet.get(i).setColor(pen, Color.white);
            if(tileSet.get(i) instanceof Lava){
                float[] hsbColors = new float[3];
                Color.RGBtoHSB(255, 140, 0, hsbColors);
                tileSet.get(i).setColor(pen, Color.getHSBColor(hsbColors[0], hsbColors[1], hsbColors[2]));
            }
            tileSet.get(i).draw(pen);
        }
    }


    public ArrayList<Tile> getTileSet() {
        return tileSet;
    }



    public void nextLevel(){
        roomNum++;
    }


    public void clear() {
        tileSet.clear();
        csvFile = new File("../room" + roomNum + ".csv");
        for (int i = 0; i < roomData.length; i++) {
            for (int j = 0; j < roomData[i].length; j++) {
                String[] temp = roomData[i];
                        temp[i] = null;
            }
        }
    }
}
