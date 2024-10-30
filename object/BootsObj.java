package object;

import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;

public class BootsObj extends SuperObject {
    
    GamePanel gp;

    public BootsObj(GamePanel gp) {
        
        this.gp = gp;
        name = "boots";

        try {
            image = ImageIO.read(getClass().getResourceAsStream(("/res/objects/boots.png")));
            scaler.scaleImage(image, gp.tileSize, gp.tileSize);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
