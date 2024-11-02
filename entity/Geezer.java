package entity;

import java.util.Random;
import main.GamePanel;

public class Geezer extends Entity{
    
    public Geezer(GamePanel gp) {
        super(gp);
        direction = "down";
        speed = 1;
        getImage();
    }

    public void getImage() {
        up1 = setupImage("/NPC/oldman_up_1");
        up2 = setupImage("/NPC/oldman_up_2");
        down1 = setupImage("/NPC/oldman_down_1");
        down2 = setupImage("/NPC/oldman_down_2");
        left1 = setupImage("/NPC/oldman_left_1");
        left2 = setupImage("/NPC/oldman_left_2");
        right1 = setupImage("/NPC/oldman_right_1");
        right2 = setupImage("/NPC/oldman_right_2");
    }

    public void setAction() {

        actionLockCounter ++;
        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;
    
            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            }

            actionLockCounter = 0;
            
        }  
    }   
}
