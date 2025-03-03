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
    CarController(CarView view, CarModel model) {
        view.gasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.gas(view.gasAmount);
            }
        });
        view.brakeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.brake(view.gasAmount);
            }
        });
        view.turboOnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { model.turboOn();
            }
        });
        view.turboOffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { model.turboOff();
            }
        });
        view.liftBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { model.liftBed();
            }
        });
        view.lowerBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { model.lowerBed();
            }
        });
        view.startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { model.startAll();
            }
        });
        view.stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { model.stopAll();
            }
        });
    }
}
