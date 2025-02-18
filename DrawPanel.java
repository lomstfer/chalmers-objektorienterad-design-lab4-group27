import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel{
    VehicleImageDrawer volvoImageDrawer;
    VehicleImageDrawer saab95ImageDrawer;
    VehicleImageDrawer scaniaImageDrawer;

    BufferedImage volvoWorkshopImage;
    Point volvoWorkshopPoint = new Point(300,300);

    void setWorkshopPosition(Point position) {
        volvoWorkshopPoint = position;
    }

    // TODO: Make this general for all cars
    void moveit(int x, int y, String carName){
        switch (carName) {
            case "Volvo240":
                volvoImageDrawer.setPoint(new Point(x, y));
                break;
            case "Saab95":
                saab95ImageDrawer.setPoint(new Point(x, y));
                break;
            case "Scania":
                scaniaImageDrawer.setPoint(new Point(x, y));
                break;
        }

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

            BufferedImage volvoImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg"));
            BufferedImage saabImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg"));
            BufferedImage scaniaImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg"));

            volvoImageDrawer = new VehicleImageDrawer(volvoImage);
            saab95ImageDrawer = new VehicleImageDrawer(saabImage);
            scaniaImageDrawer = new VehicleImageDrawer(scaniaImage);

            volvoWorkshopImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/VolvoBrand.jpg"));
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
        volvoImageDrawer.drawSelf(g);
        saab95ImageDrawer.drawSelf(g);
        scaniaImageDrawer.drawSelf(g);
        g.drawImage(volvoWorkshopImage, volvoWorkshopPoint.x, volvoWorkshopPoint.y, null);
    }
}
