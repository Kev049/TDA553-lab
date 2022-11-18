package car;

import java.awt.*;

public class Saab95 extends Car {

    private final static int factorwhenTurboOff = 1;
    private final static double turboFactor = 1.3;
    private final static int numberOfDoors = 2;
    private final static double enginePower = 125.0;
    
    private boolean turboOn;

    public Saab95() {
        super(numberOfDoors, Color.red, enginePower, "Saab95");
        turboOn = false;
    }

    public void setTurboOn() {
        turboOn = true;
    }

    public void setTurboOff() {
        turboOn = false;
    }

    @Override
    public double speedFactor() {
        double turbo = factorwhenTurboOff;
        if (turboOn)
            turbo = turboFactor;
        return this.getEnginePower() * this.getSpeedFactorVar() * turbo;
    }

}
