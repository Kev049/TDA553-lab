package vehicle.truck;
import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

import vehicle.car.Car;
import vehicle.truck.Platform.CarHaulerRamp;

import java.awt.geom.Point2D;

public class CarHauler extends Truck {

    private final static int numberOfDoors = 2;
    private final static double enginePower = 125.0;
    private final static int maxNumberOfCars = 5;

    private CarHaulerRamp platform;
    private ArrayDeque<Car> loadedCars;

    public CarHauler(){
        super(numberOfDoors, Color.red, enginePower, "Car Hauler");
        this.platform = new CarHaulerRamp();
        this.loadedCars = new ArrayDeque<Car>();
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

    public void unloadCar() {
        if (isRampDown()) {
            Car unloadedCar = this.loadedCars.pop();
            unloadedCar.setPosition(getUnloadedCarPos());
        }
    }

    public Deque<Car> getLoadedCars() {
        //return carHolder.getLoadedCars();
        return loadedCars;        
    }

    public Point2D.Double getUnloadedCarPos() {
        double thisPosX = this.getPosition().getX();
        double thisPosY = this.getPosition().getY();

        if (this.getDirection() == NORTH) {
            return new Point2D.Double(thisPosX, thisPosY - 1);
        } else if (this.getDirection() == SOUTH) {
            return new Point2D.Double(thisPosX, thisPosY + 1);
        } else if (this.getDirection() == EAST) {
            return new Point2D.Double(thisPosX - 1, thisPosY);
        } else {
            return new Point2D.Double(thisPosX + 1, thisPosY);
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
