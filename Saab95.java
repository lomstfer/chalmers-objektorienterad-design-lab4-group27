import java.awt.*;

public class Saab95 extends Car {
    public boolean turboOn = false;

    public Saab95(double positionX, double positionY) {
        super("Saab95", 2, 125, Color.red, positionX, positionY);
    }

    public void setTurboOn(){
	turboOn = true;
    }

    public void setTurboOff(){
	turboOn = false;
    }

    @Override
    public double speedFactor(){
        double turbo = 1;
        if (turboOn) turbo = 1.3;
        return enginePower * 0.01 * turbo;
    }
}
