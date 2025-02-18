import java.awt.*;
import java.util.Stack;

enum RampState {
    Up, Down
}
public class CarTransport extends TruckWithTrailer {
    private RampState rampStatus = RampState.Down;
    private Stack<CarTransportTransportable> loadedObjects = new Stack<>();
    private final int capacity = 100;
    private final int minDistanceToLoad = 5;

    public CarTransport(double positionX, double positionY) {
        super("Car Transport", 2, 80, Color.BLUE, positionX, positionY);
    }

    public void raise(){
        if(currentSpeed == 0){
            rampStatus = RampState.Up;
            allowedToDrive = false;}
    }

    public void lower() {
        if(currentSpeed == 0){
            rampStatus = RampState.Down;
            allowedToDrive = true;}
    }

    @Override
    double speedFactor() {
        return enginePower * 0.01;
    }

    @Override
    public void move() {
        super.move();
        for (CarTransportTransportable c : loadedObjects) {
            c.transport(getPositionX(), getPositionY());
        }
    }

    void loadCar(CarTransportTransportable car) {
        boolean allowed = rampStatus == RampState.Down &&
                          loadedObjects.size() < capacity && !car.getBeingTransported();
        if (!allowed) {
            return;
        }
        double distanceX = car.getPositionX() - getPositionX();
        double distanceY = car.getPositionY() - getPositionY();
        double distance = Math.sqrt(distanceX*distanceX + distanceY*distanceY);
        if (distance > minDistanceToLoad) {
            return;
        }
        loadedObjects.add(car);
        car.setBeingTransported(true);
    }

    CarTransportTransportable unloadCar() {
        if (rampStatus != RampState.Down) {
            return null;
        }
        CarTransportTransportable car = loadedObjects.pop();
        car.transport(getPositionX() + 5, getPositionY());
        car.setBeingTransported(false);
        return car;
    }
}
