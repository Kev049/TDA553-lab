package vehicle;
import java.awt.*;
import java.util.Stack;

public class CarHauler extends Truck {

    private final static int numberOfDoors = 2;
    private final static double enginePower = 125.0;
    private final static int maxNumberOfCars = 5;

    private CarHaulerRamp platform;
    private Stack<Car> loadedCars;

    public CarHauler(){
        super(numberOfDoors, Color.red, enginePower, "Car Hauler");
        this.platform = new CarHaulerRamp();
        this.loadedCars = new Stack<>();
    }

    public void raisePlatform() {
        if (this.getCurrentSpeed() == 0){
            this.platform.raise();
        }
    }

    public void lowerPlatform(){
        if (this.getCurrentSpeed() == 0){
            this.platform.lower();
        }
    }
    
    public void loadCar(Car car) {
        if ((this.loadedCars.contains(car) == false)) {
            if (isRampDown() && isCarNearTransporter(car) && this.loadedCars.size() < maxNumberOfCars) {
                this.loadedCars.add(car);
            }
        }
    }

    public boolean isCarNearTransporter(Car car) {
        boolean isCarNearTransporter = false;
        double transporterPosX = this.getPosition().getX();
        double transporterPosY = this.getPosition().getY();
        double carPosX = car.getPosition().getX();
        double carPosY = car.getPosition().getY();

        if (transporterPosX >= carPosX - 1 && transporterPosX <= carPosX + 1) {
            if (transporterPosY >= carPosY - 1 && transporterPosY <= carPosY + 1) {
                isCarNearTransporter = true;
            }
        }

        return isCarNearTransporter;
    }

    public void unloadCars() {
        if (isRampDown()) {
            Car unloadedCar = this.loadedCars.pop();
            // Need to set position of unloadedCar
        }
    }

    @Override
    public void move() {
        if (!platform.isRampDown()){
            super.move();
        }
    }

    public Boolean isRampDown(){
        return this.platform.isRampDown();
    }
}
