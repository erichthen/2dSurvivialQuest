package object;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import main.GamePanel;
import java.awt.geom.AffineTransform;
import java.awt.Rectangle;
import main.Helpers;

public class SuperObject {
    
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public int rotation = 0;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    Helpers scaler = new Helpers();

    public void draw(Graphics2D g2, GamePanel gp) {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
            worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && 
            worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
            worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

                AffineTransform at = new AffineTransform();
                at.translate(screenX + gp.tileSize / 2, screenY + gp.tileSize / 2); // Move to center
                at.rotate(Math.toRadians(rotation)); 
                at.translate(-gp.tileSize / 2, -gp.tileSize / 2); 
    
                double scaleX = (double) gp.tileSize / image.getWidth();
                double scaleY = (double) gp.tileSize / image.getHeight();
                at.scale(scaleX, scaleY); 
    
                g2.drawImage(image, at, null);
            }
    }
}
