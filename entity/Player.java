package entity;

import main.GamePanel;
import main.KeyHandler;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage; 

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    int hasKey = 0;


    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle(6, 14, 28, 28);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "right";
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_right_2.png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void update() {

        //if not moving in a direction, don't increment spritecounter for walking effect
        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {

            if (keyH.upPressed == true) {
                direction = "up";
            }
            if (keyH.downPressed == true) {
                direction = "down";
            }
            if (keyH.leftPressed == true) {
                direction = "left";       
            }
            if (keyH.rightPressed == true) {
                direction = "right";
            }
 
            collisionOn = false;
            gp.checker.checkTile(this);

            int objIndex = gp.checker.checkObject(this, true);
            interactObject(objIndex);

            if (collisionOn == false) {
                switch (direction) {
                    case "up": worldY -= speed; break; 
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                }
            }  
            spriteCounter ++;
            //change 13 in order to change speed at which sprites switch
            if (spriteCounter > 13) {
                if (spriteNumber == 1) {
                    spriteNumber = 2;
                }
                else if (spriteNumber == 2) {
                    spriteNumber = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void interactObject(int i) {
        //if 999, we haven't touched an object
        if (i != 999) {
            String objectName = gp.obj[i].name;

            switch (objectName) {
                case "key":
                    hasKey++;
                    gp.obj[i] = null;
                    break;
                case "door":
                    if (hasKey > 0) {
                        gp.obj[i] = null;
                        hasKey--;
                    }
            }
        }
    }

    public int getHasKey() {
        return hasKey;
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction) {
            case "up": 
                if (spriteNumber == 1) {
                    image = up1;
                }
                if (spriteNumber == 2) {
                    image = up2;
                }
                break;

            case "down":
                if (spriteNumber ==  1) {
                    image = down1;
                }
                if (spriteNumber == 2) {
                    image = down2;
                }
                break;

            case "left": 
                if (spriteNumber ==  1) {
                    image = left1;
                }
                if (spriteNumber == 2) {
                    image = left2;
                }
                break;

            case "right":
                if (spriteNumber == 1) {
                    image = right1;
                }
                if (spriteNumber == 2) {
                    image = right2;
                }
                break;
        }   
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}   