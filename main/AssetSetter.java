package main;

import object.KeyObj;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.obj[0] = new KeyObj();
        gp.obj[0].worldX = 23 * gp.tileSize;        
        gp.obj[0].worldY = 7 * gp.tileSize;

        gp.obj[1] = new KeyObj();
        gp.obj[1].worldX = 23 * gp.tileSize;        
        gp.obj[1].worldY = 44 * gp.tileSize;
    }

}
