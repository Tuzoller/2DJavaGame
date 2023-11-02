package Core;

import Components.Collision;
import Components.KeyHandler;
import Entity.Player;
import Objects.ObjectManager;
import Tiles.tileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    //Screen Settings
    final int originalTileSize = 64; //16x16 tile
    final int scale = 1;

    public final int tileSize = originalTileSize * scale; //48x48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; //768 pixels
    public final int screenHeight = tileSize * maxScreenRow; //576 pixels
    final double FPS = 60;

    public Thread mainThread;
    private Window window;
    public KeyHandler keyH = new KeyHandler();
    public Player player = new Player(this,keyH);
    public tileManager tileM = new tileManager(this);
    public Collision collision = new Collision(this);
    public ObjectManager obj[] = new ObjectManager[10];


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void init(){
        mainThread = new Thread(this);
        mainThread.start();

    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTIme;

        long timer = 0;
        int drawCount = 0;

        while(mainThread != null){

            currentTIme = System.nanoTime();
            delta += (currentTIme - lastTime) / drawInterval;
            timer += (currentTIme - lastTime);
            lastTime = currentTIme;

            if(delta >= 1){
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000){
                window.setTitle("2DEngine: " + drawCount);
                drawCount = 0;
                timer = 0;
            }

        }
    }

    private void update(){
        player.update();
    }

    public void setWindow(Window window){
        this.window = window;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        tileM.draw(g2);
        player.draw(g2);

        g2.dispose();
    }
}
