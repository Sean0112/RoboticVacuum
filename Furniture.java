import javax.swing.*;
import java.awt.*;

public class Furniture {
    int x;
    int y;
    int width;
    int height;

    public Furniture(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void drawFurniture(Graphics g){
        g.draw3DRect(x,y,width,height,true);
        g.setColor(Color.blue);
        g.fill3DRect(x,y,width,height,true);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DrawPanel draw = new DrawPanel();
        frame.getContentPane().add(draw);
        frame.setSize(500, 520); // window size
        frame.setVisible(true);
        //move and draw the vacuum
        for(int i = 0; i < 23; i++) {
            draw.repaint();
            pause(200);
        }
    }

    public static void pause(int ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception e) {
            System.out.println("Uh oh");
        }
    }
}
