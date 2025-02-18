import java.awt.*;

public abstract class Car extends Vehicle implements CarTransportTransportable{
    public boolean beingTransported = false;
    public Car(String modelName, int nrDoors, double enginePower, Color color, double positionX, double positionY) {
        super(modelName, nrDoors, enginePower, color, positionX, positionY);
    }

    public void transport(double x, double y) {
        if(beingTransported){
            super.setPositionX(x);
            super.setPositionY(y);
        }
    }

    public boolean getBeingTransported() {
        return beingTransported;
    }

    public void setBeingTransported(boolean b) {
        beingTransported = b;
        stopEngine();
    }
    @Override
    public void gas(double amount){
        if(!beingTransported){
            super.gas(amount);
        }
    }
}
