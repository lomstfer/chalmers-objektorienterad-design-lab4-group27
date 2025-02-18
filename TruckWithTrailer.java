import java.awt.*;

abstract class TruckWithTrailer extends Vehicle {
    protected boolean allowedToDrive = true;

    public TruckWithTrailer(String modelName, int nrDoors, double enginePower, Color color, double positionX, double positionY) {
        super(modelName, nrDoors, enginePower, color, positionX, positionY);
    }
    @Override
    public void gas(double amount){
        if(allowedToDrive){super.gas(amount);}
    }

    @Override
    public void startEngine() {
        if(allowedToDrive){super.startEngine();}
    }

}