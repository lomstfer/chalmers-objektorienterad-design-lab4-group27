import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel {
    ImageDrawer volvoImageDrawer;
    ImageDrawer saab95ImageDrawer;
    ImageDrawer scaniaImageDrawer;
    ImageDrawer unsupportedImageDrawer;

    ImageDrawer volvoWorkshopImageDrawer;
    Point volvoWorkshopPoint;

    List<Vehicle> vehiclesToDraw = new ArrayList<>();

    void setWorkshopPosition(Point position) {
        volvoWorkshopPoint = position;
    }

    // TODO: Make this general for all cars
    void updateVehiclesToDraw(List<Vehicle> vehicles){
        vehiclesToDraw = vehicles;
    }

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.blue);
        // Print an error message in case file is not found with a try/catch block
        try {
            // You can remove the "pics" part if running outside of IntelliJ and
            // everything is in the same main folder.
            // volvoImage = ImageIO.read(new File("Volvo240.jpg"));

            // Rememember to rightclick src New -> Package -> name: pics -> MOVE *.jpg to pics.
            // if you are starting in IntelliJ.

            volvoImageDrawer = new ImageDrawer("pics/Volvo240.jpg");
            saab95ImageDrawer = new ImageDrawer("pics/Saab95.jpg");
            scaniaImageDrawer = new ImageDrawer("pics/Scania.jpg");
            unsupportedImageDrawer = new ImageDrawer();

            volvoWorkshopImageDrawer = new ImageDrawer("pics/VolvoBrand.jpg");
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }

    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Vehicle v : vehiclesToDraw) {
            switch (v.modelName) {
                case "Volvo240":
                    volvoImageDrawer.draw(g, (int)v.getPositionX(), (int)v.getPositionY());
                    break;
                case "Saab95":
                    saab95ImageDrawer.draw(g, (int)v.getPositionX(), (int)v.getPositionY());
                    break;
                case "Scania":
                    scaniaImageDrawer.draw(g, (int)v.getPositionX(), (int)v.getPositionY());
                    break;
                default:
                    unsupportedImageDrawer.draw(g, (int)v.getPositionX(), (int)v.getPositionY());
                    break;
            }
        }

        volvoWorkshopImageDrawer.draw(g, volvoWorkshopPoint.x, volvoWorkshopPoint.y);
    }
}
