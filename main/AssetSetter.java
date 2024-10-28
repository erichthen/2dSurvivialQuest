package main;

import object.KeyObj;
import object.DoorObj;
import object.ChestObj;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.obj[0] = new KeyObj();
        gp.obj[0].worldX = 23 * gp.tileSize;        
        gp.obj[0].worldY = 3 * gp.tileSize;

        gp.obj[1] = new KeyObj();
        gp.obj[1].worldX = 23 * gp.tileSize;        
        gp.obj[1].worldY = 44 * gp.tileSize;

        gp.obj[2] = new KeyObj();
        gp.obj[2].worldX = 42 * gp.tileSize;        
        gp.obj[2].worldY = 23 * gp.tileSize;

        gp.obj[3] = new DoorObj();
        gp.obj[3].worldX = 19 * gp.tileSize;
        gp.obj[3].worldY = 21 * gp.tileSize;

        gp.obj[4] = new DoorObj();
        gp.obj[4].worldX = 6 * gp.tileSize;
        gp.obj[4].worldY = 23 * gp.tileSize;

        gp.obj[5] = new DoorObj();
        gp.obj[5].worldX = 23 * gp.tileSize;
        gp.obj[5].worldY = 9 * gp.tileSize;
        gp.obj[5].rotation = 90;

        gp.obj[6] = new ChestObj();
        gp.obj[6].worldX = 3 * gp.tileSize;
        gp.obj[6].worldY = 23 * gp.tileSize;

        gp.obj[7] = new KeyObj();
        gp.obj[7].worldX = 45 * gp.tileSize;
        gp.obj[7].worldY = 14 * gp.tileSize;

    }

}