package main;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D; 
import entity.Player;
import object.SuperObject;
import tile.TileManager;
import java.awt.Font;
import java.io.IOException;
import java.io.InputStream;
import java.awt.FontFormatException;
import java.io.IOException;


public class GamePanel extends JPanel implements Runnable {

    //screen settings
    final int originalTileSize = 16; 
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    //world map settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    private Font pixelfont;

    final int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    public Player player = new Player(this, keyH);
    public CollisionChecker checker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);

    public SuperObject obj[] = new SuperObject[10];


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        loadPixelFont();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void setupGame() {
        aSetter.setObject();

    }
    
    private void loadPixelFont() {
        try {
            InputStream is = getClass().getResourceAsStream("/res/fonts/pixelfont.ttf");
            pixelfont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(20f);
        }
        catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime(); 
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta --;
            } 
        }
        
    }

    public void update() {
        player.update();
    }

    public void displayKeyCount(Graphics2D g2) {
        // Set font and color for the key count display
        
        g2.setFont(pixelfont);
        g2.setColor(Color.WHITE);
        
        // Display the key count
        g2.drawString("Keys: " + player.getHasKey(), screenWidth - 100, 27); // Position the text at (20, 40)
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;    
        
        tileM.draw(g2);

        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }

        player.draw(g2);
        displayKeyCount(g2);
        g2.dispose();
    }

}