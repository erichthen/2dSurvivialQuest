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
import entity.Entity;


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
    //public final int worldWidth = tileSize * maxWorldCol;
    //public final int worldHeight = tileSize * maxWorldRow;

    private Font pixelfont;

    final int FPS = 60;
    
    Sound music = new Sound();
    Sound soundEffect = new Sound();

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this);
    Thread gameThread;

    public Player player = new Player(this, keyH);
    public CollisionChecker checker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public SuperObject obj[] = new SuperObject[20];
    public Entity npc[] = new Entity[10];

    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;

    Graphics2D g2;

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
        aSetter.setNPC();
        playMusic(0);
        stopMusic();
        gameState = playState;
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

    public void displayPauseScreen(Graphics2D g2) {
        String text = "GAME PAUSED";
        
        Font largeFont = pixelfont.deriveFont(40f); 
        g2.setFont(largeFont);
        g2.setColor(Color.WHITE);
    
        int x = getXForCenteredText(g2, text);
        int y = this.screenHeight / 2;
        g2.drawString(text, x, y);
    
        //reset font
        g2.setFont(pixelfont);
    }
    
    public int getXForCenteredText(Graphics2D g2, String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = this.screenWidth / 2 - length / 2;
        return x;
    }
    
    public void displayUI(Graphics2D g2) {
    
        g2.setFont(pixelfont);
        g2.setColor(Color.WHITE);
    
        if (player.speedBoostActive) {
            int remainingTime = player.getRemainingBoostTime();
            String boostTimeText = "Speed boost: " + remainingTime + "s";
            
            //blink the message when speed boost is almost out
            if (player.blinkMessage) {
                long currentTime = System.currentTimeMillis();
                if ((currentTime / 500) % 2 == 0) {
                    g2.drawString(boostTimeText, screenWidth - 210, 69);
                }
            } else {
                g2.drawString(boostTimeText, screenWidth - 210, 69);
            }
        }
    
        if (gameState == pauseState) {
            music.stop();
            displayPauseScreen(g2);
        }
        else {
            music.play();
        }
    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        soundEffect.stop();
    }

    public void playSoundEffect(int i) {
        soundEffect.setFile(i);
        soundEffect.play();
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
        if (gameState == playState) {
            player.update();
        }
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

        for (int i = 0; i < npc.length; i++) {
            if (npc[i] != null) {
                npc[i].draw(g2);
            }
        }

        player.draw(g2);
        displayUI(g2);
        g2.dispose();
    }
}