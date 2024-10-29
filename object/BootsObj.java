package object;

import java.io.IOException;
import javax.imageio.ImageIO;

public class BootsObj extends SuperObject {
    
    public BootsObj() {
        
        name = "boots";

        try {
            image = ImageIO.read(getClass().getResourceAsStream(("/res/objects/boots.png")));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
