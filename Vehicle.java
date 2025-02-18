import java.awt.*;

enum Direction {
    NORTH, SOUTH, WEST, EAST
}

abstract class Vehicle implements Movable {
    protected int nrDoors; // Number of doors on the car
    protected double enginePower; // Engine power of the car
    protected double currentSpeed = 0; // The current speed of the car
    protected Color color; // Color of the car
    protected String modelName; // The car model name

    private Direction currentDirection = Direction.EAST;
    private double positionX = 0;
    private double positionY = 0;

    public Vehicle(String modelName, int nrDoors, double enginePower, Color color, double positionX, double positionY) {
        this.modelName = modelName;
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.color = color;
        this.positionX = positionX;
        this.positionY = positionY;
        stopEngine();
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    protected void setPositionX(double x) {
        positionX = x;
    }

    protected void setPositionY(double y) {
        positionY = y;
    }

    public int getNrDoors () {
        return nrDoors;
    }

    public double getEnginePower () {
        return enginePower;
    }

    public double getCurrentSpeed () {
        return currentSpeed;
    }

    public Color getColor () {
        return color;
    }

    public void setColor (Color clr){
        color = clr;
    }

    public void startEngine () {
        currentSpeed = 0.1;
    }

    public void stopEngine () {
        currentSpeed = 0;
    }

    abstract double speedFactor();

    protected void incrementSpeed(double amount){
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, enginePower);
    }

    protected void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
    }

    public void gas(double amount){
        if(amount < 0 || amount > 1){
            throw new IllegalArgumentException();
        }
        incrementSpeed(amount);
    }

    public void brake(double amount){
        if(amount < 0 || amount > 1){
            throw new IllegalArgumentException();
        }
        decrementSpeed(amount);
    }

    public void move() {
        switch (currentDirection) {
            case NORTH -> positionY += currentSpeed;
            case SOUTH -> positionY -= currentSpeed;
            case WEST -> positionX -= currentSpeed;
            case EAST -> positionX += currentSpeed;
        }
    }

    public void turnLeft() {
        switch (currentDirection) {
            case NORTH -> currentDirection = Direction.WEST;
            case SOUTH -> currentDirection = Direction.EAST;
            case WEST -> currentDirection = Direction.SOUTH;
            case EAST -> currentDirection = Direction.NORTH;
        }
    }

    public void turnRight() {
        switch (currentDirection) {
            case NORTH -> currentDirection = Direction.EAST;
            case SOUTH -> currentDirection = Direction.WEST;
            case WEST -> currentDirection = Direction.NORTH;
            case EAST -> currentDirection = Direction.SOUTH;
        }
    }

    public void uTurn(double xBefore, double yBefore) {
        stopEngine();
        turnRight();
        turnRight();
        startEngine();
        setPositionX(xBefore);
        setPositionY(yBefore);
    }
}
