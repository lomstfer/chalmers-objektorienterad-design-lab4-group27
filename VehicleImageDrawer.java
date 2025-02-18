import java.awt.*;
import java.awt.image.BufferedImage;

public class VehicleImageDrawer {
    private Point point = new Point(0, 0);
    private BufferedImage image;

    public VehicleImageDrawer(BufferedImage image){
        this.image = image;
    }

    public void setPoint(Point newPoint) {
        this.point = newPoint;
    }

    public void drawSelf(Graphics g) {
        g.drawImage(this.image, this.point.x, this.point.y, null);
    }
}
