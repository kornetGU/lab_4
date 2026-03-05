import Vehicle.*;
import java.awt.*;
import java.util.List;
import javax.swing.*;

// This panel represents the animated part of the view with the car images.
public class DrawPanel extends JPanel{
    CarController cc;

    List<Vehicle> cars;
    List<Workshop> workshops;

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y, CarController cc) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);

        this.cc = cc;
        cars = cc.getCars();
        workshops = cc.getWorkshops();
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Vehicle car : cars) {
            g.drawImage(car.getImage(), (int) car.getX() , (int) car.getY(), null);
        }

        for (Workshop workshop : workshops) {
            g.drawImage(workshop.getImage(), (int) workshop.getX(), (int) workshop.getY(), null);
        }

    }
}
