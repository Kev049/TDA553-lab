package vehicle.truck;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.geom.Point2D;

import vehicle.CarHolder;
import vehicle.car.Car;
import vehicle.truck.Platform.CarHaulerRamp;

public class CarHauler extends Truck implements CarHolder {

    private final static int numberOfDoors = 2;
    private final static double enginePower = 125.0;
    private final static int maxNumberOfCars = 5;

    private CarHaulerRamp platform;
    private List<Car> loadedCars;

    public CarHauler() {
        super(numberOfDoors, Color.red, enginePower, "Car Hauler");
        this.platform = new CarHaulerRamp();
        this.loadedCars = new ArrayList<Car>();
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

    @Override
    public void loadCar(Car car) {
        if ((this.loadedCars.contains(car) == false)) {
            if (isRampDown() && isCarNearby(car) && this.loadedCars.size() < maxNumberOfCars) {
                this.loadedCars.add(car);
            }
        }
    }

    @Override
    public boolean isCarNearby(Car car) {
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

    @Override
    public void unloadCar(Car car) {
        if (isRampDown()) {
            this.loadedCars.remove(car);
            car.setPosition(getUnloadedCarPos());
        }
    }

    @Override
    public List<Car> getLoadedCars() {
        return this.loadedCars;
    }

    public Point2D.Double getUnloadedCarPos() {
        double thisPosX = this.getPosition().getX();
        double thisPosY = this.getPosition().getY();

        if (this.getDirection().equals(NORTH)) {
            return new Point2D.Double(thisPosX, thisPosY - 1);
        } else if (this.getDirection().equals(SOUTH)) {
            return new Point2D.Double(thisPosX, thisPosY + 1);
        } else if (this.getDirection().equals(EAST)) {
            return new Point2D.Double(thisPosX - 1, thisPosY);
        } else {
            return new Point2D.Double(thisPosX + 1, thisPosY);
        }
    }

    @Override
    public void move() {
        if (!platform.isRampDown()) {
            super.move();
        }
    }

    public Boolean isRampDown() {
        return this.platform.isRampDown();
    }
}
