package model.vehicle.truck;

import java.awt.*;

import model.vehicle.Vehicle;
import model.vehicle.truck.Platform.IPlatform;

public abstract class Truck extends Vehicle {

    IPlatform platform;

    public Truck(int nrDoors, Color color, double enginePower, String modelName) {
        super(nrDoors, color, enginePower, modelName);
    }

    @Override
    public double speedFactor() {
        return this.getEnginePower() * this.getSpeedFactorVar();
    }

    public void raisePlatform() {
        if (this.getCurrentSpeed() == 0) {
            this.platform.raise();
        }
    }

    public void lowerPlatform() {
        if (this.getCurrentSpeed() == 0) {
            this.platform.lower();
        }
    }

    public boolean isRampDown() {
        return this.platform.isRampDown();
    }

}