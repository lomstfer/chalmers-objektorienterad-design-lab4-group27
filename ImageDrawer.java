import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageDrawer {
    private BufferedImage image;

    public ImageDrawer(String path) {
        try {
            this.image = ImageIO.read(ImageDrawer.class.getResourceAsStream(path));
        } catch (IOException e) {
            try {
                this.image = ImageIO.read(ImageDrawer.class.getResourceAsStream("pics/unsupported.png"));
            } catch (IOException e2) {}
        }
    }
    public ImageDrawer() throws IOException {
        this.image = ImageIO.read(ImageDrawer.class.getResourceAsStream("pics/unsupported.png"));
    }

    public void draw(Graphics g, int x, int y) {
        g.drawImage(this.image, x, y, null);
    }
}
