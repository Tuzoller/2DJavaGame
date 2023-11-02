package Core;

import javax.swing.*;

public class Window extends JFrame {

    private String title;
    private int width, height;

    public Window(String title, int width, int height){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setTitle(title);
        GamePanel panel = new GamePanel();
        panel.setWindow(this);
        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        panel.init();
    }
}