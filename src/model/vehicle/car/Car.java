package model.vehicle.car;

import java.awt.*;

import model.vehicle.Vehicle;

public abstract class Car extends Vehicle {

    public Car(int nrDoors, Color color, double enginePower, String modelName) {
        super(nrDoors, color, enginePower, modelName);
    }

    public abstract double speedFactor();
}