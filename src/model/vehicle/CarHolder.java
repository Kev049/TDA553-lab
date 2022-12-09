package model.vehicle;

import java.util.List;

import model.vehicle.car.Car;

public interface CarHolder {

    public List<Car> getLoadedCars();

    public void loadCar(Car car);

    public void unloadCar(Car car);

    public boolean isCarNearby(Car car);

}
