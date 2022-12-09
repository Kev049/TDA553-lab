package model.vehicle.car;

import java.awt.*;

public class Volvo240 extends Car {

    private final static double trimFactor = 1.25;
    private final static int numberOfDoors = 4;
    private final static double enginePower = 100.0;

    public Volvo240() {
        super(numberOfDoors, Color.black, enginePower, "Volvo240");
    }

    @Override
    public double speedFactor() {
        return this.getEnginePower() * this.getSpeedFactorVar() * trimFactor;
    }

}
