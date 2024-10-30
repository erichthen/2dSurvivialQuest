package tile;

import main.GamePanel;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Graphics2D; 
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.awt.image.BufferedImage;
import main.Helpers;
import java.io.IOException;

public class TileManager {
    
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[50];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/res/maps/world02.txt");
    }

    public void getTileImage() {

        //placeholder to avoid null ptr exception
        tileSetup(0, "grass00", false);
        tileSetup(1, "grass00", false);
        tileSetup(2, "grass00", false);
        tileSetup(3, "grass00", false);
        tileSetup(4, "grass00", false);
        tileSetup(5, "grass00", false);
        tileSetup(6, "grass00", false);
        tileSetup(7, "grass00", false);
        tileSetup(8, "grass00", false);
        tileSetup(9, "grass00", false);
        tileSetup(10, "grass00", false);

        tileSetup(11, "grass01", false);
        tileSetup(12, "water00", true);
        tileSetup(13, "water01", true);
        tileSetup(14, "water02", true);
        tileSetup(15, "water03", true);
        tileSetup(16, "water04", true);
        tileSetup(17, "water05", true);
        tileSetup(18, "water06", true);
        tileSetup(19, "water07", true);
        tileSetup(20, "water08", true);
        tileSetup(21, "water09", true);
        tileSetup(22, "water10", true);
        tileSetup(23, "water11", true);
        tileSetup(24, "water12", true);
        tileSetup(25, "water13", true);
        tileSetup(26, "road00", false);
        tileSetup(27, "road01", false);
        tileSetup(28, "road02", false);
        tileSetup(29, "road03", false);
        tileSetup(30, "road04", false);
        tileSetup(31, "road05", false);
        tileSetup(32, "road06", false);
        tileSetup(33, "road07", false);
        tileSetup(34, "road08", false);
        tileSetup(35, "road09", false);
        tileSetup(36, "road10", false);
        tileSetup(37, "road11", false);
        tileSetup(38, "road12", false);
        tileSetup(39, "earth", false);
        tileSetup(40, "wall", true);
        tileSetup(41, "tree", true);

    }

    public void tileSetup(int index, String name, boolean collision) {
        Helpers scaler = new Helpers();

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/" + name + ".png"));
            tile[index].image = scaler.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String mapFile) {
        try {
            InputStream is = getClass().getResourceAsStream(mapFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();

                while (col < gp.maxWorldCol) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        }
        catch (Exception e) {

        }
    }

    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
              worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && 
              worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
              worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                g2.drawImage(tile[tileNum].image, screenX, screenY,null);
            }

            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    


        
    }
}
