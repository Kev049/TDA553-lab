package vehicle;
import java.awt.*;

public class CarHauler extends Vehicle{

    private final static int numberOfDoors = 2;
    private final static double enginePower = 125.0;

    private CarHaulerRamp platform;

    public CarHauler(){
        super(numberOfDoors, Color.red, enginePower, "Car Hauler");
        this.platform = new CarHaulerRamp();
        
    }

    @Override
    public void move() {
        if (platform.isRampDown()){
            super.move();
        }
    }

    @Override
    public double speedFactor(){
        return 1.0;
    }
}
