import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<Vehicle> vehicles = new ArrayList<>();
    Workshop<Volvo240> workshop = new Workshop<>(2, new Point(400, 0));
    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        cc.vehicles.add(new Volvo240(0,0));
        cc.vehicles.add(new Saab95(0,100));
        cc.vehicles.add(new Scania(0,200));

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc, cc.workshop.getPosition());

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
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
                System.out.println(y);
                if (x < 0 || y < 0 || x > 750 || y > 750) {
                    vehicle.uTurn(xBefore, yBefore);
                }



                frame.drawPanel.moveit(x, y, vehicle.modelName);
                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();
            }
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
}
