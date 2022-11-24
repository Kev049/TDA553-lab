package vehicle;

import java.awt.*;


public class Scania extends Vehicle {

    private final static int numberOfDoors = 2;
    private final static double enginePower = 125.0;

    private ScaniaPlatform platform;

    public Scania(){
        super(numberOfDoors, Color.yellow, enginePower, "Scania");
        this.platform = new ScaniaPlatform();
        this.platform.getAngle();
    }

    public void raiseFlatbed(){
        if (this.getCurrentSpeed() == 0){
            this.platform.raise();
        }
    }

    public void lowerFlatbed(){
        if (this.getCurrentSpeed() == 0){
            this.platform.lower();
        }
    }

    @Override
    public void move() {
        if (this.platform.getAngle() == 0){
            super.move();
        }
    }

    public double speedFactor(){
        return 1.0;
    }


}
