import java.awt.*;

public class Scania extends TruckWithTrailer {

    double trailerDegree = 0;

    public Scania(double positionX, double positionY) {
        super("Scania", 2, 100, Color.GREEN, positionX, positionY);
    }

    public void raise(double angle) {
        if (currentSpeed == 0) {
            this.trailerDegree = Math.min(70, angle + trailerDegree);
            allowedToDrive = false;
        }
    }
    public void lower(double angle) {
        if (currentSpeed == 0) {
            this.trailerDegree = Math.max(0, trailerDegree - angle);
            allowedToDrive = this.trailerDegree == 0;
        }
    }
    @Override
    public void gas(double amount){
        if(trailerDegree == 0){super.gas(amount);}
    }
    @Override
    public double speedFactor(){
        return enginePower * 0.01;
    }
}

