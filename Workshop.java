import java.awt.*;
import java.util.ArrayList;

public class Workshop<CarType extends Vehicle> {
    ArrayList<CarType> carsInWorkshop = new ArrayList<>();
    private final int capacity;
    private final Point position;

    public Workshop(int capacity, Point position) {
        this.capacity = capacity;
        this.position = position;
    }

    public Point getPosition() {
        return position;
    }

    public void giveCar(CarType car) {
        if (carsInWorkshop.size() < capacity && !carsInWorkshop.contains(car)) {
            this.carsInWorkshop.add(car);
        }
    }

    public CarType returnCar(CarType car) {
        if(carsInWorkshop.removeIf(n -> n == car)) {
            return car;
        }
        return null;
    }
}
