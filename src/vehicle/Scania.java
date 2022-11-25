package vehicle;

import java.awt.*;


public class Scania extends Truck {

    private final static int numberOfDoors = 2;
    private final static double enginePower = 125.0;

    private ScaniaPlatform platform;

    public Scania(){
        super(numberOfDoors, Color.yellow, enginePower, "Scania");
        this.platform = new ScaniaPlatform();
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

    public int getAngle(){
        return this.platform.getAngle();
    }

    @Override
    public void move() {
        if (this.platform.getAngle() == 0){
            super.move();
        }
    }



}
