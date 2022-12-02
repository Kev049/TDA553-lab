package vehicle.truck;

import java.awt.*;

import vehicle.truck.Platform.ScaniaPlatform;

public class Scania extends Truck {

    private final static int numberOfDoors = 2;
    private final static double enginePower = 125.0;

    public Scania() {
        super(numberOfDoors, Color.yellow, enginePower, "Scania");
        this.platform = new ScaniaPlatform();
    }

    public int getAngle() {
        return ((ScaniaPlatform) this.platform).getAngle();
    }

    @Override
    public void move() {
        if (this.platform.isRampDown()) {
            super.move();
        }
    }

}
