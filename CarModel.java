import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class CarModel {
    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new CarModel.TimerListener());

    private ArrayList<ModelUpdateObserver> observers = new ArrayList<>();

    private ArrayList<Vehicle> vehicles = new ArrayList<>();
    private Workshop<Volvo240> workshop = new Workshop<>(2, new Point(600, 20));

    CarModel() {
        vehicles.add(new Volvo240(0,0));
        vehicles.add(new Volvo240(0,300));
        vehicles.add(new Saab95(0,100));
        vehicles.add(new Scania(0,200));
        vehicles.add(new CarTransport(0,200));

        // Start the timer
        timer.start();
    }

    public Point getWorkshopPosition() {
        return new Point(workshop.getPosition());
    }

    public void addObserver(ModelUpdateObserver o) {
        observers.add(o);
    }
    public void removeObserver(ModelUpdateObserver o) {
        observers.remove(o);
    }
    private void notifyListeners() {
        for (ModelUpdateObserver o : observers) {
            o.onModelUpdate(vehicles);
        }
    }

    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Vehicle vehicle : vehicles) {
                if (vehicle instanceof Volvo240)
                {
                    Point wp = workshop.getPosition();
                    double workshopXdist = wp.x - vehicle.getPositionX();
                    double workshopYdist = wp.y - vehicle.getPositionY();
                    double dist = Math.sqrt(workshopXdist*workshopXdist + workshopYdist*workshopYdist);
                    if (dist < 50) {
                        vehicle.stopEngine();
                        workshop.giveCar((Volvo240) vehicle);
                    }
                }

                double xBefore = vehicle.getPositionX();
                double yBefore = vehicle.getPositionY();
                vehicle.move();
                int x = (int) Math.round(vehicle.getPositionX());
                int y = (int) Math.round(vehicle.getPositionY());
                if (x < 0 || y < 0 || x > 750 || y > 750) {
                    vehicle.uTurn(xBefore, yBefore);
                }
            }

            notifyListeners();
        }
    }


    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Vehicle vehicle : vehicles) {
            vehicle.gas(gas);
        }
    }

    void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (Vehicle vehicle : vehicles) {
            vehicle.brake(brake);
        }
    }
    void turboOn() {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.modelName == "Saab95")
                ((Saab95)vehicle).setTurboOn();
        }
    }
    void turboOff() {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.modelName == "Saab95")
                ((Saab95)vehicle).setTurboOff();
        }
    }
    void liftBed() {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.modelName == "Scania")
                ((Scania)vehicle).raise(100000);
        }
    }
    void lowerBed() {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.modelName == "Scania")
                ((Scania)vehicle).lower(10000);
        }
    }
    void startAll() {
        for (Vehicle vehicle : vehicles) {
            vehicle.startEngine();
        }
    }
    void stopAll() {
        for (Vehicle vehicle : vehicles) {
            vehicle.stopEngine();
        }
    }
    void addRandomCar(){
        if (vehicles.size() < 99) {
            vehicles.add(createRandomCar());
        }
    }
    void removeNewestCar(){
        vehicles.removeLast();
    }


    private Vehicle createRandomCar() {
        Random rand = new Random();
        int n = rand.nextInt(3);
        int randomY = rand.nextInt(520);
        Vehicle newVehicle = new Volvo240(0, randomY);
        if (n == 1) {
            newVehicle = new Saab95(0, randomY);
        }
        else if (n == 2) {
            newVehicle = new Scania(0, randomY);
        }
        return newVehicle;
    }
}