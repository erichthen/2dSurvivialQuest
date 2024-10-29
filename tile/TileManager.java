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
        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/res/maps/world01.txt");
    }

    public void getTileImage() {
        tileSetup(0, "grass", false);
        tileSetup(1, "wall", true);
        tileSetup(2, "water", true);
        tileSetup(3, "earth", false);
        tileSetup(4, "tree", true);
        tileSetup(5, "sand", false);
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
            double screenX = worldX - gp.player.worldX + gp.player.screenX;
            double screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
              worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && 
              worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
              worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                g2.drawImage(tile[tileNum].image, (int)screenX, (int)screenY, gp.tileSize, gp.tileSize, null);
            }

            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    


        
    }
}
