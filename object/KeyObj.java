package object;

import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;

public class KeyObj extends SuperObject {

    GamePanel gp;
    
    public KeyObj(GamePanel gp) {

        this.gp = gp;
        name = "key";
       
        try {
            image = ImageIO.read(getClass().getResourceAsStream(("/res/objects/key.png")));
            scaler.scaleImage(image, gp.tileSize, gp.tileSize);

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
