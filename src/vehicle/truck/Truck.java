package vehicle.truck;

import java.awt.*;

import vehicle.Vehicle;

public abstract class Truck extends Vehicle {

    public Truck(int nrDoors, Color color, double enginePower, String modelName) {
        super(nrDoors, color, enginePower, modelName);
    }

    public double speedFactor() {
        return this.getEnginePower() * this.getSpeedFactorVar();
    }

}