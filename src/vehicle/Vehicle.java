package vehicle;

import java.awt.*;
import java.awt.geom.Point2D;

public abstract class Vehicle implements Movable {

    public static final Point NORTH = new Point(0, 1);
    public static final Point SOUTH = new Point(0, -1);
    public static final Point WEST = new Point(-1, 0);
    public static final Point EAST = new Point(1, 0);

    private final int sleepingAtTheWheel = 0;
    private final int pedalToTheMetal = 1;
    private final int jamTheBrakes = 1;
    private final int minSpeed = 0;
    private final int stationary = 0;
    private final double speedFactorVar = 0.01;

    private int nrDoors; // Number of doors on the car
    private double enginePower; // Engine power of the car
    private double currentSpeed; // The current speed of the car
    private Color color; // Color of the car
    private String modelName; // The car model name
    private Point direction;
    private Point2D.Double position;

    public Vehicle(int nrDoors, Color color, double enginePower, String modelName) {
        this.nrDoors = nrDoors;
        this.color = color;
        this.modelName = modelName;
        this.direction = NORTH;
        this.position = new Point2D.Double(0.0, 0.0);
        if (enginePower >= 0) {
            this.enginePower = enginePower;
        } else {
            enginePower = 0;
        }
        stopEngine();
    }

    public void move() {
        if (this.direction == WEST || this.direction == EAST) {
            this.position.x = calculatePosition(this.position.x, this.direction.x);
        } else {
            this.position.y = calculatePosition(this.position.y, this.direction.y);
        }
    }

    private double calculatePosition(double position, int direction) {
        double newPosition = position + this.getCurrentSpeed() * direction;
        return Math.round(newPosition * 100.0) / 100.0;
    }

    public void turnLeft() {
        if (this.direction == EAST) {
            this.direction = NORTH;
        } else if (this.direction == SOUTH) {
            this.direction = EAST;
        } else if (this.direction == NORTH) {
            this.direction = WEST;
        } else if (this.direction == WEST) {
            this.direction = SOUTH;
        }
    }

    public void turnRight() {
        if (this.direction == EAST) {
            this.direction = SOUTH;
        } else if (this.direction == SOUTH) {
            this.direction = WEST;
        } else if (this.direction == WEST) {
            this.direction = NORTH;
        } else if (this.direction == NORTH) {
            this.direction = EAST;
        }
    }

    public void startEngine() {
        double initalSpeed = 0.1;
        if (this.enginePower >= initalSpeed) {
            this.currentSpeed = initalSpeed;
        } else {
            this.currentSpeed = this.enginePower;
        }
    }

    public abstract double speedFactor();

    public void stopEngine() {
        this.currentSpeed = stationary;
    }

    public void gas(double amount) {
        if (amount >= sleepingAtTheWheel && amount <= pedalToTheMetal) {
            incrementSpeed(amount);
        }
    }

    public void brake(double amount) {
        if (amount >= sleepingAtTheWheel && amount <= jamTheBrakes) {
            decrementSpeed(amount);
        }
    }

    //TODO Refactor increment and decrementSpeed to extract out rounding calculation

    private void incrementSpeed(double amount) {
        double newSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, enginePower);
        newSpeed = Math.round(newSpeed * 10000.0) / 10000.0;
        if (currentSpeed < newSpeed) {
            currentSpeed = newSpeed;
        }
    }

    private void decrementSpeed(double amount) {
        double newSpeed = Math.max(getCurrentSpeed() - (speedFactor() * amount), minSpeed);
        newSpeed = Math.round(newSpeed * 10000.0) / 10000.0;
        if (currentSpeed > newSpeed) {
            currentSpeed = newSpeed;
        }
    }

    // Getters

    public int getNrDoors() {
        return this.nrDoors;
    }

    public double getEnginePower() {
        return this.enginePower;
    }

    public double getCurrentSpeed() {
        return this.currentSpeed;
    }

    public Color getColor() {
        return this.color;
    }

    public String getModelName() {
        return this.modelName;
    }

    public Point2D getPosition() {
        return new Point2D.Double(this.position.x, this.position.y);
    }

    public Point getDirection() {
        return new Point(this.direction.x, this.direction.y);
    }

    public double getSpeedFactorVar() {
        return this.speedFactorVar;
    }

    // Setter

    public void setColor(Color clr) {
        this.color = clr;
    }

}
