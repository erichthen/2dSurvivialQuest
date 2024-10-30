package object;

import java.io.IOException;
import main.GamePanel;
import javax.imageio.ImageIO;

public class ChestObj extends SuperObject {
    
    GamePanel gp;

    public ChestObj(GamePanel gp) {
        
        this.gp = gp;
        name = "chest";

        try {
            image = ImageIO.read(getClass().getResourceAsStream(("/res/objects/chest.png")));
            scaler.scaleImage(image, gp.tileSize, gp.tileSize);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
