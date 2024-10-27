package main;

import javax.swing.JFrame;

public class Main {

    public static void main(String args[]) {
    
        JFrame window = new JFrame();
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("2D Adventure");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();
        window.setVisible(true);

        gamePanel.startGameThread();

    }
}