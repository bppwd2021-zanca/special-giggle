package com.company;
public class LevelMaker{
    private String[][] map;
    private String[] randomTileChooser;
    private String[] assetOrder;
    private int assetX;
    private int assetY;
    // 1 exit
    // 1 start
    // 1 key
    // lava optional
    // 2 door
    // 96 wall
    public LevelMaker(){
        map=new String[25][25];
        randomTileChooser=new String[]{"a","w","l"};
        assetOrder=new String[]{"s","e","k"};
    }public String[][] getMap(){
        return map;
    }public void randomizeMap(){
        for(int initWalls=0;initWalls<map.length;initWalls++){
            map[0][initWalls]="w";
            map[24][initWalls]="w";
        }for(int x=1;x<map.length-1;x++){
            map[x][0]="w";
            map[x][24]="w";
        }for(int randomTileX=1;randomTileX<map.length-1;randomTileX++)
            for(int randomTileY=1;randomTileY<map[randomTileX].length;randomTileY++)
                map[randomTileX][randomTileY]=randomTileChooser[(int)(Math.random()*3)];
        for(int assets=0;assets<3;assets++){
            assetX=(int)((Math.random()*24)+1);
            assetY=(int)((Math.random()*23)+1);
            map[assetX][assetY]=assetOrder[assets];
        }
    }
}