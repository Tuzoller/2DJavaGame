package Tiles;

import Core.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class tileManager {

    GamePanel gp;
    public tile[] tiles;
    public int mapTileNum[][];

    public tileManager(GamePanel gp){
        this.gp = gp;
        tiles = new tile[10];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
        getTileImage();
        loadMap("/Maps/map001.txt");
    }

    public void getTileImage(){
        try{
            tiles[0] = new tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/grass.png"));

            tiles[1] = new tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/wall.png"));
            tiles[1].collision = true;

            tiles[2] = new tile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/water.png"));
            tiles[2].collision = true;

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(String path){
        try{
            InputStream is = getClass().getResourceAsStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxScreenCol && row < gp.maxScreenRow){
                String line = br.readLine();

                while (col < gp.maxScreenCol){
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxScreenCol){
                    col = 0;
                    row++;
                }
            }
            br.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
        int col = 0;
        int row = 0;

        while (col < gp.maxScreenCol && row < gp.maxScreenRow){

            int tileNum = mapTileNum[col][row];

            int worldX = col * gp.tileSize;
            int worldY = row * gp.tileSize;


            g2.drawImage(tiles[tileNum].image, worldX, worldY, gp.tileSize, gp.tileSize, null);
            col++;

            if(col == gp.maxScreenCol){
                col = 0;
                row++;
            }
        }
    }
}
