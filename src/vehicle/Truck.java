package vehicle;

import java.awt.*;

public abstract class Truck extends Vehicle {
    
    public Truck(int nrDoors, Color color, double enginePower, String modelName) {
        super(nrDoors, color, enginePower, modelName);
    }

    // @Override
    // public abstract void move();

    public double speedFactor(){
        return  this.getEnginePower() * this.getSpeedFactorVar();
    }
    
}