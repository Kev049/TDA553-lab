package model.vehicle.truck;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import model.vehicle.CarHolder;
import model.vehicle.car.Car;
import model.vehicle.truck.Platform.CarHaulerRamp;

import java.awt.geom.Point2D;

public class CarHauler extends Truck implements CarHolder {

    private final static int numberOfDoors = 2;
    private final static double enginePower = 125.0;
    private final static int maxNumberOfCars = 5;

    private List<Car> loadedCars;

    public CarHauler() {
        super(numberOfDoors, Color.red, enginePower, "Car Hauler");
        this.platform = new CarHaulerRamp();
        this.loadedCars = new ArrayList<Car>();
    }

    @Override
    public List<Car> getLoadedCars() {
        return this.loadedCars;
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
    public void unloadCar(Car car) {
        if (isRampDown()) {
            this.loadedCars.remove(car);
            car.setPosition(calculatePosBehindTruck());
        }
    }

    @Override
    public boolean isCarNearby(Car car) {
        boolean isCarNearTransporter = false;
        Point2D.Double expectedPos = calculatePosBehindTruck();
        if (expectedPos.equals(car.getPosition())) {
            isCarNearTransporter = true;
        }
        return isCarNearTransporter;
    }

    private Point2D.Double calculatePosBehindTruck() {
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

}
