import java.awt.*;

public class Application {
    public static void main(String[] args) {
        CarModel model = new CarModel();
        CarView view = new CarView("CarSim 1.0", model.getWorkshopPosition());
        CarController controller = new CarController(view, model);
        model.addObserver(view);
    }
}
