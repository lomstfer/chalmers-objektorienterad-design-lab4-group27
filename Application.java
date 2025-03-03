import java.awt.*;

public class Application {
    public static void main(String[] args) {
        CarModel model = new CarModel();
        CarView view = new CarView("CarSim 1.0", new Point(400, 0));
        CarController controller = new CarController(view, model);
        model.addObserver(view);
    }
}
