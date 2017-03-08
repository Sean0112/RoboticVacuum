import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.*;
import java.util.ArrayList;

public class DrawPanel extends JPanel {
    RoboticVacuum rv = new RoboticVacuum();
    ArrayList<Furniture> fs;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //draw the floor lines
        for (int i = 1; i <= 5; i++)
            g.drawLine(100 * i, 0, 100 * i, 500);
        for (int i = 1; i <= 5; i++)
            g.drawLine(0, 100 * i, 500, 100 * i);

        //draw the lil square things in the corners
        int x = 0;
        int y = 0;
        for (int j = 1; j <= 36; j++) {
            Polygon p = new Polygon();
            for (int i = 0; i < 4; i++)
                p.addPoint((int) (x + 10 * Math.cos(i * Math.PI / 2)), (int) (y + 10 * Math.sin(i * Math.PI / 2)));
            g.drawPolygon(p);
            g.fillPolygon(p);
            x += 100;
            if (j % 6 == 0) {
                x = 0;
                y += 100;
            }
        }

        fs = new ArrayList<>();
        Furniture f1 = new Furniture(110,110,80,80);
        f1.drawFurniture(g); fs.add(f1);
        Furniture f2 = new Furniture(10,210,80,80);
        f2.drawFurniture(g); fs.add(f2);
        Furniture f3 = new Furniture(410,310,80,80);
        f3.drawFurniture(g); fs.add(f3);
        Furniture f4 = new Furniture(110,210,80,80);
        f4.drawFurniture(g); fs.add(f4);

        rv.drawRoboticVacuum(g);
        rv.moveRoboticVacuum(fs);
    }

}
