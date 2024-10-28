package object;

import java.io.IOException;
import javax.imageio.ImageIO;

public class KeyObj extends SuperObject {
    
    public KeyObj() {
        
        name = "key";

        try {
            image = ImageIO.read(getClass().getResourceAsStream(("/res/objects/key.png")));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
