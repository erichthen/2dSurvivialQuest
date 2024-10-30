package object;

import java.io.IOException;
import main.GamePanel;
import javax.imageio.ImageIO;

public class DoorObj extends SuperObject{

    GamePanel gp;
    
    public DoorObj(GamePanel gp) {

        this.gp = gp;
        name = "door";

        try {
            image = ImageIO.read(getClass().getResourceAsStream(("/res/objects/door.png")));
            scaler.scaleImage(image, gp.tileSize, gp.tileSize);
        }
        catch (IOException e) { 
            e.printStackTrace();
        }
        collision = true;
    }
}
